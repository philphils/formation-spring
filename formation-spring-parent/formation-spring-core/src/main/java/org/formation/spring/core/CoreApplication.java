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
public class CoreApplication {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CoreApplication.class);

		for (String beanName : context.getBeanDefinitionNames()) {

			System.out.println("Nom du bean : " + beanName);
			System.out.println("Type du bean : " + context.getBean(beanName).getClass());

		}

	}

}
