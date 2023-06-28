package S52.DiceGame.RosIvan.MongoDB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoMongo {

    private Integer userId;

    private String name;
}
