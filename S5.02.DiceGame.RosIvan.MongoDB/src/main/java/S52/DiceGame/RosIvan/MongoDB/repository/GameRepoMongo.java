package S52.DiceGame.RosIvan.MongoDB.repository;

import S52.DiceGame.RosIvan.MongoDB.Domain.GameMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface GameRepoMongo extends MongoRepository<GameMongo,Integer> {

    public List<GameMongo> findAllByUserSqlId(Integer userId) ;



}
