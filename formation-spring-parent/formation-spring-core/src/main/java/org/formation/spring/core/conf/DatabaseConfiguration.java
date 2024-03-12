package org.formation.spring.core.conf;

import org.formation.spring.core.conf.condition.CacheDatabaseConfiguredCondition;
import org.formation.spring.core.persistence.database.CacheDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

	@Bean
	// On ne veut instancier la base de donnée en cache que si on a pas de base de donnée distante disponible
	@Conditional(CacheDatabaseConfiguredCondition.class)
	public CacheDatabase database() {
		return new CacheDatabase();
	}

}
