package org.formation.spring.core;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("org.formation.spring.core")
@PropertySource("classpath:fr/insee/config/database.properties")
public class CoreApplication {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(CoreApplication.class);

		for (String beanName : context.getBeanDefinitionNames()) {

			System.out.println("Nom du bean : " + beanName);
			System.out.println("Type du bean : " + context.getBean(beanName).getClass());

		}

		Environment environment = context.getEnvironment();

		System.out.println(
				"L'url de connexion à la base est : " + environment.getProperty("fr.insee.database.database_pool.url"));

		System.out.println("Les profiles actifs sont : " + String.join(";", environment.getActiveProfiles()));
	}

}
