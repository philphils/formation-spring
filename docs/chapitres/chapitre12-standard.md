
# Les annotations standards
# pour l'injection

--
# Standard VS propriétaire

* __Comme pour Hibernate\, avec l’apparition de la norme JPA\, Java a intégré l’injection de dépendances dans ses standards__

* __Apparition de plusieurs annotations standards permettant de réaliser l’injection de dépendances__

* __Permet de ne pas dépendre de l’implémentation__
    * __→ Théoriquement meilleure portabilité de l’application__


--
# JSR 330 : `@Inject`

* __Possibilité de remplacer `@Autowired` par `@Inject`, de même appairage par type__

* __Fonctionnalités de base pour l’injection__

* __Pas d’attribut `required` disponible\, par défaut lève une exception si aucun bean disponible__

* __Certaines fonctionnalités pas dispo ex\. l’injection de collection ou de tableaux__

* __Peut s’utiliser avec `@Primary`, `@Qualifier`__ 


--
# JSR 250 : `@Resource`

* __Possible aussi de remplacer `@Autowired` par `@Resource`__

* __La différence avec `@Inject` est dans le type d’appairage__

* __Appairage par nom et non par type__

--
# JSR 250 : `@Resource`

* __De même que `@Inject` pas de possibilité de rendre l’injection optionnelle__

* __Peut\-être utilisé sur les constructeurs\, setter ou attribut__

--

# TP3


![](./img/diapo_formation_spring_13.png)

