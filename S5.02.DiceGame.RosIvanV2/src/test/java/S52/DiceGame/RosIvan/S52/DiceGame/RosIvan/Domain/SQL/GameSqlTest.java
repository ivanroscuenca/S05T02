package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


class GameSqlTest {

    @Test
    void getGameId() {
        GameSql gameSql = new GameSql();
        gameSql.setGameId(1);
        Integer gameId = gameSql.getGameId();
        assertThat(gameId).isEqualTo(1);
    }

    @Test
    void getDice1() {
        GameSql gameSql = new GameSql();
        gameSql.setDice1(3);
        int dice1 = gameSql.getDice1();
        assertThat(dice1).isEqualTo(3);

    }

    @Test
    void getDice2() {

        GameSql gameSql = new GameSql();
        gameSql.setDice2(4);
        int dice2 = gameSql.getDice2();
        assertThat(dice2).isEqualTo(4);
    }

    @Test
    void getResultGame() {
        GameSql gameSql = new GameSql();
        gameSql.setResultGame(GameSql.ResultGame.LOSER);
        GameSql.ResultGame resultGame = gameSql.getResultGame();
        assertThat(resultGame).isEqualTo(GameSql.ResultGame.LOSER);
    }

    @Test
    void getTimeRegistration() {
        GameSql gameSql = new GameSql();
        Date expectedTimeRegistration = new Date();
        gameSql.setTimeRegistration(expectedTimeRegistration);
        Date actualTimeRegistration = gameSql.getTimeRegistration();
        assertThat(actualTimeRegistration).isEqualTo(expectedTimeRegistration);
    }

    @Test
    void setGameId() {
        GameSql gameSql = new GameSql();
        gameSql.setGameId(2);
        int gameId = gameSql.getGameId();
        assertThat(gameId).isEqualTo(2);
    }

    @Test
    void setDice1() {
        GameSql gameSql = new GameSql();
        gameSql.setDice1(3);
        int dice1 = gameSql.getDice1();
        assertThat(dice1).isEqualTo(3);
    }

    @Test
    void setDice2() {
        GameSql gameSql = new GameSql();
        gameSql.setDice2(4);
        int dice2 = gameSql.getDice2();
        assertThat(dice2).isEqualTo(4);
    }

    @Test
    void setResultGame() {
        GameSql gameSql = new GameSql();
        gameSql.setResultGame(GameSql.ResultGame.WINNER);
        GameSql.ResultGame resultGame = gameSql.getResultGame();
        assertThat(resultGame).isEqualTo(GameSql.ResultGame.WINNER);
    }

    @Test
    void setTimeRegistration() {
        GameSql gameSql = new GameSql();
        Date expectedTimeRegistration = new Date();
        gameSql.setTimeRegistration(expectedTimeRegistration);
        Date actualTimeRegistration = gameSql.getTimeRegistration();
        assertThat(actualTimeRegistration).isEqualTo(expectedTimeRegistration);
    }

    @Test
    void testToString() {
            // Create a UserSql object
            UserSql userSql = new UserSql();
            userSql.setId(1);
            userSql.setName("John Doe");

            // Create a GameSql object
            GameSql gameSql = new GameSql(userSql);
            gameSql.setGameId(1);
            gameSql.setDice1(4);
            gameSql.setDice2(3);
            gameSql.setResultGame(GameSql.ResultGame.WINNER);
            gameSql.setTimeRegistration(new Date());
            String toStringResult = gameSql.toString();
            // Define the expected string representation
            String expectedString = "GameSql{gameId=1, userSql=UserSql{id=1, name='John Doe'}, dice1=4, dice2=3, resultGame=WINNER, timeRegistration=" + gameSql.getTimeRegistration() + "}";
            // Assert that the toStringResult matches the expected string representation
            assertThat(toStringResult).isEqualTo(expectedString);
        }


        @Test
    void getUserSql() {
            UserSql userSql = new UserSql();
            userSql.setId(1);
            userSql.setName("Ivan");
            // Create a GameSql object with the associated UserSql object
            GameSql gameSql = new GameSql(userSql);

            // Retrieve the UserSql object using getUserSql() method
            UserSql retrievedUserSql = gameSql.getUserSql();

            // Use AssertJ assertions to verify the retrieved UserSql object
            assertThat(retrievedUserSql).isNotNull();
            assertThat(retrievedUserSql.getId()).isEqualTo(1);
            assertThat(retrievedUserSql.getName()).isEqualTo("Ivan");
        }



    @Test
    void setUserSql() {
        UserSql userSql = new UserSql();
        userSql.setId(1);
        userSql.setName("Ivan");
        // Create a GameSql object with the associated UserSql object
        GameSql gameSql = new GameSql(userSql);

        // Retrieve the UserSql object using getUserSql() method
        UserSql retrievedUserSql = gameSql.getUserSql();

        // Use AssertJ assertions to verify the retrieved UserSql object
        assertThat(retrievedUserSql).isNotNull();
        assertThat(retrievedUserSql.getId()).isEqualTo(1);
        assertThat(retrievedUserSql.getName()).isEqualTo("Ivan");
    }
}