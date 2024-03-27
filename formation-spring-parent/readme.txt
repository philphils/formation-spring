1 - Créer une classe EmptyModelGenerator qui implémente ModelGenerator et qui renvoie des objets vides
2 - Créer une classe de test Spring pour le EmptyModelGenerator.
3 - Déclarer le type de l'attribut que vous testez avec l'interface ModelGenerator. Constatez l'erreur dans la log.
4 - Maîtriser la dépendance retournée via Spring grâce à l'annotation Primary. Vérifier avec un test que vous récupérer le bon bean.
5 - Maîtriser la dépendance retournée via Spring grâce à l'annotation Qualifier. Tester.
6 - Récupérer l'ensemble des beans implémentant ModelGenerator dans une liste.
7 - Vérifier avec un test.