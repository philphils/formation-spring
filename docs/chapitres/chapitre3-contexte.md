# Le contexte d’application Spring

--
# What !
# Encore une couche d’abstraction !

* _"Tout problème en informatique peut être résolu par une nouvelle couche d'abstraction... sauf le problème d'avoir trop de couches d'abstraction\."\, David Wheeler_

![](./img/diapo_formation_spring_8.png)  <!-- .element: class="image-emoji" -->


--
# Le rôle du contexte d’application

* __Spring appelle « contexte » l’objet qui va centraliser les composants applicatifs__

* __On utilise aussi le terme « conteneur IoC »\. Le conteneur gère le cycle de vie des composants__

--
# Le rôle du contexte d’application

* __Création : Constitution des instances \(new\) avec gestion des inter\-dépendances__

* __Mise à disposition selon plusieurs modes : instance unique \(singleton\) ou création à la volée \(prototype\)__

* __Destruction : libération éventuelle des ressources \(connexions\, nettoyage dossier\)__


--
# Mister « beans »

* __Les instances des classes Java de composants sont appelées « beans »__

* __Le terme « bean » renvoit à des composants petits\, simples… et donc réutilisables \(__  * __inspiré par les grains de café?\)__

* __Le contexte d’application gère donc le « cycle de vie des beans » et leurs inter\-dépendances et permet de les récupérer simplement__


--
# Le contexte d’application

* __Le contexte d’application est une classe interne à Spring qui implémente l’interface__  __ApplicationContext__

* __Différents types concrets sont disponibles selon la manière dont il est configuré__

* __On peut récupérer un bean directement depuis l’objet « context » ainsi :__
```java
context.getBean("monBean")
```

--
# Le contexte d’application

* __On a souvent un seul contexte d’application__

* __Il est possible d’en configurer plusieurs\, par exemple pour les différents modules \(web\, batch\.\.\.\)__

* __...But stay KISS ! 😜__

--
# 3 méthodes de configuration

* __3 manières de décrire les beans gérés par Spring__
* __Méthodes de configuration du contexte par ordre chronologique d’apparition :__
  * __Via des fichiers XML : Permet d’externaliser la configuration des beans → `ClassPathXmlApplicationContext(« fichier.xml »)`__
  * __Via des annotations : Présentes directement au sein des composants qui seront gérés → `AnnotationConfigApplicationContext(Config.class)`__

--
# 3 méthodes de configuration

*
  * __En Java : Méthode dite « Java\-based »\, constitution d’objet en Java et récupération par le conteneur à l’issue → `AnnotationConfigApplicationContext(Config.class)`__
* __Nous verrons la méthode XML dans le cours\, mais pour les TPs nous n’utiliserons que les 2 plus récentes__
