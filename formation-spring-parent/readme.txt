1 - Créer une classe EmptyModelGenerator qui implémente ModelGenerator et qui renvoie des objets vides
2 - Créer une classe de test d'intégration Spring pour le EmptyModelGenerator
3 - Essayer d'injecter par attribut un bean de type ModelGenerator. Constatez l'erreur dans la log.
4 - Maîtriser la dépendance retournée via Spring grâce à l'annotation Primary. Vérifier avec un test que vous récupérer le bon bean.
5 - Maîtriser la dépendance retournée via Spring grâce à l'annotation Qualifier. Tester.
6 - Récupérer l'ensemble des beans implémentant ModelGenerator dans une liste.
7 - Vérifier avec un test.