package S52.DiceGame.RosIvan.MongoDB.service;


import S52.DiceGame.RosIvan.MongoDB.Domain.GameMongo;
import S52.DiceGame.RosIvan.MongoDB.Domain.UserMongo;
import S52.DiceGame.RosIvan.MongoDB.dto.GameDtoMongo;
import S52.DiceGame.RosIvan.MongoDB.dto.UserDtoMongo;
import S52.DiceGame.RosIvan.MongoDB.repository.GameRepoMongo;
import S52.DiceGame.RosIvan.MongoDB.repository.UserRepoMongo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ImplServiceMongo implements ServiceMongo {

    @Autowired
    private GameRepoMongo gameRepoMongo;
    @Autowired
    private UserRepoMongo userRepoMongo;
    @Autowired
    private ModelMapper modelMapper;

    //Users
    @Override
    public List<UserDtoMongo> findAllUsers() {
        return userRepoMongo.findAll()
                .stream()
                .map(this::convertUserEntityToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public Optional<List<GameMongo>> findGamesByUserId(Integer id) {
        List<GameMongo> games = gameRepoMongo.findAllByUserSqlId(id);
        return Optional.of(games);
    }


    //done
    @Override
    public void createUser(UserDtoMongo userDtoMongo) {
        userRepoMongo.save(modelMapper.map(userDtoMongo, UserMongo.class));

    }

    @Override
    public void updateUser(UserDtoMongo userDtoMongo ) {
        UserMongo  userMongo  = convertUserDTOToEntity(userDtoMongo);
        userRepoMongo.save(userMongo);
    }

    @Override
    public UserDtoMongo findUserById(Integer id) {
        Optional<UserMongo> optionalUser = userRepoMongo.findById(id);
        UserDtoMongo userDtoMongo = null;
        if (optionalUser.isPresent()) {
            userDtoMongo = convertUserEntityToDTO(optionalUser.get());
        }
        return userDtoMongo;
    }


    @Override
    public GameDtoMongo createGame(Integer id) {
        Optional<UserMongo> optionalUser = userRepoMongo.findById(id);
        GameMongo newGame = null;
        if (optionalUser.isPresent()) {
            UserMongo userMongo = optionalUser.get();
            newGame = new GameMongo(userMongo);
            //userSql add to list
            userMongo.addGamesToListGames(newGame);
            gameRepoMongo.save(newGame);
        }
        return convertGameEntityToDTO(newGame);
    }


    @Override
    public void deleteGamesByUserId(Integer id) {
        Optional<UserMongo> optionalUser = userRepoMongo.findById(id);
        if (optionalUser.isPresent()) {
            UserMongo userSelected = optionalUser.get();
            userSelected.deleteFromListGames();
            userRepoMongo.save(userSelected);
        }

    }


//Games

    @Override
    public List<GameDtoMongo> rankingAll() {
        return gameRepoMongo.findAll()
                .stream()
                .map(this::convertGameEntityToDTO)
                .collect(Collectors.toList());

    }

    public List<String> calculateUsersRank() {
        List<UserMongo> users = userRepoMongo.findAll();
        users.sort(Comparator.comparing(UserMongo::getSuccessRate).reversed());

        List<String> rankUsers = new ArrayList<>();
        int rank = 1;
        for (UserMongo user : users) {
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
    public UserDtoMongo convertUserEntityToDTO(UserMongo userMongo) {
        return modelMapper.map(userMongo, UserDtoMongo.class);
    }

    public UserMongo convertUserDTOToEntity(UserDtoMongo userDtoMongo) {
        return modelMapper.map(userDtoMongo, UserMongo.class);
    }

    //metodos de conversión Game
    public GameDtoMongo convertGameEntityToDTO(GameMongo gameMongo) {
        return modelMapper.map(gameMongo, GameDtoMongo.class);
    }

    public GameMongo convertGameDTOToEntity(GameDtoMongo gameDtoMongo) {
        return modelMapper.map(gameDtoMongo, GameMongo.class);
    }

}
