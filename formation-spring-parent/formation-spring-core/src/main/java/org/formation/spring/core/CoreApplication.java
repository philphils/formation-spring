package org.formation.spring.core;

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

		/*
		 * Il faut créer le context sans classe de config au départ pour pouvoir définir
		 * le profile ensuite. Il est impossible de rafraîchir le contexte sinon on
		 * obtiendrait une : IllegaleStateException.
		 */
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.getEnvironment().setActiveProfiles("prod");

		context.register(CoreApplication.class);

		context.refresh();

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
