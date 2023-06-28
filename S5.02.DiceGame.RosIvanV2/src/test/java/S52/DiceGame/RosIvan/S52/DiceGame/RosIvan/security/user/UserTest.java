package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;


class UserTest {

    @Test
    void getAuthorities() {
        //arrange
        Role role = Role.ADMIN;
        User user = User.builder().role(role).build();

        // Act
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Convert the authorities to a list of strings
        List<String> authorityStrings = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Assert
        assertThat(authorityStrings).containsExactlyInAnyOrder(role.name());
    }


    @Test
    void getPassword() {
        //arrange or given
        String password = "password123";
        User user = User.builder().password(password).build();
        //act or when
        String retrievedPassword = user.getPassword();
        //assert or then
        assertThat(retrievedPassword).isEqualTo(password);
    }

    @Test
    void getUsername() {
    String email = "iros@gmail.com";
    User user = User.builder().email(email).build();
    String emailRetrieved = user.getEmail();
    assertThat(emailRetrieved).isEqualTo(email);
    }

    @Test
    void isAccountNonExpired() {
        User user = User.builder().build();

        boolean accountNonExpired = user.isAccountNonExpired();

       assertThat(accountNonExpired).isTrue();
    }

    @Test
    void isAccountNonLocked() {
        User user = User.builder().build();

        boolean accountNonLocked = user.isAccountNonLocked();

        assertThat(accountNonLocked).isTrue();
    }

    @Test
    void isCredentialsNonExpired() {
        User user = User.builder().build();

        boolean CredentialsNonExpired = user.isCredentialsNonExpired();

        assertThat(CredentialsNonExpired).isTrue();

    }

    @Test
    void isEnabled() {
        User user = new User();
        boolean enabled = user.isEnabled();
        assertThat(enabled).isTrue();
    }

    @Test
    void getId() {
        Integer id = 1;
        User user = User.builder().id(id).build();
        Integer idRetrieved = user.getId();
        assertThat(idRetrieved).isEqualTo(id);
    }

    @Test
    void getFirstname() {
        String firstname = "Ivan";
        User user = User.builder().firstname(firstname).build();

        String result = user.getFirstname();

        assertThat(result).isEqualTo(firstname);
    }

    @Test
    void getLastname() {

        String lastname = "Ros";
        User user = User.builder().lastname(lastname).build();

        String result = user.getLastname();

        assertThat(result).isEqualTo(lastname);
    }

    @Test
    void getEmail() {
        // Arrange
        String email = "iros@gmail.com";
        User user = User.builder().email(email).build();

        String result = user.getEmail();

        assertThat(result).isEqualTo(email);
    }

    @Test
    void getRole() {

        Role role = Role.ADMIN;
        User user = User.builder().role(role).build();

        Role result = user.getRole();

        assertThat(result).isEqualTo(role);
    }

    @Test
    void setId() {
        int id = 1;
        User user = new User();

        user.setId(id);

        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void setFirstname() {
        String firstname = "Ivan";
        User user = new User();

        user.setFirstname(firstname);

        assertThat(user.getFirstname()).isEqualTo(firstname);
    }

    @Test
    void setLastname() {
        String lastname = "Ros";
        User user = new User();

        user.setLastname(lastname);

        assertThat(user.getLastname()).isEqualTo(lastname);
    }

    @Test
    void setEmail() {
        String email = "iros@gmail.com";
        User user = new User();

        user.setEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void setPassword() {
        String password = "1234";
        User user = new User();

        user.setPassword(password);

        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    void setRole() {
        Role role = Role.ADMIN;
        User user = new User();

        user.setRole(role);

        assertThat(user.getRole()).isEqualTo(role);
    }

    @Test
    void testEquals() {
        // Arrange
        User user1 = User.builder().id(1).build();
        User user2 = User.builder().id(1).build();
        User user3 = User.builder().id(2).build();

        // Assert
        assertThat(user1).isEqualTo(user2); // Mismo ID, deberían ser iguales
        assertThat(user1).isNotEqualTo(user3); // IDs diferentes, no deberían ser iguales
    }

    @Test
    void canEqual() {

        User user = new User();

        assertThat(user.canEqual(new User())).isTrue(); // Otro objeto de la misma clase
        assertThat(user.canEqual(new Object())).isFalse(); // Objeto de otra clase
    }

    @Test
    void testHashCode() {
        // Arrange
        User user = User.builder().id(1).build();
        int expectedHashCode = 475815063;

        // Act
        int actualHashCode = user.hashCode();

        // Assert
        assertThat(actualHashCode).isEqualTo(expectedHashCode);
    }


    @Test
    void testToString() {
        // Arrange
        String firstname = "Ivan";
        String lastname = "Ros";
        User user = User.builder().firstname(firstname).lastname(lastname).build();
        String expectedToString = "User(id=null, firstname=Ivan, lastname=Ros, email=null, password=null, role=null)";

        // Assert
        assertThat(user.toString()).isEqualTo(expectedToString);
    }

    @Test
    void builder() {
        // Arrange
        String firstname = "Ivan";
        String lastname = "Ros";
        String email = "iros@protonmail.com";
        String password = "password";
        Role role = Role.ADMIN;

        // Act
        User user = User.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(password)
                .role(role)
                .build();

        // Assert
        assertThat(user.getFirstname()).isEqualTo(firstname);
        assertThat(user.getLastname()).isEqualTo(lastname);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getRole()).isEqualTo(role);
    }

}