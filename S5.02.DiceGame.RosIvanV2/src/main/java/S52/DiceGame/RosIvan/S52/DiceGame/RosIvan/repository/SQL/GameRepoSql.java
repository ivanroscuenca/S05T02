package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.repository.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.GameSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.UserSql;
import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.dto.SQL.GameDtoSql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GameRepoSql extends JpaRepository<GameSql,Integer> {

    public List<GameSql> findAllByUserSqlId(Integer userId) ;



}
