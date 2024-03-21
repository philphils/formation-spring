1 - Ajouter la dépendance maven jakarta.annotation-api au module "core"
2 - Ajouter une dépendance RandomModelGenerator à CacheDatabase (Attention : y compris dans la méthode de génération du bean)
3 - Ajouter une méthode d'initialisation à l'instanciation de la cacheDatabase qui ajoute une liste de 3 Secteurs
4 - Ajouter une méthode PreDestroy à la fermeture de la cacheDatabase libérant les ressources et affichant un message dans la log
5 - Tester avec une classe dédiée la CacheDatabase