
# Dépendances circulaires

--
# Le problème des 
# dépendances circulaires

* __Le principal problème du mode d’injection via constructeur est qu’il ne permet pas les dépendances circulaires__

* __Une `BeanCurrentlyInCreationException` sera levée si une dépendance circulaire est détectée__

* __Le meilleur moyen de résoudre le problème reste la réorganisation du code : utilisation des interfaces\, séparations plus claires des responsabilités…__

--
# Le problème des 
# dépendances circulaires

* __Il est possible aussi d’avoir recours à l’injection via setter ou attribut pour ces beans seulement__

* __Enfin\, on peut utiliser les méthodes d’initialisation\, avec par exemple `@PostConstruct` pour configurer le bean après création ou encore utiliser `@Lazy`…__


--
# `@Lazy`, `@DependsOn`

* __Le problème des dépendances circulaires peut aussi être résolu par `@Lazy`, ou `@DependsOn`__

* __`@Lazy` permet de retarder la création d’un bean jusqu'au premier appel réel de ses méthodes__

* __`@Lazy` peut ainsi être positionné au niveau de l’argument d’un constructeur, au niveau attribut, au niveau de la classe ou encore sur une méthode `@Bean`__

--
# `@Lazy`, `@DependsOn`

* __`@DependsOn` permet de signifier à Spring qu’un bean dépend d’un autre et doit être créé après lui__

* __Ces annotations sont à utiliser avec parcimonie car elles peuvent complexifier le déboggage...__
