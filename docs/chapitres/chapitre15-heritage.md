
# Héritage entre beans

--
# Fonctionnement 
# de l’héritage en XML

* __On peut réutiliser la configuration d’un bean via l’héritage__

* __En XML\, avec la balise `parent=` on peut réutiliser la configuration d’un bean__

* __La valorisation des properties est héritée aussi\, et peut\-être surchargée__

--
# Fonctionnement 
# de l’héritage en XML

* __Il n’y a pas nécessairement besoin d’un héritage en Java…__

* __… mais cela semble plus naturel \! → KISS__


--
# Fonctionnement de 
# l’héritage en Java

* __Avec `@Configuration` on peut de même réutiliser la configuration d’une méthode `@Bean`__

* __Les 2 classes\, parent et enfant\, doivent alors être annotées avec `@Configuration`__

* __Les 2 méthodes doivent être annotées `@Bean`__

--
# Fonctionnement de 
# l’héritage en Java

* __Un seul bean sera créé en cas de surcharge pour éviter les conflits\, celui de la classe fille__

* __Ce type de configuration devrait rester relativement rare car peut réduire la lisibilité du code__


--
# Digression :
# Héritage VS composition

* __L’héritage fait partie des notions de base Java…__

* __Mais son utilisation excessive peut réduire la lisibilité du code et accroît le couplage__

* __Ex : un trop grand nombre de niveaux d’héritage__

--
# Digression :
# Héritage VS composition

* __On peut lui préférer la composition\, ie la définition de composants/attributs communs__

* __Il s’agit d’un vieux débat… mais toujours d’actualité :__

* __https://www.youtube.com/watch?v=wfMtDGfHWpA__

--

# TP4

![](./img/diapo_formation_spring_14.png)

