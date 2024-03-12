package org.formation.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// On fait le scan des annotations de spring context dans le package suivant
@ComponentScan(basePackages = "org.formation.spring.core")
// Fichier de properties par défaut spring boot, ici on utilise que spring donc on va le chercher avec cette annotation
@PropertySource("classpath:fr/insee/config/application.properties")
public class CoreApplication {

	public static ApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = new AnnotationConfigApplicationContext(CoreApplication.class);

		for (String beanName : applicationContext.getBeanDefinitionNames()) {
			System.out.println("bean name : " + beanName);
			System.out.println("bean type : " + applicationContext.getType(beanName));
		}
	}
}
