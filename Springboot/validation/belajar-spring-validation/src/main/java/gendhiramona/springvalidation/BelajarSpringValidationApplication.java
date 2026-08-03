package gendhiramona.springvalidation;

import gendhiramona.springvalidation.properties.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
		DatabaseProperties.class
})
@SpringBootApplication
public class BelajarSpringValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringValidationApplication.class, args);
	}

}
