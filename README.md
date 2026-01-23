# Formation Spring – Noyau & IoC

## 🎯 Objectifs

Cette formation a pour objectif de fournir une **compréhension solide des fondements de Spring**, en particulier du **conteneur IoC** (*Inversion of Control*) et de la gestion des **beans**.

Spring est un ensemble de frameworks Java largement utilisés depuis plus de 10 ans, notamment à l’Insee. L’écosystème Spring s’est construit autour d’un noyau permettant la **gestion de l’injection de dépendances**, un *design pattern* visant à :

* réduire le couplage entre les composants applicatifs ;
* améliorer la lisibilité et la structuration du code ;
* faciliter les tests et la maintenance ;
* rendre les applications plus modulaires et évolutives.

Cette formation se concentre volontairement sur **Spring “core”**, sans aborder Spring Boot, qui fera l’objet d’une formation ultérieure.

---

## 🗓️ Organisation de la formation

La formation se déroule sur **2 journées**.

Elle ne vise pas à couvrir l’intégralité des modules Spring, mais à donner aux participants les **repères conceptuels essentiels** pour comprendre le fonctionnement interne du framework et être autonomes dans des projets existants utilisant Spring.

L’accent est mis sur :

* le cycle de vie des beans ;
* les mécanismes de configuration ;
* les bonnes pratiques d’utilisation du conteneur Spring.

---

## 🧪 Travaux pratiques

Les travaux pratiques ont pour objectif de **manipuler concrètement le conteneur Spring** afin de comprendre :

* la définition et l’utilisation des **beans** ;
* les différents types d’**injection de dépendances** ;
* les mécanismes d’**autowiring** et de résolution des ambiguïtés ;
* les **scopes** des beans ;
* les **profiles** et la configuration selon l’environnement ;
* la gestion du cycle de vie et de l’initialisation des beans.

### Approche pédagogique

* Les TPs s’appuient sur des **exemples progressifs et ciblés**, indépendants d’une application métier complète.
* Chaque notion théorique est rapidement mise en pratique.
* L’objectif n’est pas de construire une API complète, mais de **comprendre finement le fonctionnement de Spring**.
* Une démarche proche du **TDD (Test Driven Development)** est encouragée afin de valider le comportement attendu du conteneur.

---

## 📚 Contenu du cours

Le cours est structuré en chapitres, chacun abordant une notion clé du noyau Spring :

* introduction et problématiques de couplage ;
* Inversion of Control (IoC) ;
* conteneur Spring et contexte applicatif ;
* configuration par XML, annotations et Java-based configuration ;
* création et injection des beans ;
* autowiring et dépendances circulaires ;
* scopes, héritage et initialisation ;
* environnements, properties et profiles ;
* bonnes pratiques et standards.

---

## 📚 Ressources pédagogiques

Une partie du contenu théorique s’inspire de la ressource suivante :

👉 [https://gayerie.dev/docs/spring/index.html](https://gayerie.dev/docs/spring/index.html)

---

## 📽️ Support de présentation

Les diapositives de la formation sont disponibles à l’adresse suivante :

👉 [https://philphils.github.io/formation-spring/](https://philphils.github.io/formation-spring/)

---

## 📄 Licence

Ce dépôt (code source et supports de formation) est distribué sous licence **Creative Commons Attribution 4.0 International (CC BY 4.0)**.

🔗 [https://creativecommons.org/licenses/by/4.0/deed.fr](https://creativecommons.org/licenses/by/4.0/deed.fr)

Vous êtes libre de :

* réutiliser ;
* modifier ;
* partager ce contenu,

à condition de **mentionner l’auteur**.
