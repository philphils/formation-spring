TP 2
1 - Ajouter la dépendance maven "spring-context" au module "core" (pas besoin de spécifier la version cf pom parent)
2 - Identifier les différents beans à déclarer avec des annotations dérivées de @Component positionner celles-ci (laisser pour l'instant les classes CacheDatabase et RandomModelGenerator)
3 - Créer un package configuration. A l'intérieur de celui-ci créer des classes @Configuration qui produise les beans pour CacheDatabase et RandomModelGenerator.
4 - Créer une classe CoreApplication à la racine du projet avec une méthode main. Créer un contexte d'application. Lister dans la console le nom des beans et leur type.
5 - Modifier le nom des beans CacheDatabase et SecteurDao. Vérifier en lançant votre classe CoreApplication.
6 - Supprimer la référence au singleton dans la classe CacheDatabase
7 - Corriger les erreurs de compilation dans les classes de test du package "persistence.dao" en injectant la dépendance au niveau attribut