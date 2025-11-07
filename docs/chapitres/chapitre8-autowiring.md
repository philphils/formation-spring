# L’autowiring

--

# Principe de l’autowiring

* __L’injection de dépendance \(DI\)\, ie récupérer des beans dans des beans\, est la vocation première de Spring__

* __Spring propose une manière naturelle de faire avec un code et une configuration minimaliste__

* __Le mécanisme le plus utilisé pour récupérer un bean est l’autowiring\, ou « auto\-câblage »__


--
# Principe de l’autowiring

* __Plusieurs niveaux d’utilisation de ce mécanisme existent : par constructeur\, attribut\, ou setter__

* __Spring va rechercher le bon bean pour une dépendance au sein du contexte : on parle d’appairage\.\.\.__


--
# 3 niveaux d’utilisation



* __L’autowiring peut\-être utilisé à 3 niveaux :__
  * __Constructeur : appairage des arguments du constructeur et injection au moment de la création du bean__
  * __Attribut : appairage de l’attribut de la classe et renseignement une fois l’instance créée par réflexivité__
  * __Setter : appairage de l’argument du setter\, appel__  * __du setter après la création__



--
# 2 modes d’appairage



* __Principe : Spring cherche un ou des beans candidats au sein du contexte pour satisfaire une dépendance__
* __2 types d’appairage existent :__
  * __Par type : mode le plus utilisé\, il se base sur le type de l’interface ou de la classe__
  * __Par nom : recherche d’un bean ayant le même nom que l’attribut ou l’argument de la méthode__
 
--
# 2 modes d’appairage 

* __Peuvent être combinés : appairage par type  puis par nom en cas d’échec \(ex : `@Autowired`)__
* __Mécanisme transparent en général\, peut évoluer selon les versions__
* __Mieux vaut sécuriser en évitant les ambiguïtés__



--
# Autowiring de type constructeur

* __Mode simple et peu verbeux__

* __Il s’agit de la méthode d’injection préconisée en termes de bonnes pratiques \(cf plus loin\)__

* __Spring cherche un bean pour chaque argument du constructeur en se basant sur son type__

--
# Autowiring de type constructeur

* __Si plusieurs constructeurs sont définis\, l’un doit être annoté `@Autowired` pour indiquer à Spring le constructeur à utiliser__

* __Si aucun constructeur n’est défini\, Spring utilisera le constructeur vide pour créer le bean__

--
# Autowiring de type constructeur

![](./img/diapo_formation_spring_9_1.png) <!-- .element: class="image-large" -->



--
# Autowiring de type constructeur

![](./img/diapo_formation_spring_9_2.png) <!-- .element: class="image-large" -->


--
# Autowiring de type constructeur

![](./img/diapo_formation_spring_9_3.png) <!-- .element: class="image-large" -->


--
# Autowiring de type constructeur

![](./img/diapo_formation_spring_9_4.png) <!-- .element: class="image-large" -->


--
# Autowiring de type constructeur

![](./img/diapo_formation_spring_9_5.png) <!-- .element: class="image-large" -->


--
# Autowiring de type constructeur

![](./img/diapo_formation_spring_9_6.png) <!-- .element: class="image-large" -->


--
# Autowiring de type constructeur

![](./img/diapo_formation_spring_9_7.png) <!-- .element: class="image-large" -->

--
# L’Autowiring et 
# l’annotation @Bean

* __De même que pour l’autowiring sur un constructeur\, celui\-ci s’applique aux méthodes `@Bean`__ 

* __On peut ainsi ajouter des arguments à la méthode que Spring tentera de valoriser avec des beans correspondant__

--
# L’Autowiring et 
# l’annotation @Bean

* __Ex Spring cherchera un bean de type ReservationSalleDao:__

```java
@Bean
public ReservationSalleService reservationSalleService
                    (ReservationSalleDao reservationSalleDao) {
  ReservationSalleServiceImpl reservationSalleServiceImpl 
      = new ReservationSalleServiceImpl();
  reservationSalleServiceImpl
      .setReservationDalleDao(reservationSalleDao);
  return (ReservationSalleService) reservationSalleServiceImpl;
}
```

--
# Autowiring de type constructeur

![](./img/diapo_formation_spring_9_8.png) <!-- .element: class="image-large" -->


--
# L’annotation @Autowired

* __`@Autowired` a de multiples usages__

* __Cette annotation peut aussi s’utiliser pour résoudre une dépendance au niveau attribut__

* __L’attribut est alors renseigné par réflexivité après création de l’instance__

--
# L’annotation @Autowired

* __Elle réalise par défaut un appairage via le type de l’attribut\, puis\, en général\, par nom si ambiguïté__

* __Il est aussi possible de récupérer tous les beans d’un type au sein d’une collection__


--
# Autowiring au niveau attribut


![](./img/diapo_formation_spring_10_1.png) <!-- .element: class="image-large" -->

--
# Autowiring au niveau attribut

![](./img/diapo_formation_spring_10_2.png) <!-- .element: class="image-large" -->

--
# Autowiring au niveau attribut

![](./img/diapo_formation_spring_10_3.png) <!-- .element: class="image-large" -->

--
# @Autowired et le paramètre required

* __Si la dépendance ne peut être satisfaite avec les beans du container__

    * __→ NoSuchBeanDefinitionException \!__

* __`@Autowired` dispose d’un paramètre `required=` qui attend un booléen et vaut _true_ par défaut__

* __Si `required= false` la dépendance est alors définie comme « optionnelle »__

    * __→ Aucune exception n’est levée\, l’attribut ou l’argument vaut _null_ .__


--
# Autowiring au niveau setter

* __L’autowiring peut aussi s’utiliser au niveau des méthodes setter__

* __Spring invoquera la méthode setter en lui passant un bean correspondant à l’argument après la création de l’instance__

* __Avantage : possibilité d’ajouter du code supplémentaire de \(configuration par ex\.\)__

--
# Autowiring au niveau setter
 * __Ex :__

```java
@Service
public class ReservationSalleServiceImpl {
  private ReservationSalleDao reservationSalleDao;
  ...
  @Autowired
  public void setReservationSalleDao
          (ReservationSalleDao reservationSalleDao) {
    this.reservationSalleDao = reservationSalleDao;
    //Possibilité d'ajouter ici du code après l'injection
    reservationSalleDao.setNbSallesDispo(11);
  }
...
}
```

--
# Autowiring en XML

* __L’autowiring diffère entre les configurations XML et en mode annotation/Java based__
* __En XML\, il n’y a pas d’autowiring par défaut__

--
# Autowiring en XML

* __On peut l’activer au niveau d’un bean avec l’attribut `autowire=` :__
    * __« constructor » : active l’autowire au niveau du constructeur__
    * __« byType » : active l’autowire pour l’ensemble des attributs avec appairage par type__
    * __« byName » : active l’autowire pour l’ensemble des attributs avec appairage par nom__

--
# Autowiring en XML

* __On peut aussi l’activer pour l’ensemble des beans au niveau de la balise `<beans>` avec par ex :__
  ```xml
  <beans default-autowire="byType">
  ```
* __Il n’y a pas en revanche d’autowiring de type setter en XML__

--
# TP2 :

__Mise en place Spring__

![](./img/diapo_formation_spring_12.png)