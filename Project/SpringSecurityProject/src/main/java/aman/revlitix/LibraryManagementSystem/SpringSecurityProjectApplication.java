package aman.revlitix.LibraryManagementSystem;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringSecurityProjectApplication {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProjectApplication.class, args);
	}

}
