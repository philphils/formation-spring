# Création des beans en XML

--
# Plusieurs méthodes

* __Nous allons voir les différentes méthodes pour créer des beans__

* __La création d’un bean entraîne son ajout au sein du contexte d’application__

* __Il est donc mis à disposition de toutes les classes__

* __Nous verrons ensuite les différentes méthodes pour récupérer un bean__

* __La création des beans se fait en général au démarrage de l’application\, lors de la création du contexte__


--
# Exemple XML

![](./img/diapo_formation_spring_8_1.png)

--
# Exemple XML

![](./img/diapo_formation_spring_8_2.png)

--
# Exemple XML

![](./img/diapo_formation_spring_8_3.png)

--
# Exemple XML

![](./img/diapo_formation_spring_8_4.png)

--
# Exemple XML

![](./img/diapo_formation_spring_8_5.png)

--
# Exemple XML

![](./img/diapo_formation_spring_8_6.png)


--
# Création d’un bean en XML

* __En XML la déclaration d’un bean se fait via la balise :__

```xml
<bean id=«...» class=«...» > … </bean>
```
* __La convention de nommage est le Camel\-Case avec minuscule pour le premier mot__

--
# Création d’un bean en XML

* __On pourra ensuite faire référence à cette instance avec :__

```xml
ref = «idAutreBean»
```
* __On peut ainsi valoriser les dépendances avec :__
```xml
<property name=«nomAttribut» ref=«idAutreBean» /> 
```
* __Ou s’il s’agit d’un type « basique » avec une valeur en dur:__
```xml
<property name=«nomAttribut» value=«...» />
```

--
# Création d’un bean en XML

* __Pour construire le bean\, on passe des arguments au constructeur avec :__

```xml
<constructor-arg ref=« ... » /> ou <constructor-arg value=« ... » />
```
* __On répète la balise pour les différents paramètres du constructeur__

--
# Création d’un bean en XML

* __On peut préciser le numéro de l’argument avec `index = « 0 »`__

* __Ou encore via son nom avec `name= « nomArg »`__

* __Spring choisira le constructeur qui correspond le mieux au nombre et au type des arguments \(ou le constructeur vide si aucun argument\)__


--
# Alternatives au constructeur

* __Il est possible de créer des beans via des méthodes autres que le constructeur__

* __On peut utiliser une méthode statique\, on valorisera alors l’attribut : `factory-method`__

* __Pour utiliser une méthode non statique\, à partir d’un bean donc\, on précisera les attributs : `factory-bean`, et `factory-method`__

* __Ces cas devraient être relativement rares. On utilise le plus souvent le constructeur\, vide ou avec paramètres__


--
# Méthode XML

* __On créera ensuite le contexte à partir du fichier XML :__
```java
ApplicationContext context = 
      new ClassPathXmlApplicationContext("config-spring.xml")
```

* __On pourra aussi importer la config XML dans une classe `@Configuration` avec  `@ImportResource("classpath:config-spring.xml")`__

--
# Méthode XML

* __On peut récupérer le bean ainsi créé via son identifiant :__

```java
context.getBean("reservationSalleService")
```
* __Dans la réalité\, on utilise plutôt d’autres méthodes vues plus loin\, par ex\. via l’annotation : `@Autowired`__

* __Les configurations hybrides sont abordées plus loin__


--
# Découpage du XML

* __Pour des applis importantes\, le fichier XML devient vite trop gros et illisible__

* __On découpe donc le fichier XML contenant les beans en plusieurs fichiers__

* __Plusieurs logiques de découpage\, mais en général découpage par couches \(Dao\, controller\, service\.\.\.\)__


--
# Découpage du XML

* __On utilise ensuite la balise `import` (y compris dans les fichiers découpés\) :__
```xml
<import resource=« config_spring_bis.xml » />
```
* __Et on construira le contexte avec un fichier central\, « point d’entrée » pour Spring__
