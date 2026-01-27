# La levée des ambiguïtés


--
# Ambiguïté : Quel est le bon bean ?

* __L’autowiring est la manière la moins verbeuse et la plus naturelle d’injecter les beans__

* __Dans la majorité\, voire la totalité des cas\, il n’y a pas d’ambiguïté à lever au niveau du bean à injecter : une interface -> un bean__

* __En cas d’ambiguïté\, c’est à dire que plusieurs beans matchent\, des outils existent pour les résoudre…__

* __…Mais 1ere étape : chercher à ré\-organiser son code pour éviter les ambiguïtés non\-nécessaires \!__

--
# Se poser les bonnes questions...

* __Il y a ambiguïté si plusieurs beans peuvent matcher une dépendance : même type\, pas d’appairage par le nom de l’attribut ou de l’argument__
* __De même\, la meilleure manière de résoudre le problème peut\-être la réorganisation du code :__
  * __Redéfinition des responsabilités__
  * __Création d’interfaces supplémentaires__
  * __Création de nouveaux beans__

--
# ... et y répondre !

* __Dans le cas où l’ambiguïté reste nécessaire\, plusieurs méthodes existent…__
* __En cas avec plusieurs pool de connexion par ex. la qualification peut être nécessaire__
* __Mais il s’agit de cas assez rares__

--
# `@Primary`

* __Cette annotation permet d’indiquer à Spring le candidat par défaut à choisir en cas d’ambiguïté__

* __Elle se positionne soit sur une classe `@Component` ou dérivés :__

```java
@Service
@Primary
public class ReservationSalleServiceImpl 
    implements ReservationSalleService {...}
```
--
# `@Primary`

* __Soit sur une méthode annotée `@Bean` :__
```java
@Bean
@Primary
public ReservationSalleService getReservationSalleImpl() {...}
```
* __Enfin en XML avec l’attribut `primary="true"`__

* __Mais s’assurer au préalable de la pertinence d’avoir 2 instances de la même interface au sein du container__

--
# `@Qualifier`

* __Cette annotation permet de "tagger" des catégories de beans, ex : `@Qualifier("bdd_metier")`__

* __Elle se positionne de même que `@Primary`, soit sur une classe `@Component` ou dérivés, soit sur une méthode annotée `@Bean`__ 

* __On pourra alors spécifier lors de l’injection sur un attribut ou un setter la "catégorie" à laquelle doit appartenir le bean__

--
# `@Qualifier`

* __De même au sein d’une fonction sujette à l’injection \(constructeur\, création de bean…\) on pourra spécifier la catégorie de l’argument__

* __Ex :__

```java
`@Autowired`
public void setReservationSalleDao(
      @Qualifier("festif") ReservationSalleDao reservationSalleDao) {
  reservationSalleDao.setNbSallesDispo(11);
}
```