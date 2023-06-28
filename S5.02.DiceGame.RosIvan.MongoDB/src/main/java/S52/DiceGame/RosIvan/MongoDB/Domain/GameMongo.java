package S52.DiceGame.RosIvan.MongoDB.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.Date;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GameDice")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GameMongo {
    @Id
    private String gameId;

    @DBRef
    @JsonIgnore
    @Getter
    @Setter
    private UserMongo userMongo;

    private int dice1 = generateRandomNumber();
    private int dice2 = generateRandomNumber();
    private ResultGame resultGame;
    private Date timeRegistration = new Date();

    enum ResultGame {
        WINNER, LOSER
    }

    public GameMongo(UserMongo userMongo) {
        this.userMongo = userMongo;
        resultDices();
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    private void resultDices() {
        if (dice1 + dice2 == 7) {
            this.resultGame = ResultGame.WINNER;
        } else {
            this.resultGame = ResultGame.LOSER;
        }
    }
}
