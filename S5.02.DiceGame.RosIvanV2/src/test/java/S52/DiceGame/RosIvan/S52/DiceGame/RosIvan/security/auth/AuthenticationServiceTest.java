package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.auth;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.config.JwtService;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.user.Role;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.user.User;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;


    @Test
    void register() {
        // Prepare test data
        RegisterRequest request = new RegisterRequest();
        request.setFirstname("Ivan");
        request.setLastname("Ros");
        request.setEmail("iros@gmail.com");
        request.setPassword("password");

        User mockUser = User.builder()
                .id(1)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(jwtService.generateToken(any(User.class))).thenReturn("jwtToken");

        // Perform the registration
        AuthenticationResponse response = authenticationService.register(request);

        // Verify the results
        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());

        verify(userRepository).findByEmail(request.getEmail());
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(any(User.class));
        verify(jwtService).generateToken(any(User.class));
    }



    @Test
    void authenticate() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("iros@gmail.com");
        request.setPassword("password");

        User mockUser = User.builder()
                .id(1)
                .firstname("Ivan")
                .lastname("Ros")
                .email(request.getEmail())
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(mockUser));
        when(jwtService.generateToken(mockUser)).thenReturn("jwtToken");

        // Perform the authentication
        AuthenticationResponse response = authenticationService.authenticate(request);

        // Verify the results
        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail(request.getEmail());
        verify(jwtService).generateToken(mockUser);
    }
}