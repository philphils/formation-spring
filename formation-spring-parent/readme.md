# TP 2 : Mise en œuvre de l'Inversion de Contrôle (IoC) avec Spring

**Objectif :** Migrer une application standard vers le framework Spring en utilisant les annotations et la configuration Java.

---

## 1. Gestion des dépendances
* **Ajout de Spring Core :** Dans le fichier `pom.xml` du module **"core"**, ajoutez la dépendance Maven `spring-context`.
    > **Note :** Ne spécifiez pas la version, celle-ci est gérée par le `pom` parent.

## 2. Déclaration des Beans par Annotations
* Identifiez les différents composants de votre application.
* Positionnez les annotations dérivées de `@Component` (`@Service`, `@Repository`, etc.) sur les classes appropriées.
* **Consigne :** Laissez de côté pour l'instant les classes `CacheDatabase` et `RandomModelGenerator`.

## 3. Configuration Java (JavaConfig)
* Créez un package nommé `configuration`.
* À l'intérieur, créez une classe annotée avec `@Configuration`.
* Déclarez dans cette classe les méthodes nécessaires pour produire les beans des classes :
    * `CacheDatabase`
    * `RandomModelGenerator`

## 4. Initialisation du Contexte
* Créez une classe `CoreApplication` à la racine du projet munie d'une méthode `main`.
* Initialisez un contexte d'application Spring.
* **Vérification :** Codez une boucle pour lister dans la console le **nom** et le **type** de tous les beans présents dans le contexte.

## 5. Personnalisation des Beans
* Modifiez manuellement le nom des beans `CacheDatabase` et `SecteurDao` (via les paramètres de l'annotation).
* Relancez la classe `CoreApplication` pour valider que les noms ont bien été mis à jour.

## 6. Suppression du Singleton Manuel
* Dans la classe `CacheDatabase`, supprimez toute la logique liée au pattern Singleton (attribut statique, méthode `getInstance`).
* **Observation :** Constatez les erreurs de compilation qui apparaissent dans les tests du package `persistence.dao`.

## 7. Configuration des Tests
* Ajoutez la dépendance Maven `spring-test` au module **"core"**.

## 8. Chargement du Contexte dans les Tests
* Pour les 3 classes de tests actuellement en erreur, configurez le chargement automatique du contexte Spring par annotation (ex: `@ExtendWith(SpringExtension.class)` ou `@SpringJUnitConfig`).

## 9. Injection et Validation
* Utilisez l'injection par attribut (`@Autowired`) pour fournir les dépendances nécessaires aux classes de tests.
* Lancez les tests et vérifiez que tout est fonctionnel.