package org.formation.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		// Pour activer un profile particulier
		context.getEnvironment().setActiveProfiles("prod");

		context.register(CoreApplication.class);

		context.refresh();

		for (String beanName : context.getBeanDefinitionNames()) {

			System.out.println("Nom du bean : " + beanName);
			System.out.println("Type du bean : " + context.getBean(beanName).getClass());

		}

		Environment environment = ((ConfigurableApplicationContext) context).getEnvironment();

		System.out.println("La properties fr.insee.database.database_pool.url vaut :"
				+ environment.getProperty("fr.insee.database.database_pool.url"));

	}

}
