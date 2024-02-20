package org.formation.spring.core.conf;

import java.util.Locale;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class GenerationConfiguration {

	@Bean
	public Faker faker() {
		return new Faker(Locale.FRANCE);
	}

	@Bean
	public Random random() {
		return new Random();
	}
}
