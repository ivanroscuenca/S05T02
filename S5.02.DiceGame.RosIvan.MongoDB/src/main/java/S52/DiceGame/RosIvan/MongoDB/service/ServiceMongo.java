package S52.DiceGame.RosIvan.MongoDB.service;

import S52.DiceGame.RosIvan.MongoDB.Domain.GameMongo;
import S52.DiceGame.RosIvan.MongoDB.dto.GameDtoMongo;
import S52.DiceGame.RosIvan.MongoDB.dto.UserDtoMongo;


import java.util.List;
import java.util.Optional;


public interface ServiceMongo {

    //POST: /players: crea un jugador/a.
    void createUser(UserDtoMongo userDtoMongo);

    //PUT /players: modifica el nom del jugador/a.
    void updateUser(UserDtoMongo userDtoMongo);

    //POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.

    UserDtoMongo findUserById(Integer id);

    GameDtoMongo createGame(Integer id);

//DELETE /players/{id}/games: elimina les tirades del jugador/a.
    void deleteGamesByUserId(Integer id);

//            GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits.
    List<UserDtoMongo> findAllUsers();

//            GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
//use first UserDtoSql findUserById(Integer id);
    Optional<List<GameMongo>> findGamesByUserId(Integer id);


//            GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
List<GameDtoMongo> rankingAll();
    List<String> calculateUsersRank();
//            GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.
        String rankingLooser();
//            GET /players/ranking/winner: retorna el jugador amb millor percentatge d’èxit.
        String rankingWinner();

}
