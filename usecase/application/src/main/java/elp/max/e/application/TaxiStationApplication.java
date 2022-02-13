package elp.max.e.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"elp.max.e.persistence"})
@EntityScan(basePackages = {"elp.max.e.model"})
@ComponentScan(basePackages = {"elp"})
public class TaxiStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxiStationApplication.class, args);
	}

}
