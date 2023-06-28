package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


class UserSqlTest {

    @Test
    void getListGames() {
        UserSql userSql = new UserSql();

        // Create some GameSql objects
        GameSql game1 = new GameSql();
        GameSql game2 = new GameSql();

        // Add the GameSql objects to the UserSql's listGames
        userSql.addGamesTolistGames(game1);
        userSql.addGamesTolistGames(game2);

        // Retrieve the listGames from the UserSql object using getListGames() method
        List<GameSql> retrievedGames = userSql.getListGames();

        // Use AssertJ assertions to verify the retrieved listGames
        assertThat(retrievedGames).isNotNull();
        assertThat(retrievedGames).hasSize(2);
        assertThat(retrievedGames).containsExactly(game1, game2);
    }


    @Test
    void addGamesTolistGames() {

        UserSql userSql = new UserSql();
        GameSql game = new GameSql();
        userSql.addGamesTolistGames(game);
        List<GameSql> retrievedGames = userSql.getListGames();
        assertThat(retrievedGames).isNotNull();
        assertThat(retrievedGames).hasSize(1);
        assertThat(retrievedGames).containsExactly(game);
    }

    @Test
    void deleteFromlistGames() {
        UserSql userSql = new UserSql();
        GameSql game1 = new GameSql();
        GameSql game2 = new GameSql();
        GameSql game3 = new GameSql();

        // Add the GameSql objects to the UserSql's listGames using addGamesTolistGames() method
        userSql.addGamesTolistGames(game1);
        userSql.addGamesTolistGames(game2);
        userSql.addGamesTolistGames(game3);

        // Delete all games from the UserSql's listGames using deleteFromlistGames() method
        userSql.deleteFromlistGames();

        // Retrieve the listGames from the UserSql object
        List<GameSql> retrievedGames = userSql.getListGames();

        // Use AssertJ assertions to verify that the listGames is empty
        assertThat(retrievedGames).isNotNull();
        assertThat(retrievedGames).isEmpty();

    }

    @Test
    void setSuccessRate() {
        UserSql userSql = new UserSql();

        // Create GameSql objects with different resultGame values
        GameSql game1 = new GameSql();
        game1.setResultGame(GameSql.ResultGame.WINNER);

        GameSql game2 = new GameSql();
        game2.setResultGame(GameSql.ResultGame.WINNER);

        GameSql game3 = new GameSql();
        game3.setResultGame(GameSql.ResultGame.LOSER);

        // Add the GameSql objects to the UserSql's listGames using addGamesTolistGames() method
        userSql.addGamesTolistGames(game1);
        userSql.addGamesTolistGames(game2);
        userSql.addGamesTolistGames(game3);

        // Invoke the setSuccessRate() method to calculate the success rate
        userSql.setSuccessRate();

        // Retrieve the success rate from the UserSql object
        double successRate = userSql.getSuccessRate();

        // Use AssertJ assertions to verify the success rate
        assertThat(successRate).isEqualTo(0.67);
    }

    @Test
    void getName() {
        UserSql userSql = new UserSql();
        userSql.setName("Ivan");
        String name = userSql.getName();
        assertThat(name).isEqualTo("Ivan");
    }

    @Test
    void getId() {
        UserSql userSql = new UserSql();
        userSql.setId(13);
        int id = userSql.getId();
        assertThat(id).isEqualTo(13);
    }

    @Test
    void getSuccessRate() {
        UserSql userSql = new UserSql();
        userSql.setSuccessRate(0.50);
        Double rate = userSql.getSuccessRate();
        assertThat(rate).isEqualTo(0.50);
    }

    @Test
    void setId() {
        UserSql userSql = new UserSql();
        userSql.setId(13);
        int id = userSql.getId();
        assertThat(id).isEqualTo(13);
    }

    @Test
    void setName() {
        UserSql userSql = new UserSql();
        userSql.setName("Ivan");
        String name = userSql.getName();
        assertThat(name).isEqualTo("Ivan");
    }

    @Test
    void testSetSuccessRate() {
        UserSql userSql = new UserSql();

        // Create GameSql objects with different resultGame values
        GameSql game1 = new GameSql();
        game1.setResultGame(GameSql.ResultGame.WINNER);

        GameSql game2 = new GameSql();
        game2.setResultGame(GameSql.ResultGame.LOSER);


        // Add the GameSql objects to the UserSql's listGames using addGamesTolistGames() method
        userSql.addGamesTolistGames(game1);
        userSql.addGamesTolistGames(game2);

        // Invoke the setSuccessRate() method to calculate the success rate
        userSql.setSuccessRate();

        // Retrieve the success rate from the UserSql object
        double successRate = userSql.getSuccessRate();

        // Use AssertJ assertions to verify the success rate
        assertThat(successRate).isEqualTo(0.50);
    }

    @Test
    void setListGames() {
        UserSql userSql = new UserSql();

        // Create a list of GameSql objects
        List<GameSql> gameList = new ArrayList<>();

        // Create GameSql objects and add them to the gameList
        GameSql game1 = new GameSql(userSql);
        gameList.add(game1);

        GameSql game2 = new GameSql(userSql);
        gameList.add(game2);

        // Set the listGames of the UserSql object
        userSql.setListGames(gameList);

        // Retrieve the listGames from the UserSql object
        List<GameSql> retrievedGameList = userSql.getListGames();

        // Use AssertJ assertions to verify the retrieved listGames
        assertThat(retrievedGameList).isEqualTo(gameList);
    }

    @Test
    void testToString() {
        UserSql userSql = new UserSql();
        userSql.setId(1);
        userSql.setName("Ivan");

        // Get the expected string representation
        String expectedString = "UserSql{id=1, name='Ivan'}";

        // Call the toString() method on the UserSql object
        String actualString = userSql.toString();

        // Use AssertJ assertion to verify the string representation
        assertThat(actualString).isEqualTo(expectedString);
    }

}