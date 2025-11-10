# La création du contexte

--
# La porte d’entrée du framework

* __On a vu comment créer un contexte selon le mode de configuration choisi__

* __Reste à voir les pratiques concrètes mises en œuvre__

--
# La porte d’entrée du framework

* __Il s’agit du mécanisme permettant à l’application de charger les beans Spring à son démarrage__

* __Plusieurs méthodes selon le type d’application \(web\, batch\)__

* __On suppose ici qu’on utilise pas Spring\-Boot__

--
# Config XML en mode Web

* __Avec une config en XML en mode web\, on pourra initialiser le container avec un listener proposé par la librairie Spring\-Web__
* __Dans le fichier web\.xml :__

```xml
<listener>
  <listener-class>
    org.springframework.web.context.ContextLoaderListener
  </listener-class>
</listener>
```

--
# Config XML en mode Web

* __On renseigne ensuite la localisation du fichier :__

```xml
<context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring.xml</param-value>
</context-param>
```

--
# Config Java en mode Web

* __Si on a une config via annotation/Java\-Based\, on pourra utiliser le mécanisme des `WebApplicationInitializer`__

* __Permettent d’initialiser des composants de configuration en Java\, sans passer par le fichier web\.xml__


--
# Config Java en mode Web

* __Ex\. en supposant qu’on a une classe `Application` annotée `@Configuration`:__
```java
public class MyWebAppInitializer implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext context 
        = new AnnotationConfigWebApplicationContext();
    context.register(Application.class);
    context.setServletContext(servletContext);
    ServletRegistration.Dynamic servlet 
        = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/");
  }
}
```

--
# Config Java en mode batch

* __En mode batch \(ou en mode embarqué\) une pratique répandue est de déclarer la classe portant la méthode « main » comme un bean__

* __On pourra lui faire porter les annotations de configuration permettant la création du contexte Spring et autres__

--
# Config Java en mode batch

* __Ex\. :__
```java
@Configuration
@ComponentScan("org.formation.spring.core")
@EnableBatchProcessing //Permet la création des composants Spring-Batch
public class Application {
  public static void main(String[] args) {
    ApplicationContext context = 
        new AnnotationConfigApplicationContext(Application.class);
    ... //Suite traitement (ex. lancement des batchs ...)
  }
}
```

--
# Config avec Spring Boot

* __Avec Spring Boot c’est plus simple :__

```java
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
```

* __Cette annotation déclenche le scan du package qui contient la classe et ses sous packages récursivement__


--
# Création du contexte pour les tests

* __Pour les tests unitaires\, on souhaitera aussi pouvoir récupérer les beans du contexte Spring__
* __Plusieurs annotations permettent de recréer le contexte :__
    * __Avec JUnit 4 :__
      ```java
      @RunWith(SpringRunner.class)
      @ContextConfiguration(classes=Application.class)
      ```
    * __Avec JUnit 5:__
      ```java
      @ExtendWith(SpringExtension.class)
      @ContextConfiguration(classes=Application.class)
      ```

--
# Création du contexte pour les tests

* __En JUnit 4\, on utilisera l’injection par attribut plutôt que celle par constructeur en raison de limitations JUnit :__
```java
@Autowired
private ServiceATester serviceATester;
```



--
# Question

* __On a vu comment créer des beans dans le container avec différentes méthodes :__
  * __En XML avec une configuration dans des fichiers externalisés__
  * __Via annotations en ajoutant des méta\-données au code__
  * __En mode Java\-based\, en annotant des méthodes qui créé des instances d’objets Java__

--
# Question

* __Mais comment récupérer ces instances au sein de nos beans pour les utiliser ?__
* __Oui c’est vrai ça\, comment ??\!…__


![](./img/diapo_formation_spring_9.png)

