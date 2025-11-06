# Spring et l’inversion de contrôle

--
# Le besoin d’un nouvel outil

* __Délégation et centralisation de la gestion du cycle de vie des composants__

* __Possible réalisation maison \(ex : pattern Locator\)\, mais… risque de malfaçon__

* __Le framework se charge de gérer les instances de composants applicatifs…__

* __…Et peut donc les modifier à l’exécution \!__

* __Sans doute le principal intérêt de l’injection de dépendances__


--
# Principe de l’IoC

* __Ce n’est plus le développeur qui va utiliser les classes et méthodes d’une librairie__

* __Ici le framework prend la main__

* __Il crée les instances des composants et supervise l’exécution__

* __C’est ce qu’on appelle l’« inversion de contrôle »__

* __Le code du développeur s’insère dans le cadre défini par le framework__


--
# Effet positif du « lâcher prise »

* __Le framework « prend le contrôle » sur le cycle de vie des composants de l’application__

* __Il gère l’articulation entre eux et peut les modifier à la création ou à l’exécution__

* __Le Pattern Proxy souvent utilisé par exemple__

* __Création de sous\-classes avec des comportements supplémentaires__

* __Typiquement : ajout de l’ouverture/fermeture des transactions en BDD__


--
# Et la galaxie Spring fut

* __C’est grâce à l’inversion de contrôle que Spring va pouvoir proposer de nombreux outils__

* __Gestion des properties\, gestion des batchs\, gestion des transactions\, définition de profiles\, programmation orientée aspect…__

* __La prise en charge du cycle de vie des composants ouvrira de nombreuses potentialités__

* __C’est une porte qui s’ouvre sur de nombreuses fenêtres…__

* __Ok j’arrête__


--
# Un monopole scandaleux

* __Spring est l’ultra\-leader de gestion de la DI \!__

* __Google Guice seul concurrent\. Beaucoup moins répandu\.__

* __Avec Spring Boot\, Pivotal enterre définitivement la concurrence__

* __Un problème pour la fluidité du marché…__

* __Un danger en termes de sécurité ? 🤔__

![](./img/diapo_formation_spring_6.png)

--
# TP1 :

__Refactorisation avant Spring__

![](./img/diapo_formation_spring_7.png)
