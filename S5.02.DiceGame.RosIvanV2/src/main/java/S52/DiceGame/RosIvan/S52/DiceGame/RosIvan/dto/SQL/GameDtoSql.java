package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.GameSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.UserSql;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GameDtoSql {

    //Get from Mysql in the userSqls BBDD
    private UserSql userSql;
    private int dice1;
    private int dice2;
    private boolean winner;
    private String timeRegistration;


    @Override
    public String toString() {
        return "GameDtoSql(userSql=" + userSql +
                ", dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", winner=" + winner +
                ", timeRegistration=" + timeRegistration +
                ")";
    }

}
