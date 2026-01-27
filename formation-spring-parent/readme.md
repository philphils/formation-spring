# TP 2 (Suite) : Injection de dépendances et Qualificateurs

**Objectif :** Maîtriser l'injection d'interfaces, la résolution des ambiguïtés (conflits de beans) et l'injection de collections avec Spring.

---

## 1. Création d'une nouvelle implémentation
* Créez une classe `EmptyModelGenerator`.
* Celle-ci doit implémenter l'interface `ModelGenerator`.
* Faites en sorte qu'elle renvoie des objets vides (ou une implémentation minimale).
* **Action :** Marquez cette classe avec l'annotation appropriée pour qu'elle soit détectée par Spring.

## 2. Configuration du test Spring
* Créez une nouvelle classe de test JUnit pour `EmptyModelGenerator`.
* Configurez-la pour qu'elle utilise le contexte Spring (chargement des annotations).

## 3. Test d'injection par Interface
* Dans votre classe de test, déclarez un attribut de type `ModelGenerator` (l'interface).
* Créez une méthode de test qui vérifie simplement que cet attribut n'est pas nul (`assertNotNull`).
* **Observation :** Lancez le test. Vous devriez obtenir une erreur de type `NoUniqueBeanDefinitionException`. Observez bien le message dans la log.

## 4. Gestion de l'ambiguïté (Qualifiers)
* Testez les deux solutions vues en cours pour résoudre le conflit entre `RandomModelGenerator` et `EmptyModelGenerator` :
    1.  **Solution 1 :** Utilisation de l'annotation `@Primary` sur la classe choisie.
    2.  **Solution 2 :** Utilisation de l'annotation `@Qualifier` au niveau du point d'injection.
* **Vérification :** Validez avec un test que le bean injecté est bien du type attendu (`instanceof EmptyModelGenerator`).

## 5. Injection multiple (Lists)
* Modifiez votre test pour injecter non pas un seul bean, mais une liste de tous les beans implémentant l'interface :  
  `@Autowired List<ModelGenerator> generators;`
* **Vérification :** Créez un test pour valider que la liste contient bien les deux implémentations créées.