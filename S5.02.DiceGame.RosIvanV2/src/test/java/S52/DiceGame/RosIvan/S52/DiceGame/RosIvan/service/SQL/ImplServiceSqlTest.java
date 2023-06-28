package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.service.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.GameSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.UserSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.GameDtoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.UserDtoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.repository.SQL.GameRepoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.repository.SQL.UserRepoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.service.SQL.ImplServiceSql;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ImplServiceSqlTest {

    @Mock
    private GameRepoSql gameRepoSql;

    @Mock
    private UserRepoSql userRepoSql;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ImplServiceSql implServiceSql;

    @Test
    void findAllUsers() {
        // Arrange
        List<UserSql> users = Arrays.asList(new UserSql(), new UserSql());
        when(userRepoSql.findAll()).thenReturn(users);
        when(modelMapper.map(any(UserSql.class), eq(UserDtoSql.class))).thenReturn(new UserDtoSql());

        // Act
        List<UserDtoSql> result = implServiceSql.findAllUsers();

        // Assert
        assertThat(result).hasSize(2);
        verify(userRepoSql, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(UserSql.class), eq(UserDtoSql.class));
    }

    @Test
    void findGamesByUserId() {
        // Arrange
        Integer userId = 1;
        List<GameSql> games = Arrays.asList(new GameSql(), new GameSql());
        when(gameRepoSql.findAllByUserSqlId(userId)).thenReturn(games);

        // Act
        Optional<List<GameSql>> result = implServiceSql.findGamesByUserId(userId);

        // Assert
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).hasSize(2);
        verify(gameRepoSql, times(1)).findAllByUserSqlId(userId);
    }

    @Test
    void createUser() {
        // Arrange
        UserDtoSql userDtoSql = new UserDtoSql();

        // Act
        implServiceSql.createUser(userDtoSql);

        // Assert
        verify(userRepoSql, times(1)).save(any(UserSql.class));
    }

    @Test
    void updateUser() {
        // Arrange
        UserDtoSql userDtoSql = new UserDtoSql();
        UserSql userSql = new UserSql();
        when(modelMapper.map(userDtoSql, UserSql.class)).thenReturn(userSql);

        // Act
        implServiceSql.updateUser(userDtoSql);

        // Assert
        verify(userRepoSql, times(1)).save(userSql);
    }

    @Test
    void findUserById() {
        // Arrange
        Integer userId = 1;
        UserSql userSql = new UserSql();
        when(userRepoSql.findById(userId)).thenReturn(Optional.of(userSql));
        when(modelMapper.map(userSql, UserDtoSql.class)).thenReturn(new UserDtoSql());

        // Act
        UserDtoSql result = implServiceSql.findUserById(userId);

        // Assert
        assertThat(result).isNotNull();
        verify(userRepoSql, times(1)).findById(userId);
        verify(modelMapper, times(1)).map(userSql, UserDtoSql.class);
    }

    @Test
    void createGame() {
        // Arrange
        Integer userId = 1;
        UserSql userSql = new UserSql();
        GameSql newGame = new GameSql(userSql);
        when(userRepoSql.findById(userId)).thenReturn(Optional.of(userSql));
        when(gameRepoSql.save(newGame)).thenReturn(newGame);
        when(modelMapper.map(newGame, GameDtoSql.class)).thenReturn(new GameDtoSql());

        // Act
        GameDtoSql result = implServiceSql.createGame(userId);

        // Assert
        assertThat(result).isNotNull();
        verify(userRepoSql, times(1)).findById(userId);
        verify(gameRepoSql, times(1)).save(newGame);
        verify(modelMapper, times(1)).map(newGame, GameDtoSql.class);
    }

    @Test
    void deleteGamesByUserId() {
    }

    @Test
    void rankingAll() {
    }

    @Test
    void calculateUsersRank() {
    }

    @Test
    void rankingWinner() {
    }

    @Test
    void rankingLooser() {
    }

    @Test
    void convertUserEntityToDTO() {
    }

    @Test
    void convertUserDTOToEntity() {
    }

    @Test
    void convertGameEntityToDTO() {
    }

    @Test
    void convertGameDTOToEntity() {
    }
}