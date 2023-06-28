package S52.DiceGame.RosIvan.MongoDB.controller;


import S52.DiceGame.RosIvan.MongoDB.Domain.GameMongo;
import S52.DiceGame.RosIvan.MongoDB.dto.UserDtoMongo;
import S52.DiceGame.RosIvan.MongoDB.service.ServiceMongo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/players")
public class GameControllerSql {
@Autowired
    private ServiceMongo serviceMongo;

//OK
//    POST: /players: crea un jugador/a.
    //http://localhost:9000/players/
    @PostMapping({"","/"})
    @Operation(
            tags="POST",
            operationId="ADD",
            summary = "This action to add new userSqls",
            description = "Please add a userSql and fill the parameters required in Requestbody.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This the requestbody Description",
                    content = @Content(schema = @Schema(implementation = UserDtoMongo.class)))
    )
    public ResponseEntity<String> saveUser(@RequestBody UserDtoMongo userDtoMongo) {
        try {
            serviceMongo.createUser(userDtoMongo);
            return ResponseEntity.ok("User saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save user.");
        }
    };

//OK
    //http://localhost:9000/players/
// PUT /players: modifica el nom del jugador/a.
    @PutMapping({"","/"})
    @Operation(
            tags="UPDATE",
            summary = "This action to update an userSql",
            description = "Please update an userSql and fill the parameters required in Requestbody : name",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This the requestbody Description",
                    content = @Content(schema = @Schema(implementation = UserDtoMongo.class)))
    )
    public ResponseEntity<String> updateUser(
            @RequestBody UserDtoMongo userDtoMongo) {
        try {
            serviceMongo.updateUser(userDtoMongo);
            return ResponseEntity.ok("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update user: " + e.getMessage());
        }
    };

    //OK
//            POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.
//http://localhost:9000/players/{id}/games
    @PostMapping({"/{id}/games","/{id}/games/"})
    @Operation(
            tags="POST",
            operationId="PLAY A GAME",
            summary = "This action to play a game"
    )
    public ResponseEntity<String> PlayGame(@PathVariable Integer id) {
        try {
            UserDtoMongo userDtoMongo = serviceMongo.findUserById(id);
            serviceMongo.createGame(userDtoMongo.getUserId());
            return ResponseEntity.ok("User searched properly");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to search this id user: " + e.getMessage());
        }
    };


//OK
//            DELETE /players/{id}/games: elimina les tirades del jugador/a.

    @DeleteMapping ({"/{id}/games","/{id}/games/"})
    @Operation(
            tags="DELETE",
            operationId="DELETE GAMES OF A PLAYER",
            summary = "This action to delete games of players",
            description = "Please check id and delete all the games of a player"
    )
    public ResponseEntity<String> GetIdAndDeleteGames(@PathVariable Integer id) {

        try {
            serviceMongo.deleteGamesByUserId(id);
            return ResponseEntity.ok("User searched properly");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to search this id user: " + e.getMessage());
        }
    };
//OK
//            GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu percentatge mitjà d’èxits.

    @GetMapping ({"","/"})
    @Operation(
            tags="GET",
            operationId="RETURN LIST OF PLAYERS",
            summary = "This action to get list of players",
            description = "Please see the list"
    )
    public ResponseEntity<List<UserDtoMongo>> getAllPlayers(){
        List<UserDtoMongo> userDtoMongos = serviceMongo.findAllUsers();
        if (!userDtoMongos.isEmpty()) {
            return new ResponseEntity<>(userDtoMongos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };
//WRONG
            //GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
    @GetMapping ({"/{id}/games","/{id}/games/"})
    @Operation(
            tags="GET",
            operationId="RETURN LIST OF ALL GAMES FROM A PLAYER",
            summary = "This action to get list of games from a player",
            description = "Please see the list of games"
    )
    public ResponseEntity<List<GameMongo>> getUserGamesById(@PathVariable Integer id) {
    Optional<List<GameMongo>> optionalGames = serviceMongo.findGamesByUserId(id);
        if (optionalGames.isPresent()) {
            return new ResponseEntity<>(optionalGames.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

//            GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
@GetMapping({"/ranking", "/ranking/"})
@Operation(
        tags = "GET",
        operationId = "RETURN LIST OF ALL PLAYERS ACCORDING RANKING",
        summary = "This action to get list of players ordered by score",
        description = "Please see the list of players"
)
public ResponseEntity<List<String>> getUsersRanking() {
    try {
        List<String> ranking = serviceMongo.calculateUsersRank();
        if (ranking.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}



//            GET /players/ranking/winner: retorna el jugador/a  amb millor percentatge d’èxit.
    @GetMapping({"/ranking/winner", "/ranking/winner/"})
    @Operation(
            tags = "GET",
            operationId = "GET USER WINNER",
            summary = "Get the winner of the game",
            description = "Retrieve the username of the game winner"
    )
    public ResponseEntity<String> getUserWinner() {
        try {
            String winner = serviceMongo.rankingWinner();
            if (winner == null) {
                return new ResponseEntity<>("No winner found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("The winner is: " + winner, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//            GET /players/ranking/loser: retorna el  jugador amb pitjor percentatge d’èxit.
@GetMapping({"/ranking/loser", "/ranking/loser/"})
@Operation(
        tags = "GET",
        operationId = "GET USER LOSER",
        summary = "Get the loser of the game",
        description = "Retrieve the username of the game winner"
)
public ResponseEntity<String> getUserLoser() {
    try {
        String loser = serviceMongo.rankingLooser();
        if (loser == null) {
            return new ResponseEntity<>("No loser found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("The loser is: " + loser, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


}