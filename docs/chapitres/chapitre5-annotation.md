# Création des beans
# avec annotations


--
# Principe

* __Idée de Spring au départ : être le moins intrusif possible__

* __L’externalisation dans des fichiers XML permet de ne pas polluer le code__

* __Problème : XML verbeux\, pas ou peu d’autocomplétion\, on perd la souplesse de Java…__

--
# Principe

* __Finalement apparition des annotations__

* __Ajout de « méta\-données » ie code non\-exécutable__

* __Même cheminement du côté Hibernate__

--
# Une annotation principale : @Component

* __L’annotation `@Component` est l’annotation principale pour la déclaration de beans__
* __Elle se place au niveau de la déclaration de la classe__
* __On indique ainsi à Spring de créer un bean à partir de cette classe__

--
# Une annotation principale : @Component

* __Spring choisit le constructeur de cette manière :__
  * __Pas de constructeur → Choix du constructeur vide__
  * __Constructeur unique → Choix de ce constructeur \(depuis 4\.3\)__
  * __Plusieurs constructeurs → Nécessité de choisir un des constructeurs avec l’annotation `@Autowired`__   
* __Les paramètres du constructeur seront renseignés avec d’autres beans\, par « auto\-wiring » \(expliqué plus loin\)__

--
# @Component et @Value

* __Par défaut le nom du bean est le nom de la classe__
* __On peut le spécifier via `@Component("nomDuBean")`__
* __On pourra valoriser les attributs de type basique \(Integer\, String…\) avec l’annotation `@Value`__

--
# @Component et @Value

* __Possibilité de passer des valeurs « en dur » \, ex :__
  ```java
    @Value("12")
    private Integer nbSallesDispo;
  ```
* __Plus souvent\, on récupère des properties avec le langage SpeL \(cf partie Properties\)\, ex :__
  ```java
    @Value("${batch.nomFichier}")
    private String nomFichier;
  ```

--
# Les dérivés de @Component

* __Spring propose plusieurs dérivés de cette annotation\, appelés « stéréotypes » :__
  *  __`@Service`: Pour les composants de la couche service__
  *  __`@Repository` : Pour les DAO\, par ex\. avec Spring\-Data__
  *  __`@Controller` : Pour la présentation\, par ex\. Spring\-MVC__
  *  __`@RestController` : Pour les classes exposant une API__

--
# Les dérivés de @Component

* __Permettent de typer plus fortement les beans créés__
* __Permettent de mettre en place des comportements différenciés__
* __Ex: avec `@Repository` certaines exceptions JDBC sont encapsulées dans des exceptions Spring pour une meilleure lisibilité__

--
# Exemple simpliste

* __Exemple \(sans injection de dépendances\) :__
```java
  @Service
  public class ReservationSalleServiceImpl 
        implements ReservationSalleService {

    @Value("12")
    private Integer nbSallesDispo;

    //	Suite des properties, constructeurs, méthodes…
```
--
# Exemple simpliste

* __Crée un bean\, ie une instance de la classe `ReservationSalleServiceImpl`\, au sein du container nommé `reservationSalleServiceImpl`__

* __Si on veut modifier le nom du bean : `@Service("beanReservService")`__ 


--
# Component scan en XML

* __Une fois les composants annotés\, il faut activer la détection des annotations__

* __En XML\, on active la détection avec `component-scan` :__

```xml
  <context:component-scan base-package="fr.insee.appli.services" />
```

--
# Component scan par annotation

* __Avec les annotations\, on active la détection au sein d’une classe annotée `@Configuration` (cf plus loin) avec l’annotation `@ComponentScan` :__

```java
@Configuration
@ComponentScan("fr.insee.appli.services") 
public class Application{ … } 
```

* __On peut cibler des packages assez haut dans l’arborescence que Spring parcourt recursivement__