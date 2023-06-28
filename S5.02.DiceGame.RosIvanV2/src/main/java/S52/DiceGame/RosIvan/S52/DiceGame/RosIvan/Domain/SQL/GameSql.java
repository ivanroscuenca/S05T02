package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Random;

@Entity
@Table(name="GameDice")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GameSql {
    //for MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gameId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL , optional = false)
    @JoinColumn(name = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @Getter
    @Setter
    private UserSql userSql;

    @Column (name="Dice1")
    private int dice1=generateRandomNumber();
    @Column (name="Dice2")
    private int dice2=generateRandomNumber();
    @Column (name="Result")
    private ResultGame resultGame;
    @Column (name="Registration")
    private Date timeRegistration= new Date();

    enum ResultGame {
        WINNER,LOSER
    }

    //Constructor when creates a new game
    public GameSql(UserSql userSql) {
        this.userSql = userSql;
        resultDices();
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
    private void resultDices(){
    if(dice1+dice2==7){
        this.resultGame = ResultGame.WINNER;
        } else {
            this.resultGame = ResultGame.LOSER;
        }
    }

    @Override
    public String toString() {
        return "GameSql{" +
                "gameId=" + gameId +
                ", userSql=" + userSql +
                ", dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", resultGame=" + resultGame +
                ", timeRegistration=" + timeRegistration +
                '}';
    }

}
