package com.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.play.repository")
public class JPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(JPlaygroundApplication.class, args);
	}

}
