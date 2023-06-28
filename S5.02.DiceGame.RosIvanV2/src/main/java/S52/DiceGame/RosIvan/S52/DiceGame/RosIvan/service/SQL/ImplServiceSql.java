package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.service.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.GameSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.UserSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.GameDtoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.UserDtoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.repository.SQL.GameRepoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.repository.SQL.UserRepoSql;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ImplServiceSql implements ServiceSql {

    @Autowired
    private GameRepoSql gameRepoSql;
    @Autowired
    private UserRepoSql userRepoSql;
    @Autowired
    private ModelMapper modelMapper;

    //Users
    @Override
    public List<UserDtoSql> findAllUsers() {
        return userRepoSql.findAll()
                .stream()
                .map(this::convertUserEntityToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<List<GameSql>> findGamesByUserId(Integer id) {
        List<GameSql> games = gameRepoSql.findAllByUserSqlId(id);
        return Optional.of(games);
    }


    //done
    @Override
    public void createUser(UserDtoSql userDtoSql) {
        userRepoSql.save(modelMapper.map(userDtoSql, UserSql.class));

    }

    @Override
    public void updateUser(UserDtoSql userDtoSql) {
        UserSql userSql = convertUserDTOToEntity(userDtoSql);
        userRepoSql.save(userSql);
    }

    @Override
    public UserDtoSql findUserById(Integer id) {
        Optional<UserSql> optionalUser = userRepoSql.findById(id);
        UserDtoSql userDtoSql = null;
        if (optionalUser.isPresent()) {
            userDtoSql = convertUserEntityToDTO(optionalUser.get());
        }
        return userDtoSql;
    }


    @Override
    public GameDtoSql createGame(Integer id) {
        Optional<UserSql> optionalUser = userRepoSql.findById(id);
        GameSql newGame = null;
        if (optionalUser.isPresent()) {
            UserSql userSql = optionalUser.get();
            newGame = new GameSql(userSql);
            //userSql add to list
            userSql.addGamesTolistGames(newGame);
            gameRepoSql.save(newGame);
        }
        return convertGameEntityToDTO(newGame);
    }


    @Override
    public void deleteGamesByUserId(Integer id) {
        Optional<UserSql> optionalUser = userRepoSql.findById(id);
        if (optionalUser.isPresent()) {
            UserSql userSelected = optionalUser.get();
            userSelected.deleteFromlistGames();
            userRepoSql.save(userSelected);
        }

    }


//Games

    @Override
    public List<GameDtoSql> rankingAll() {
        return gameRepoSql.findAll()
                .stream()
                .map(this::convertGameEntityToDTO)
                .collect(Collectors.toList());

    }

    public List<String> calculateUsersRank() {
        List<UserSql> users = userRepoSql.findAll();
        users.sort(Comparator.comparing(UserSql::getSuccessRate).reversed());

        List<String> rankUsers = new ArrayList<>();
        int rank = 1;
        for (UserSql user : users) {
            String userRate = " " + user.getSuccessRate() + " % " + user.getName();
            rankUsers.add(rank + "." + userRate);
            rank++;
        }
        return rankUsers;
    }

    @Override
    public String rankingWinner() {
        List<String> rankedUsers = calculateUsersRank(); // Call the calculateUsersRank method to get the sorted user rankings
        String firstRankedUser = null;
        if (!rankedUsers.isEmpty()) {
            firstRankedUser = rankedUsers.get(0);
        }
        return  firstRankedUser;
    }


    //to be modified
    @Override
    public String rankingLooser() {
        List<String> rankedUsers2 = calculateUsersRank(); // Call the calculateUsersRank method to get the sorted user rankings
        String lastRankedUser = null;
        if (!rankedUsers2.isEmpty()) {
            lastRankedUser = rankedUsers2.get(rankedUsers2.size() - 1); // Get the first user in the ranked list
        }
        return  lastRankedUser;
    }
    //to be modified


    //metodos de conversión user
    public UserDtoSql convertUserEntityToDTO(UserSql userSql) {
        return modelMapper.map(userSql, UserDtoSql.class);
    }

    public UserSql convertUserDTOToEntity(UserDtoSql userDtoSql) {
        return modelMapper.map(userDtoSql, UserSql.class);
    }

    //metodos de conversión Game
    public GameDtoSql convertGameEntityToDTO(GameSql gameSql) {
        return modelMapper.map(gameSql, GameDtoSql.class);
    }

    public GameSql convertGameDTOToEntity(GameDtoSql gameDtoSql) {
        return modelMapper.map(gameDtoSql, GameSql.class);
    }

}
