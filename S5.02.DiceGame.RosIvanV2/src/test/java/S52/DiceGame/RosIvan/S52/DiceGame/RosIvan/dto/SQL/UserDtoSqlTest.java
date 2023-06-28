package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoSqlTest {

    @Test
    void getUserId() {
        UserDtoSql userDto = new UserDtoSql(13, "Ivan");

        // Use AssertJ's assertThat and the extracted property 'userId' to test the method
        assertThat(userDto.getUserId()).isEqualTo(13);
    }

    @Test
    void getName() {
        UserDtoSql userDto = new UserDtoSql(13, "Ivan");
        assertThat(userDto.getName()).isEqualTo("Ivan");
    }

    @Test
    void setUserId() {
        UserDtoSql userDto = new UserDtoSql();
        userDto.setUserId(13);
        assertThat(userDto.getUserId()).isEqualTo(13);
    }

    @Test
    void setName() {
        UserDtoSql userDto = new UserDtoSql();
        userDto.setName("Ivan");
        assertThat(userDto.getName()).isEqualTo("Ivan");
    }

    @Test
    void testEquals() {
        UserDtoSql userDto1 = new UserDtoSql(1, "Ivan");
        UserDtoSql userDto2 = new UserDtoSql(1, "Ivan");

        assertThat(userDto1).isEqualTo(userDto2);
    }



    @Test
    void canEqual() {
        UserDtoSql userDto = new UserDtoSql(1, "Ivan");

        // Test the canEqual() method using AssertJ's assertThat
        assertThat(userDto.canEqual(new UserDtoSql())).isTrue();
    }

    @Test
    void testHashCode() {
        UserDtoSql userDto1 = new UserDtoSql(1, "John");
        UserDtoSql userDto2 = new UserDtoSql(1, "John");

        // Test the hashCode() method using AssertJ's assertThat
        assertThat(userDto1.hashCode()).isEqualTo(userDto2.hashCode());
    }

    @Test
    void testToString() {
        UserDtoSql userDto = new UserDtoSql(1, "John");
        assertThat(userDto.toString()).isEqualTo("UserDtoSql(userId=1, name=John)");
    }
}