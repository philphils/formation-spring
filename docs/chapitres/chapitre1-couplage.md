# Objectif Spring :

# Réduction du couplage

--
# Couplage fort VS couplage faible

* __Les bonnes pratiques se généralisent pour rendre les applications plus maintenables__

* __Objectif : Réduire l’adhérence entre différentes parties du code__

* __On parle de couplage faible vs couplage fort__

* __Couplage fort → adhérence\, rigidité\, coût élevé d’une évolution du code__

* __Couplage faible → Souplesse pour les évolutions et maintenances\, coût moindre__


--
# Ex 1 : La division 
# en couches applicatives

* __C’est l’objectif de la division en couches de l’application par exemple__

* __Présentation \(IHM\) – Service \- DAO__

* __Chaque couche applicative a ses responsabilités qui ne regarde qu’elle__

* __Autonomie des couches applicatives__

* __Une modification d’une couche n’impacte pas les autres__


--
# Ex 2 : Les interfaces

* __Même principe pour les interfaces__

* __Idée : L’interface établit un contrat auquel doivent répondre les implémentations__

* __Les autres classes n’ont pas connaissance de la manière dont elle y répond__

* __Réduit la dépendance\, le couplage entre les différentes classes__

* __Plus simple de changer d’implémentation__


--
# Ex 3 : Les DTO

* __Moyen de découpler les données métiers et les données exposées__

* __Bonne pratique en termes de sécurité pour les API__

* __Plus de travail à la réalisation\, mais moins d’adhérence en cas d’évolution du modèle métier__


--
# Le faible couplage :
# une bonne pratique

* __Conduit à un code plus lisible__

* __Plus grande modularité applicative__

* __Évolutions et maintenances moins coûteuses__

* __Intérêt croissant avec la taille de l’application__

* __Couplage trop fort peut conduire à l’abandon de maintenances \!__

* __L’injection de dépendances__


--
# Schéma appli 3 tiers

![](./img/diapo_formation_spring_3.png)


--
# Schéma avec interfaces

![](./img/diapo_formation_spring_4.png)


--
# Schéma avec interfaces + DTO

![](./img/diapo_formation_spring_5.png)


--
# Nouvelle problématique

* __Comment et où gérer les liens entre les différents composants des différentes couches applicatives ?__

* __Comment et où gérer les liens entre les interfaces et leurs implémentations ?__

* __Comment articuler ces éléments en maintenant un couplage le plus faible possible ?__


--
# Exemple avec adhérence

![](./img/diapo_formation_spring_5_1.png)

--
# Exemple avec adhérence

![](./img/diapo_formation_spring_5_2.png)


--
# Exemple avec adhérence

![](./img/diapo_formation_spring_5_3.png)

--
# Plusieurs problèmes

* __On a une référence directe à l’implémentation au sein d’une autre classe__
    * __→ Perte de l’abstraction\, accès aux composants ou méthodes internes__
* __Considération concernant la couche « persistance » au sein de la couche « service »__
    * __→ Transgression de la séparation en couche__
* __Partage d’instances de__  __ReservationDao__  * __compliqué voire impossible__
    * __→ Pas de pattern « singleton »__



--
# Mais surtout !

* __Les composants internes de la classe ne sont pas modifiables__

* __Impossible de mocker pour les tests__

* __Impossible de modifier les composants à l’exécution__

* __Impossible d’étendre les fonctionnalités des composants__

* __Pas de pattern Proxy__


--
# L’injection de dépendances

* __Pour résoudre ces problèmes, on va injecter depuis l’extérieur les composants__

* __La classe n’instancie plus elle\-même ses composants__

* __La classe ne déclare que des types abstraits__

* __Les composants sont donc modifiables depuis l’extérieur avant injection__

* __On peut remplacer par des Mocks par ex\.__


--
# Exemple avec 
# injection de dépendances

![](./img/diapo_formation_spring_5_4.png)


--
# Exemple avec 
# injection de dépendances

![](./img/diapo_formation_spring_5_5.png)

--
# Exemple avec 
# injection de dépendances

![](./img/diapo_formation_spring_5_5.png)

__Le lien entre `ReservationSalleService` et `ReservationSalleDao` apparaît plus clairement__


--
# Mais où sont créées les instances !?

* __Noob :__  _Ok je vois bien l’intérêt de cette pratique mais… ou sont fait maintenant les `new QuelqueChose();` ??\!_

* __Expert :__  _Il n’y a pas de magie mon petit… il te faut un outil de création/destruction/gestion des instances de tes composants_

* __Noob :__  _Ah oui… je sens que tu vas me vendre un truc là…_

--
# Mais où sont créées les instances !?

* __Expert :__  _Pas forcément\, tu peux le faire toi\-même\, mais ça risque de…_

* __Noob :__  _D’accord, bon allez vend moi ta camelote_
