package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegisterRequestTest {

    @Test
    void getFirstname() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname("John");

        assertEquals("John", registerRequest.getFirstname());
    }

    @Test
    void getLastname() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setLastname("Doe");

        assertEquals("Doe", registerRequest.getLastname());
    }

    @Test
    void getEmail() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("john.doe@example.com");

        assertEquals("john.doe@example.com", registerRequest.getEmail());
    }

    @Test
    void getPassword() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("password");

        assertEquals("password", registerRequest.getPassword());
    }

    @Test
    void setFirstname() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname("John");

        assertEquals("John", registerRequest.getFirstname());
    }

    @Test
    void setLastname() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setLastname("Doe");

        assertEquals("Doe", registerRequest.getLastname());
    }

    @Test
    void setEmail() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("john.doe@example.com");

        assertEquals("john.doe@example.com", registerRequest.getEmail());
    }

    @Test
    void setPassword() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPassword("password");

        assertEquals("password", registerRequest.getPassword());
    }

    @Test
    void testEquals() {
        RegisterRequest registerRequest1 = new RegisterRequest("John", "Doe", "john.doe@example.com", "password");
        RegisterRequest registerRequest2 = new RegisterRequest("John", "Doe", "john.doe@example.com", "password");
        RegisterRequest registerRequest3 = new RegisterRequest("Jane", "Smith", "jane.smith@example.com", "password");

        assertEquals(registerRequest1, registerRequest2);
        assertNotEquals(registerRequest1, registerRequest3);
    }

    @Test
    void canEqual() {
        RegisterRequest registerRequest1 = new RegisterRequest("John", "Doe", "john.doe@example.com", "password");
        RegisterRequest registerRequest2 = new RegisterRequest("John", "Doe", "john.doe@example.com", "password");
        RegisterRequest registerRequest3 = new RegisterRequest("Jane", "Smith", "jane.smith@example.com", "password");

        assertTrue(registerRequest1.canEqual(registerRequest2));
        assertTrue(registerRequest2.canEqual(registerRequest1));
        assertTrue(registerRequest1.canEqual(registerRequest3));
        assertTrue(registerRequest3.canEqual(registerRequest1));
    }

    @Test
    void testHashCode() {
        RegisterRequest registerRequest1 = new RegisterRequest("John", "Doe", "john.doe@example.com", "password");
        RegisterRequest registerRequest2 = new RegisterRequest("John", "Doe", "john.doe@example.com", "password");

        assertEquals(registerRequest1.hashCode(), registerRequest2.hashCode());
    }

    @Test
    void testToString() {
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john.doe@example.com", "password");

        assertEquals("RegisterRequest(firstname=John, lastname=Doe, email=john.doe@example.com, password=password)", registerRequest.toString());
    }

    @Test
    void builder() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .password("password")
                .build();

        assertEquals("John", registerRequest.getFirstname());
        assertEquals("Doe", registerRequest.getLastname());
        assertEquals("john.doe@example.com", registerRequest.getEmail());
        assertEquals("password", registerRequest.getPassword());
    }
}