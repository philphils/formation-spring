package org.formation.spring.core.configuration;

import java.util.Random;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class GenerationModelConfiguration {

	@Bean
	public RandomModelGenerator randomModelGenerator(Faker faker, Random random) {
		return new RandomModelGenerator(new Faker(), new Random());
	}

	@Bean
	public Faker faker() {
		return new Faker();
	}

	@Bean
	public Random random() {
		return new Random();
	}

}