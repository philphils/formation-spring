# TP 4 : Cycle de vie et Dépendances des Beans

**Objectif :** Maîtriser le cycle de vie des beans Spring (initialisation/destruction) et la gestion des dépendances entre composants via la configuration Java.

---

## 1. Ajout de l'API des Annotations
* Ajoutez la dépendance Maven suivante au module **"core"** :
    * **Group ID :** `jakarta.annotation`
    * **Artifact ID :** `jakarta.annotation-api`
> **Pourquoi ?** Depuis Java 9, les annotations de cycle de vie ne sont plus incluses par défaut dans le JDK standard.

## 2. Injection de dépendance dans CacheDatabase
* Modifiez la classe `CacheDatabase` pour y ajouter une dépendance vers `ModelGenerator` en qualifiant le bean pour récupérer l'implémentation `RandomModelGenerator` et pas `EmptyModelGenerator`.
* **Configuration :** Mettez à jour votre classe `@Configuration`. La méthode qui produit le bean `CacheDatabase` doit maintenant recevoir un `RandomModelGenerator` en paramètre pour l'injecter.

## 3. Initialisation du Bean (`@PostConstruct`)
* Créez une méthode d'initialisation dans `CacheDatabase`.
* Annotez-la avec `@PostConstruct`.
* **Logique :** Cette méthode doit remplir automatiquement le cache avec une liste de **3 Secteurs** dès que le bean est prêt.

## 4. Destruction du Bean (`@PreDestroy`)
* Ajoutez une méthode de nettoyage dans `CacheDatabase`.
* Annotez-la avec `@PreDestroy`.
* **Logique :** Simulez une libération des ressources (ex: vider la liste).
    * Affichez un message dans la console (Log) pour confirmer que Spring ferme correctement le composant.

## 5. Tests et Validation
* Créez une classe de test dédiée pour `CacheDatabase`.
* **Vérifications attendues :**
    * Le bean est correctement injecté.
