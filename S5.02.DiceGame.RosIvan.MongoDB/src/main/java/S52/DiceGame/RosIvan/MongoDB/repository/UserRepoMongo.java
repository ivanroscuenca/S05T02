package S52.DiceGame.RosIvan.MongoDB.repository;

import S52.DiceGame.RosIvan.MongoDB.Domain.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepoMongo extends MongoRepository<UserMongo,Integer> {
}
