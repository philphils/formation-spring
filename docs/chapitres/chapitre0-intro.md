# Les bases du framework Spring

![](./img/diapo_formation_spring_0.png)

<div style="position: absolute; bottom: 20px; right: 30px; font-size: 0.8em; color: #444">
<i>#SpringIsComing…</i> 😉
</div>
--


# Plan
* [Chapitre 1 — Couplage faible / Couplage fort](#/1)
* [Chapitre 2 — Spring et l’inversion de contrôle](#/2)
* [Chapitre 3 — Le contexte d’application Spring](#/3)
* [Chapitre 4 — Création des beans en XML](#/4)
* [Chapitre 5 — Création des beans avec annotations](#/5)
* [Chapitre 6 — Création des beans en mode Java-based](#/6)
* [Chapitre 7 — La création du contexte](#/7)
* [Chapitre 8 — L’autowiring](#/8)

--
# Plan

* [Chapitre 9 — Injection](#/9)
* [Chapitre 10 — Dépendances circulaires](#/10)
* [Chapitre 11 — La levée des ambiguïtés](#/11)
* [Chapitre 12 — Les annotations standards](#/12)
* [Chapitre 13 — Initialisation et destruction des beans](#/13)
* [Chapitre 14 — Le scope](#/14)
* [Chapitre 15 — Héritage entre beans](#/15)
* [Chapitre 16 — Configurations hybrides](#/16)

--
# Plan

* [Chapitre 17 — Spring Tools Suite](#/17)
* [Chapitre 18 — Environnement (Environment)](#/18)
* [Chapitre 19 — Récupération des Properties](#/19)
* [Chapitre 20 — Profiles](#/20)
* [Chapitre 21 — Conclusion](#/21)
--

# Introduction

* __Framework très répandu dans le monde Java__
* __Développé dans les années 2000 par Rod Johnson__
* __Entièrement Open Source__
* __Utilisé pour le développement d’applications Web ou Batch__

--

# Introduction

* __Volonté d’un framework « léger » et souple__

* __Au départ configuration via fichiers XML externes__

* __Plus intrusif depuis l’utilisation des annotations mais plus de lisibilité__


--
# Introduction

* __Noyau initial de Spring \(spring\-core\) autour de l’Injection de Dépendance__

* __Maintenant\, Spring présent dans toutes les couches applicatives__

* __Services\, DAO\, Web\, Batch\, échange par messagerie \(JMS\)\, configuration etc\.__

* __Enfin Spring\-Boot \(2014\) « chapeaute » l’ensemble, et permet de démarrer rapidement une application Spring__

--

# Schéma des modules Spring

 ![](./img/diapo_formation_spring_2.png) <!-- .element: class="image-large" -->

--
# Dépendances Maven

* __Spring a de nombreuses extensions et donc dépendances__
* __Les applications récentes passent par Spring\-Boot et ses « starters »__
* __Pour utiliser Spring sans Spring\-Boot\, on peut déclarer la dépendance spring-context :__
    ```xml
     <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>  spring-context  </artifactId>
          <version>6.1.4</version>
     </dependency>
    ```