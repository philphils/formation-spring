# TP 2 (Suite) : Environnement, Properties et Profils

**Objectif :** Apprendre à gérer la configuration externe via des fichiers `.properties` et à conditionner la création de beans selon des profils (Environnements).

---

## 1. Chargement des propriétés
* Localisez le fichier `database.properties` dans `fr/insee/config`.
* Au niveau de votre classe `CoreApplication`, utilisez l'annotation `@PropertySource` pour charger ce fichier dans le contexte Spring.

## 2. Exploration de l'objet Environment
* Injectez l'objet `Environment` dans votre classe de lancement.
* **Affichage console :**
    * Affichez la valeur de la propriété `fr.insee.database.database_pool.url`.
    * Affichez la liste des **profils actifs** (via `env.getActiveProfiles()`).

## 3. Configuration de la Source de Données
* Créez une nouvelle classe de configuration : `DatasourceConfiguration`.
* **Package :** `org.formation.spring.core.persistence.database`.
* Marquez cette classe avec `@Configuration`.

## 4. Injection de valeurs (`@Value`)
* Dans `DatasourceConfiguration`, utilisez l'annotation `@Value` pour injecter les différentes propriétés du fichier dans des attributs de classe (URL, Driver, User, Password).

## 5. Création du Bean DataSource
* Déclarez une méthode `@Bean` qui retourne un objet de type `BasicDataSource` (provenant de la librairie Commons DBCP).
* Configurez cet objet en utilisant les attributs injectés à l'étape précédente.
* **Vérification :** Lancez `CoreApplication` et vérifiez que le bean `basicDataSource` est bien présent dans votre liste de beans.

## 6. Utilisation des Profils
* Marquez le bean `BasicDataSource` (ou la classe de configuration entière) comme appartenant au profil **"prod"** via l'annotation `@Profile`.
* **Test :** Relancez `CoreApplication`. Le bean doit maintenant avoir disparu de la liste (car aucun profil n'est actif par défaut).

## 7. Activation de Profil
* Activez le profil **"prod"** en utilisant l'une des méthodes vues en cours :
    * Argument VM : `-Dspring.profiles.active=prod`
    * Via l'API du contexte dans le `main`.
* **Vérification :** Le bean doit de nouveau apparaître.

## 8. Test d'intégration et Profils
* Créez une classe de test Spring pour vérifier la présence du bean `BasicDataSource` (tentez de l'injecter avec `@Autowired`).
* **Observation :** Lancez le test tel quel. Il doit échouer car le contexte de test ne connaît pas le profil "prod".

## 9. Activation du profil de Test
* Utilisez l'annotation `@ActiveProfiles("prod")` sur votre classe de test.
* Relancez le test et vérifiez qu'il passe désormais avec succès (✅).