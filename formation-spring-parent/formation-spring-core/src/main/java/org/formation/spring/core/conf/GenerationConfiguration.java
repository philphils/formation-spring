package org.formation.spring.core.conf;

import java.util.Locale;
import java.util.Random;

import org.formation.spring.core.persistence.model.generator.ModelGenerator;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.github.javafaker.Faker;

@Configuration
public class GenerationConfiguration {

	@Bean
	// On souhaite s'assurer que ce Bean sera généré après les beans dépendances
	// Ici c'est inutile, Spring est capable d'instancier dans l'ordre des dépendaces
	@DependsOn({ "faker", "random" })
	public ModelGenerator modelGenerator(Faker faker, Random random) {
		return new RandomModelGenerator(faker, random);
	}

	@Bean
	public Faker faker() {
		return new Faker(Locale.FRANCE);
	}

	@Bean
	public Random random() {
		return new Random();
	}

}
