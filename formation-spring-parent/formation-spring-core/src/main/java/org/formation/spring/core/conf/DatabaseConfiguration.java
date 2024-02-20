package org.formation.spring.core.conf;

import org.formation.spring.core.persistence.database.CacheDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

	@Bean
	public CacheDatabase database() {
		return new CacheDatabase();
	}

}
