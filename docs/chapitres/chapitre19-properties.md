# Récupération des Properties

--
# `@PropertySource`

* __L’annotation `@PropertySource` permet de charger le contenu d’un fichier de properties__ 

* __Ces properties seront ensuite disponibles pour l’ensemble des beans Spring__ 

* __On pourra cibler le fichier avec `classpath:chemin/du/fichier`__ 

--
# `@PropertySource`

* __Cette annotation permet d’intégrer des fichiers de type properties ou yaml__ 

* __Ex : `@PropertySource(
            "classpath:application.properties")`__ 

--
# @Value

* __Il est ensuite possible de récupérer les properties au sein des beans avec `@Value`__

* __Cette annotation s’utilise au niveau attribut__

* __On fait référence aux properties avec `${...}`__

--
# @Value

* __Ex :__ 

```java
@Value("${ma.propriete}")
private String valeurPropriete;
```

* __Elle supporte le langage SpEL qui permet de définir des expressions régulières (Regex)__ 

* __On peut ainsi définir des valeurs par défaut\, ou des valeurs conditionnelles etc\. Ex :__
```java
@Value("${ma.propriete ? ‘La prop est dispo !’ 
        : ‘Ouuu La prop est pas dispo’}")
```

--
# `@ConfigurationProperties`

* __Spring Boot offre un mécanisme intéressant pour injecter un ensemble de propriété dans un bean en plus de `@Value`__ 

* __Il faut créer un bean et l’annoter avec `@ConfigurationProperties`__

--
# `@ConfigurationProperties`

* __Les noms des attributs de ce bean doivent correspondre au nom des properties__ 

* __Les tirets\, underscores\, points doivent être remplacés par du camelCase__ 

* __Ex : ma.super.property → maSuperProperty__ 

--
# `@ConfigurationProperties`

* __Ex :__

```java
@ConfigurationProperties(prefix="database.connection")
public class DatabaseProperties {
    private String username;
    private String password;
...}
```

* __Les attributs du bean sont alimentés automatiquement avec les properties disponibles__ 

--
# `@ConfigurationProperties`

* __Le prefix permet de cibler un ensemble de properties lié à un domaine de l’appli \(bdd\, batch…\)__ 

* __On déclare ensuite la classe comme un bean\, ou bien avec `@EnableConfigurationProperties(value = DatabaseProperties.class)`__ 
