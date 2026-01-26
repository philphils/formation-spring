# Création des beans 
# en mode Java-based

--
# Principes

* __Le framework Spring a permis de mettre en œuvre l’injection de dépendance__

* __L’externalisation en XML des liens interface/implémentation permet un couplage faible dans le code Java__

--
# Principes

* __L’utilisation des annotations a permis de revenir au code via l’introduction de « meta\-données »__

* __La configuration Java\-based\, ou full Java\, est un retour à la simplicité et la puissance de Java__

* __On a l’impression d’un retour arrière\, mais en réalité la structure des applications a évoluée__


--
# Création des beans avec @Bean

* __On a toujours le container « au\-dessus » de notre application__

* __On va injecter des beans en annotant des méthodes Java qui produisent des objets__


--
# Création des beans avec @Bean

* __On récupère ainsi la souplesse et la puissance de Java__

* __Simplification et confort du point de vue du développeur__

* __Possibilité de configuration dynamique\, ie à l’exécution\, qui était complexe ou impossible en XML/Annotation__


--
# Exemple

* __On crée une méthode qui renvoie un bean avec la configuration souhaitée :__

```java
@Bean
public IReservationSalleService reservationSalleService() {
  ReservationSalleServiceImpl reservationSalleServiceImpl 
      = new ReservationSalleServiceImpl();
  reservationSalleServiceImpl.setNbSallesDispo(12);
  return (IReservationSalleService) reservationSalleServiceImpl;
}
```

--
# Exemple

* __On renvoie un objet typé avec une interface\, ce qui permet de conserver le découplage__

* __Par défaut l’id du bean = le nom de la méthode__

* __Pour configurer l’id on fait comme pour `@Component`: `@Bean("nomDuBean")`__


--
# Exemple

* __Si des dépendances sont nécessaires à la création du bean\, on ajoute des arguments qui seront valorisés par « Autowiring » \(cf plus loin\). Ex:__

```java
@Bean
public IReservationSalleService 
    reservationSalleService(ReservationSalleDao reservationSalleDao) {
  ReservationSalleServiceImpl reservationSalleServiceImpl 
      = new ReservationSalleServiceImpl(reservationSalleDao);
  reservationSalleServiceImpl.setNbSallesDispo(12);
  return (IReservationSalleService) reservationSalleServiceImpl;
}
```

--
# Où positionner les @Bean ?

* __Dans des classes annotées avec `@Configuration`__

* __Les classes `@Configuration` deviennent elles-mêmes des beans pour Spring__

* __Spring utilise alors un proxy particulier__

--
# Où positionner les @Bean ?


* __On peut mettre `@Bean` dans des classes `@Component` ou dérivés, mais toutes les fonctionnalités ne sont pas couvertes__

* __Il est conseillé pour la validité et la lisibilité du code d’utiliser `@Configuration` pour ces cas__


--
# Exemple d’utilisation 
# classique de @Bean

```java
@Configuration
public class ReservationSalleConfiguration {
  @Bean
  public ReservationSalleService reservationSalleService() {
    ReservationSalleServiceImpl reservationSalleServiceImpl 
        = new ReservationSalleServiceImpl();
    reservationSalleServiceImpl.setNbSallesDispo(12);
    return (ReservationSalleService) reservationSalleServiceImpl;
  }
}
```