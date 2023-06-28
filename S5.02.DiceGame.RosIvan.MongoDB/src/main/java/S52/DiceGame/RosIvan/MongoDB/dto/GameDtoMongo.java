package S52.DiceGame.RosIvan.MongoDB.dto;

import S52.DiceGame.RosIvan.MongoDB.Domain.UserMongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDtoMongo {

    //Get from Mysql in the userSqls BBDD
    private UserMongo userMongo;
    private int dice1;
    private int dice2;
    private boolean winner;
    private String timeRegistration;

}
