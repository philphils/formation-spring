# Les Profiles : outil 
# pour la gestion
# multi-environnement

--
# Principe

* __Pour une même application\, souvent on a des environnements d’exécution différents__ 

* __Ex : les chaînes de connexion vont changer entre les environnement de dev, qualif, prod ou test__ 

* __On peut avoir carrément des composants différents selon les contextes d’exécution__ 

--
# Principe

* __Ex : base mémoire embarquée pour les tests\, mock pour certaines API en dev et qualif etc\.__ 

* __Spring propose les Profiles pour gérer ces différents environnement et passer simplement de l’un à l’autre__ 

--

# Mise en œuvre avec `@Profile`

* __On rattache un bean à un Profile avec l’annotation `@Profile("nom.du.profile")`__

* __Cette annotation s’utilise sur un bean annoté `@Component` (ou annotation dérivée) ou sur une méthode `@Bean`__ 

* __Le bean ne sera créé que si le profile mentionné par l’annotation est activé \(cf suite\)__ 

--

# Mise en œuvre avec `@Profile`

* __On peut donc charger des fichiers de property ou pas selon le profile actif__ 

* __Spring Boot a lui un fonctionnement natif qui attribue les fichiers à un profil par la convention de nommage : `application-{nom.du.profile}.properties`__ 

--
# Activation des profiles

* __Les profiles peuvent s’activer de diverses manières__ 

* __On peut paramètrer la JVM avec l’argument : `-Dspring.profiles.active=dev`__ 

* __On peut l’activer programmatiquement dès la construction depuis l’objet `AnnotationConfigApplicationContext` :__ 

```java
context.getEnvironment().setActiveProfiles(...);
context.register(//Classes de configuration);
context.refresh();
```

--
# Activation des profiles

* __Avec Spring Boot\, on peut définir et valoriser la variable d’environnement ou la property : `spring.profiles.active`, ou encore passer cette variable en argument à la ligne de commande : `--spring.profiles.active=prod`__ 

* __Enfin\, pour pouvoir tester ses profiles\, ou activer un profile de test\, on peut utiliser au sein d’une classe de test l’annotation : `@ActiveProfile("test")`__ 

--

# TP5

![](./img/diapo_formation_spring_property_2.png)
