package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.controller.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.GameSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.GameDtoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.UserDtoSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.service.SQL.ServiceSql;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/")
public class GameControllerSql {
    @Autowired
    private ServiceSql serviceSql;

    // POST: /players: crea un jugador/a.
    @PostMapping({"players","players/"})
    @Operation(
            tags = "POST",
            operationId = "ADD",
            summary = "This action to add new userSqls",
            description = "Please add a userSql and fill the parameters required in Requestbody.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "This the requestbody Description",
                    content = @Content(schema = @Schema(implementation = UserDtoSql.class))
            ),
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> saveUser(@RequestBody UserDtoSql userDtoSql) {
        try {
            serviceSql.createUser(userDtoSql);
            return ResponseEntity.ok("UserSql saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save userSql.");
        }
    }


// PUT /players: modifica el nom del jugador/a.
    @PutMapping({"players","players/"})
    @Operation(
            tags="UPDATE",
            summary = "This action to update an userSql",
            description = "Please update an userSql and fill the parameters required in Requestbody : name",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This the requestbody Description",
                    content = @Content(schema = @Schema(implementation = UserDtoSql.class))),
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> updateUser(
            @RequestBody UserDtoSql userDtoSql) {
        try {
            serviceSql.updateUser(userDtoSql);
            return ResponseEntity.ok("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update userSql: " + e.getMessage());
        }
    };

    //OK
//            POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.
//http://localhost:9000/players/{id}/games
    @PostMapping({"players/{id}/games","players/{id}/games/"})
    @Operation(
            tags="POST",
            operationId="PLAY A GAME",
            summary = "This action to play a game",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> PlayGame(@PathVariable Integer id) {
        try {
            UserDtoSql userDtoSql = serviceSql.findUserById(id);
            serviceSql.createGame(userDtoSql.getUserId());
            return ResponseEntity.ok("User searched properly");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to search this id user: " + e.getMessage());
        }
    };


//OK
//            DELETE /players/{id}/games: elimina les tirades del jugador/a.

    @DeleteMapping ({"players/{id}/games","players/{id}/games/"})
    @Operation(
            tags="DELETE",
            operationId="DELETE GAMES OF A PLAYER",
            summary = "This action to delete games of players",
            description = "Please check id and delete all the games of a player",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> GetIdAndDeleteGames(@PathVariable Integer id) {

        try {
            serviceSql.deleteGamesByUserId(id);
            return ResponseEntity.ok("User searched properly");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to search this id user: " + e.getMessage());
        }
    };
//OK
//            GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu percentatge mitjà d’èxits.

    @GetMapping ({"players","players/"})
    @Operation(
            tags="GET",
            operationId="RETURN LIST OF PLAYERS",
            summary = "This action to get list of players",
            description = "Please see the list",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<List<UserDtoSql>> getAllPlayers(){
        List<UserDtoSql> userDtoSqls = serviceSql.findAllUsers();
        if (!userDtoSqls.isEmpty()) {
            return new ResponseEntity<>(userDtoSqls, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };
//WRONG
            //GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
    @GetMapping ({"players/{id}/games","players/{id}/games/"})
    @Operation(
            tags="GET",
            operationId="RETURN LIST OF ALL GAMES FROM A PLAYER",
            summary = "This action to get list of games from a player",
            description = "Please see the list of games",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<List<GameSql>> getUserGamesById(@PathVariable Integer id) {
    Optional<List<GameSql>> optionalGames = serviceSql.findGamesByUserId(id);
        if (optionalGames.isPresent()) {
            return new ResponseEntity<>(optionalGames.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

//            GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
@GetMapping({"players/ranking", "players/ranking/"})
@Operation(
        tags = "GET",
        operationId = "RETURN LIST OF ALL PLAYERS ACCORDING RANKING",
        summary = "This action to get list of players ordered by score",
        description = "Please see the list of players",
        security = {@SecurityRequirement(name = "bearerAuth")}
)
public ResponseEntity<List<String>> getUsersRanking() {
    try {
        List<String> ranking = serviceSql.calculateUsersRank();
        if (ranking.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}



//            GET /players/ranking/winner: retorna el jugador/a  amb millor percentatge d’èxit.
    @GetMapping({"players/ranking/winner", "players/ranking/winner/"})
    @Operation(
            tags = "GET",
            operationId = "GET USER WINNER",
            summary = "Get the winner of the game",
            description = "Retrieve the username of the game winner",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    public ResponseEntity<String> getUserWinner() {
        try {
            String winner = serviceSql.rankingWinner();
            if (winner == null) {
                return new ResponseEntity<>("No winner found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("The winner is: " + winner, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//            GET /players/ranking/loser: retorna el  jugador amb pitjor percentatge d’èxit.
@GetMapping({"players/ranking/loser", "players/ranking/loser/"})
@Operation(
        tags = "GET",
        operationId = "GET USER LOSER",
        summary = "Get the loser of the game",
        description = "Retrieve the username of the game winner",
        security = {@SecurityRequirement(name = "bearerAuth")}
)
public ResponseEntity<String> getUserLoser() {
    try {
        String loser = serviceSql.rankingLooser();
        if (loser == null) {
            return new ResponseEntity<>("No loser found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("The loser is: " + loser, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


}