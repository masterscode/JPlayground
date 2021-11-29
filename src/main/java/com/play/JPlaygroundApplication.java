package com.play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories("com.play.repository")
public class JPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(JPlaygroundApplication.class, args);
	}

}
