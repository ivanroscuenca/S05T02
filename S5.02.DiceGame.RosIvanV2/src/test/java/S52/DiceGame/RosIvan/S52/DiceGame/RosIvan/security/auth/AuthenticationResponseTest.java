package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class AuthenticationResponseTest {

    @Test
    void getToken() {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse("token");

        assertEquals("token", authenticationResponse.getToken());
    }

    @Test
    void setToken() {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken("token");

        assertEquals("token", authenticationResponse.getToken());
    }

    @Test
    void testEquals() {
        AuthenticationResponse authenticationResponse1 = new AuthenticationResponse("token");
        AuthenticationResponse authenticationResponse2 = new AuthenticationResponse("token");
        AuthenticationResponse authenticationResponse3 = new AuthenticationResponse("anotherToken");

        assertEquals(authenticationResponse1, authenticationResponse2);
        assertNotEquals(authenticationResponse1, authenticationResponse3);
    }

    @Test
    void canEqual() {
        AuthenticationResponse authenticationResponse1 = new AuthenticationResponse("token");
        AuthenticationResponse authenticationResponse2 = new AuthenticationResponse("token");
        AuthenticationResponse authenticationResponse3 = new AuthenticationResponse("anotherToken");

        assertTrue(authenticationResponse1.canEqual(authenticationResponse2));
        assertTrue(authenticationResponse2.canEqual(authenticationResponse1));
        assertFalse(authenticationResponse1.canEqual(authenticationResponse3));
        assertFalse(authenticationResponse3.canEqual(authenticationResponse1));
    }

    @Test
    void testHashCode() {
        AuthenticationResponse authenticationResponse1 = new AuthenticationResponse("token");
        AuthenticationResponse authenticationResponse2 = new AuthenticationResponse("token");

        assertEquals(authenticationResponse1.hashCode(), authenticationResponse2.hashCode());
    }

    @Test
    void testToString() {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse("token");

        assertEquals("AuthenticationResponse(token=token)", authenticationResponse.toString());
    }

    @Test
    void builder() {
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token("token")
                .build();

        assertEquals("token", authenticationResponse.getToken());
    }
}