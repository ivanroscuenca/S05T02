package S52.DiceGame.RosIvan.MongoDB.Domain;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "UserDice")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMongo {
    @Id
    private String id;

    @Field("Name")
    @Builder.Default
    private String name = "Anonymous";

    @Builder.Default
    private double successRate = 0.00;

    @DBRef
    private List<GameMongo> listGames;

    public UserMongo() {
        this.listGames = new ArrayList<>();
    }

    public void addGamesToListGames(GameMongo gameMongo) {
        listGames.add(gameMongo);
        setSuccessRate();
    }

    public void deleteFromListGames() {
        listGames.clear();
    }

    public void setSuccessRate() {
        double wins = 0.00;
        for (GameMongo gameMongo : listGames) {
            if (gameMongo.getResultGame() == GameMongo.ResultGame.WINNER) {
                wins++;
            }
        }
        double winRate = (double) Math.round(wins / listGames.size() * 100.0) / 100.0;
        this.successRate = winRate;
    }

    public String getName() {
        if (name == null || name.isEmpty() || name.equalsIgnoreCase("string")) {
            return "anonymous";
        }
        return name;
    }
}