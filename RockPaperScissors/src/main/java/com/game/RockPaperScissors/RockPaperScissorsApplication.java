package com.game.RockPaperScissors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Entry point to the RockPaperScissorsApplication}
 *
 * @author Niclas Fölster
 *
 */

@SpringBootApplication
public class RockPaperScissorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockPaperScissorsApplication.class, args);
	}

}
