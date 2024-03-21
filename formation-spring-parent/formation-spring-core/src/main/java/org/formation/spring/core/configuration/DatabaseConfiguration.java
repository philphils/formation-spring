package org.formation.spring.core.configuration;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.formation.spring.core.persistence.model.generator.RandomModelGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

	@Bean("databaseEnCache")
	public CacheDatabase cacheDatabase(RandomModelGenerator randomModelGenerator) {
		return new CacheDatabase(randomModelGenerator);
	}

}
