package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.auth;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    void register() {
        // Prepare test data
        RegisterRequest request = new RegisterRequest();
        // Set up any necessary expectations or mock behavior on the authenticationService
        when(authenticationService.register(any(RegisterRequest.class)))
                .thenReturn(new AuthenticationResponse("token"));

        // Perform the register request
        ResponseEntity<AuthenticationResponse> response = authenticationController.register(request);

        // Verify the results
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("token", response.getBody().getToken());

        verify(authenticationService).register(any(RegisterRequest.class));
    }

    @Test
    void authenticate() throws AuthenticationException {
        // Prepare test data
        AuthenticationRequest request = new AuthenticationRequest();
        // Set up any necessary expectations or mock behavior on the authenticationService
        when(authenticationService.authenticate(any(AuthenticationRequest.class)))
                .thenReturn(new AuthenticationResponse("token"));

        // Perform the authenticate request
        ResponseEntity<AuthenticationResponse> response = authenticationController.authenticate(request);

        // Verify the results
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("token", response.getBody().getToken());

        verify(authenticationService).authenticate(any(AuthenticationRequest.class));
    }
}