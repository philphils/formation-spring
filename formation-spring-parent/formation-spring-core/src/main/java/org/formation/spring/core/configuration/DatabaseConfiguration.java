package org.formation.spring.core.configuration;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

	@Bean("databaseEnCache")
	public CacheDatabase cacheDatabase() {
		return new CacheDatabase();
	}

}
