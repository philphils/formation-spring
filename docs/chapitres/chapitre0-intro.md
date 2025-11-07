# Les bases du framework Spring

![](./img/diapo_formation_spring_0.png)

<div style="position: absolute; bottom: 20px; right: 30px; font-size: 0.8em; color: #444">
<i>#SpringIsComing…</i> 😉
</div>
--

- Tour de parole pour mesurer le niveau d'expérience
- Questions organisationnelles

--
# Plan

* __[Introduction](slide3.xml)__

* __[Couplage faible/Couplage fort](slide9.xml)__

* __[L’injection de dépendances](slide15.xml)__

* __[L’inversion de contrôle](slide30.xml)__

* __[Le contexte d’application](slide37.xml)__

* __[Création des beans en XML](slide43.xml)__

--
# Plan

* __[Création des beans avec annotations](slide56.xml)__

* __[Création des beans en mode Java\-Based](slide63.xml)__

* __[La création du context](slide69.xml)__

* __[L'autowiring](slide76.xml)__

* __[Levée des ambiguïtés](slide107.xml)__

* __[Les annotations standards](slide111.xml)__

* __[Initialisation destruction des beans](slide116.xml)__

--
# Plan

* __[Bean’s scope](slide120.xml)__

* __[Héritage entre les beans](slide125.xml)__

* __[Configuration XML/Java remix](slide130.xml)__

* __[Spring Tools Suite](slide134.xml)__


--
# Introduction

* __Spring est un framework très répandu dans le monde Java__

* __Développé dans les années 2000 par Rod Johnson__

* __Entièrement Open Source__

* __Utilisé pour le développement d’applications Web ou Batch__

* __Volonté d’un framework « léger » et souple__

--

# Introduction

* __A l’origine une alternative à l’architecture J2EE assez contraignante \(EJB\)__

* __On parle de conteneur « léger » par opposition au conteneur « lourd » J2EE__

* __Volonté d’être « non\-intrusif » dans le code__

* __Au départ configuration via fichiers XML externes__

* __Plus intrusif depuis l’utilisation des annotations mais plus de lisibilité__

--

# Introduction

* __Noyau initial de Spring \(spring\-core\) autour de l’Injection de Dépendance__

* __Maintenant\, Spring présent dans toutes les couches applicatives__

* __Services\, DAO\, Web\, Batch\, échange par messagerie \(JMS\)\, configuration etc\.__

* __Enfin Spring\-Boot \(2014\) « chapeaute » l’ensemble__

* __Permet de démarrer et configurer très rapidement les applications__

--
# Schéma des modules Spring

 ![](./img/diapo_formation_spring_2.png)

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