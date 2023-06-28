package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserDice")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSql {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Name")
    @Schema(example = "Anonymous")
    private String name;


    @Column(name ="SuccessRate")
    @Builder.Default
    private double SuccessRate = 0.00;

    //list to have all the games of the users
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "UserGame",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<GameSql> listGames ;

    public List<GameSql> getListGames() {
        return listGames;
    }

    public UserSql() {
        this.listGames = new ArrayList<>();

    }

    //function to add all the games in to arraylist
    public void addGamesTolistGames(GameSql gameSql){
        listGames.add(gameSql);
        setSuccessRate();
    }
    //function to delete all the games of one user
    public void deleteFromlistGames(){
        listGames.clear();
    }

    //function to get success rate between wins and loses
    public void setSuccessRate() {
        double wins = 0.00;
        for (GameSql gameSql : listGames) {
            if (gameSql.getResultGame() == GameSql.ResultGame.WINNER) {
                wins++;
            }
        }
        double winRate =(double)Math.round( wins/ listGames.size()*100.0)/100.0;
        this.SuccessRate = winRate;
    }
//function to set anonymos in case a name is not wrote
    public String getName() {
        if (name == null || name.isEmpty() || name.equalsIgnoreCase("string")) {
            return "anonymous";
        }
        return name;
    }

    @Override
    public String toString() {
        return "UserSql{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
