package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.security.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {


    @Test
    void values() {

        Role[] roles = Role.values();

        assertThat(roles).containsExactly(Role.USER, Role.ADMIN);
    }

    @Test
    void valueOf() {

        Role role = Role.valueOf("USER");

        assertThat(role).isEqualTo(Role.USER);
    }
}