package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.UserSql;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoSql {

    private Integer userId;

    private String name;
}
