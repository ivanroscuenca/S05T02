package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.service.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.GameSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.GameDtoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.UserDtoSql;

import java.util.List;
import java.util.Optional;


public interface ServiceSql {

    //POST: /players: crea un jugador/a.
    void createUser(UserDtoSql userDtoSql);

    //PUT /players: modifica el nom del jugador/a.
    void updateUser(UserDtoSql userDtoSql);

    //POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.

    UserDtoSql findUserById(Integer id);

    GameDtoSql createGame(Integer id);

//DELETE /players/{id}/games: elimina les tirades del jugador/a.
    void deleteGamesByUserId(Integer id);

//            GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits.
    List<UserDtoSql> findAllUsers();

//            GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
//use first UserDtoSql findUserById(Integer id);
    Optional<List<GameSql>> findGamesByUserId(Integer id);


//            GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
List<GameDtoSql> rankingAll();
    List<String> calculateUsersRank();
//            GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.
        String rankingLooser();
//            GET /players/ranking/winner: retorna el jugador amb millor percentatge d’èxit.
        String rankingWinner();

}
