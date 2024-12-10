1 - Créer une classe EmptyModelGenerator qui implémente ModelGenerator et qui renvoie des objets vides
2 - Créer une classe de test Spring pour le EmptyModelGenerator.
3 - Déclarer le type de l'attribut que vous testez avec l'interface ModelGenerator. Créer une méthode de test qui vérifie la non-nullité de votre attribut. Constatez l'erreur dans la log.
4 - Contrôler la dépendance récupérée via Spring avec les 2 annotations le permettant vu dans le cours. Vérifier avec un test que vous récupérer le bon bean.
5 - Récupérer l'ensemble des beans implémentant ModelGenerator dans une liste. Vérifier avec un test.