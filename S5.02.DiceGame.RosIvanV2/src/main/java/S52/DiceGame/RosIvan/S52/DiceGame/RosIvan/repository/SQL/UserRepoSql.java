package S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.repository.SQL;

import S52.DiceGame.RosIvan.S52.DiceGame.RosIvan.Domain.SQL.UserSql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepoSql extends JpaRepository<UserSql,Integer> {
}
