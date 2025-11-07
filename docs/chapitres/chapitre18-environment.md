# Environnement abstraction
--
# Spring properties

* __Spring offre un mécanisme de gestion des configurations et des properties__ 

* __Permet de centraliser les properties de différentes sources__ 

* __Rendre disponibles simplement les properties à tous les beans du container__ 

--
# Spring properties

* __Propose un mécanisme souple de gestion des différents environnements \(prod\, dev\, test\)__ 

* __Offre d’autres fonctionnalités comme la gestion de clés cryptées__ 

--

# Environment Abstraction

--
# Property Source

* __Le container Spring contient un objet Environnement__ 

* __Spring propose de centraliser au sein de cet objet les properties provenant de différentes sources__ 

* __On pourra récupérer les propriétés à partir de l’objet Environment :__ 

```java
Environment environment = context.getEnvironment();
environment.getProperty("ma.property");
```

--
# Spring Boot

* __Sans Spring Boot\, il est nécessaire de charger explicitement les sources de properties__ 

* __Spring Boot charge lui automatiquement les propriétés provenant de différentes sources__ 

--
# Spring Boot

* __Les sources sont chargés avec un ordre de priorité__ 

* __Les variables système \(ligne de commande\, JVM\, variables d’environnement\) ont priorité sur les properties au sein des fichiers__ 

* __Si une variable est redéfinie par l’environnement d’exécution\, elle écrase la valeur de la property interne__ 

--

# Schéma Property Source

![](./img/diapo_formation_spring_property_1.png)
