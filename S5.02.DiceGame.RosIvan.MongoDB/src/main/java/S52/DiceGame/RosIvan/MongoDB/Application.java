package S52.DiceGame.RosIvan.MongoDB;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "DICE GAME", version = "1.0.0"),
		servers = {@Server(url = "http://localhost:9000")},
		tags = {@Tag(name = "Dice Game API", description = "This is an API CRUD to Manage USERS and Game results in MySQL")}

)
public class Application {
	//ModelMapper put Bean
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
