package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.auth;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationRequestTest {

    @Test
    void getEmail() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@example.com");

        assertEquals("test@example.com", authenticationRequest.getEmail());
    }

    @Test
    void getPassword() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setPassword("password");

        assertEquals("password", authenticationRequest.getPassword());
    }

    @Test
    void setEmail() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@example.com");

        assertEquals("test@example.com", authenticationRequest.getEmail());
    }

    @Test
    void setPassword() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setPassword("password");

        assertEquals("password", authenticationRequest.getPassword());
    }

    @Test
    void testEquals() {
        AuthenticationRequest authenticationRequest1 = new AuthenticationRequest("test@example.com", "password");
        AuthenticationRequest authenticationRequest2 = new AuthenticationRequest("test@example.com", "password");
        AuthenticationRequest authenticationRequest3 = new AuthenticationRequest("another@example.com", "password");

        assertEquals(authenticationRequest1, authenticationRequest2);
        assertNotEquals(authenticationRequest1, authenticationRequest3);
    }

    @Test
    void canEqual() {
        AuthenticationRequest authenticationRequest1 = new AuthenticationRequest("test@example.com", "password");
        AuthenticationRequest authenticationRequest2 = new AuthenticationRequest("test@example.com", "password");
        AuthenticationRequest authenticationRequest3 = new AuthenticationRequest("another@example.com", "password");

        assertTrue(authenticationRequest2.canEqual(authenticationRequest1));
        assertTrue(authenticationRequest1.canEqual(authenticationRequest2));
        assertTrue(authenticationRequest3.canEqual(authenticationRequest3));
    }

    @Test
    void testHashCode() {
        AuthenticationRequest authenticationRequest1 = new AuthenticationRequest("test@example.com", "password");
        AuthenticationRequest authenticationRequest2 = new AuthenticationRequest("test@example.com", "password");

        assertEquals(authenticationRequest1.hashCode(), authenticationRequest2.hashCode());
    }

    @Test
    void testToString() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@example.com", "password");

        assertEquals("AuthenticationRequest(email=test@example.com, password=password)", authenticationRequest.toString());
    }

    @Test
    void builder() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        assertEquals("test@example.com", authenticationRequest.getEmail());
        assertEquals("password", authenticationRequest.getPassword());
    }
}