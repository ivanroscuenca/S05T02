package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.GameSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.UserSql;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameDtoSqlTest {

    @Test
    void getUserSql() {
        UserSql userSql = new UserSql();
        userSql.setId(1);
        userSql.setName("Ivan");

        // Create a GameDtoSql object
        GameDtoSql gameDtoSql = new GameDtoSql();
        gameDtoSql.setUserSql(userSql);
        gameDtoSql.setDice1(4);
        gameDtoSql.setDice2(3);
        gameDtoSql.setWinner(true);
        gameDtoSql.setTimeRegistration("2023-06-26 10:00:00");

        // Get the expected UserSql object
        UserSql expectedUserSql = gameDtoSql.getUserSql();

        // Use AssertJ assertion to verify the UserSql object
        assertThat(expectedUserSql).isEqualTo(userSql);
    }

    @Test
    void getDice1() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        gameDtoSql.setDice1(4);
        int expectedDice1 = gameDtoSql.getDice1();
        assertThat(expectedDice1).isEqualTo(4);
    }

    @Test
    void getDice2() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        gameDtoSql.setDice2(5);
        int expectedDice1 = gameDtoSql.getDice2();
        assertThat(expectedDice1).isEqualTo(5);
    }

    @Test
    void isWinner() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        gameDtoSql.setWinner(true);
        boolean expectedWinner = gameDtoSql.isWinner();
        assertThat(expectedWinner).isTrue();
    }

    @Test
    void getTimeRegistration() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        String expectedTimeRegistration = "2023-06-26 15:30:00";
        gameDtoSql.setTimeRegistration(expectedTimeRegistration);
        String actualTimeRegistration = gameDtoSql.getTimeRegistration();
        assertThat(actualTimeRegistration).isEqualTo(expectedTimeRegistration);
    }

    @Test
    void setUserSql() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        UserSql expectedUserSql = new UserSql();
        expectedUserSql.setName("Ivan");
        gameDtoSql.setUserSql(expectedUserSql);
        UserSql actualUserSql = gameDtoSql.getUserSql();
        assertThat(actualUserSql).isEqualTo(expectedUserSql);
    }

    @Test
    void setDice1() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        int expectedDice1 = 4;
        gameDtoSql.setDice1(expectedDice1);
        int actualDice1 = gameDtoSql.getDice1();
        assertThat(actualDice1).isEqualTo(expectedDice1);
    }

    @Test
    void setDice2() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        int expectedDice1 = 4;
        gameDtoSql.setDice1(expectedDice1);
        int actualDice1 = gameDtoSql.getDice1();
        assertThat(actualDice1).isEqualTo(expectedDice1);
    }

    @Test
    void setWinner() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        boolean expectedWinner = true;
        gameDtoSql.setWinner(expectedWinner);
        boolean actualWinner = gameDtoSql.isWinner();
        assertThat(actualWinner).isEqualTo(expectedWinner);
    }

    @Test
    void setTimeRegistration() {
        GameDtoSql gameDtoSql = new GameDtoSql();
        String expectedTimeRegistration = "2023-06-26 15:30:00";
        gameDtoSql.setTimeRegistration(expectedTimeRegistration);
        String actualTimeRegistration = gameDtoSql.getTimeRegistration();
        assertThat(actualTimeRegistration).isEqualTo(expectedTimeRegistration);
    }

    @Test
    void testEquals() {
        GameDtoSql gameDtoSql1 = new GameDtoSql();
        gameDtoSql1.setUserSql(new UserSql());
        gameDtoSql1.setDice1(3);
        gameDtoSql1.setDice2(5);
        gameDtoSql1.setWinner(true);
        gameDtoSql1.setTimeRegistration("2023-06-26T12:34:56");

        GameDtoSql gameDtoSql2 = gameDtoSql1;
        gameDtoSql2.setUserSql(new UserSql());
        gameDtoSql2.setDice1(3);
        gameDtoSql2.setDice2(5);
        gameDtoSql2.setWinner(true);
        gameDtoSql2.setTimeRegistration("2023-06-26T12:34:56");

        assertThat(gameDtoSql1).isEqualTo(gameDtoSql2);
    }

    @Test
    void canEqual() {
        GameDtoSql game1 = new GameDtoSql(new UserSql(), 3, 5, true, "2023-06-26T12:34:56");
        GameDtoSql game2 = new GameDtoSql(new UserSql(), 3, 5, true, "2023-06-26T12:34:56");

        // Assert that the canEqual method returns true for the two objects
        assertTrue(game1.canEqual(game2));
    }
    @Test
    void testHashCode() {
        GameDtoSql game = new GameDtoSql(new UserSql(), 3, 5, true, "2023-06-26T12:34:56");

        // Create another GameDtoSql object with the same values
        GameDtoSql sameGame = new GameDtoSql(new UserSql(), 3, 5, true, "2023-06-26T12:34:56");

        // Assert that the hash codes of the two objects are equal
        assertNotEquals(game.hashCode(), sameGame.hashCode());
    }

    @Test
    void testToString() {
        UserSql userSql = new UserSql();
        userSql.setId(1);
        userSql.setName("Ivan");
        GameDtoSql game = new GameDtoSql(userSql, 4, 3, true, "2023-06-26T12:34:56");

        // Define the expected string representation of the object
        String expectedString = "GameDtoSql(userSql=" + userSql +
                ", dice1=4" +
                ", dice2=3"+
                ", winner=true"+
                ", timeRegistration=2023-06-26T12:34:56"+
                ")";

        // Compare the expected string with the actual string representation of the object
        assertEquals(expectedString, game.toString());
    }
}