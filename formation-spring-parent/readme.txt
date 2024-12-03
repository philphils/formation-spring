TP 2
1 - Ajouter la librairie maven "spring-context" au module "core" (pas besoin de spécifier la version cf pom parent)
2 - Identifier les différents beans à déclarer avec des annotations dérivées de @Component. Positionner celles-ci. (laisser pour l'instant les classes CacheDatabase et RandomModelGenerator)
3 - Créer un package configuration. A l'intérieur de celui-ci créer une classe @Configuration qui produise les beans pour CacheDatabase et RandomModelGenerator.
4 - Créer une classe CoreApplication à la racine du projet avec une méthode main. Créer un contexte d'application. Lister dans la console le nom des beans et leur type.
5 - Modifier le nom des beans CacheDatabase et SecteurDao. Vérifier en lançant votre classe CoreApplication.
6 - Supprimer la référence au singleton dans la classe CacheDatabase. Constater les erreurs de complilation dans les classes de test du package "persistence.dao". 
7 - Ajouter la librairie maven "spring-test" au module core.
8 - Mettre en place le chargement du contexte par annotation pour les 3 tests en erreur.
9 - Injecter par attribut les dépendances dans les classes de tests. Vérifier le bon fonctionnement en lançant les tests.