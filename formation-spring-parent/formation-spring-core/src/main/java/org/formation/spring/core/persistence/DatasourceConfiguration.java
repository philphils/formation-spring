package org.formation.spring.core.persistence;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Configuration
public class DatasourceConfiguration {

	@Value("${fr.insee.database.database_pool.url}")
	private String url;

	@Value("${fr.insee.database.database_pool.driverClassName}")
	private String driverClassName;

	@Value("${fr.insee.database.database_pool.username}")
	private String username;

	@Value("${fr.insee.database.database_pool.password}")
	private String password;

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	@Bean
	@Profile("prod")
	public BasicDataSource basicDataSource() {

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setDriverClassName(driverClassName);
		basicDataSource.setPassword(password);

		return basicDataSource;

	}

}
