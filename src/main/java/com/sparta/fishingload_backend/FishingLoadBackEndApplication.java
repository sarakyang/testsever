package com.sparta.fishingload_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FishingLoadBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishingLoadBackEndApplication.class, args);
	}

}
