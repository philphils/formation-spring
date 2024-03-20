package org.formation.spring.core.configuration;

import org.formation.spring.core.persistence.database.CacheDatabase;

public class DatabaseConfiguration {

	public CacheDatabase cacheDatabase() {
		return new CacheDatabase();
	}

}
