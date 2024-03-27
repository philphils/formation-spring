package org.formation.spring.core.persistence.database;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Configuration
public class SQLDatabaseConfiguration {

	@Value("${fr.insee.database.database_pool.driverClassName}")
	private String driverClassName;

	@Value("${fr.insee.database.database_pool.url}")
	private String urlConnexion;

	@Value("${fr.insee.database.database_pool.username}")
	private String username;

	@Value("${fr.insee.database.database_pool.password}")
	private String password;

	@Bean
	@Profile("prod")
	public DataSource basicDataSource() {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(urlConnexion);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;

	}

}
