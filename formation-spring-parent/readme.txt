Intro :

L'objectif est de réaliser petit à petit une mini API-Sirene. Pour la formation Spring-Initiation nous n'utiliserons que le module core, les autres seront utilisés lors des formation Spring-Data, Spring-REST, Spring-Boot.

Pour les besoins de cette formation une base de données en mémoire a été réalisé. Il s'agit simplement de stocker les données sous la forme de collection d'objets dans une classe dédiée. La classe CacheDatabase qui est un singleton contient donc plusieurs liste d'objets. Une vrai BDD sera mise en place avec Spring-Data.

La librairie JavaFaker est utilisée pour générer des données aléatoirement.

Dans ce premier TP nous partons d'une application "mal-codée" ou l'instanciation des composants est fait dans les classes elles-mêmes à des endroits alétoires. L'objectif est donc de la restructurer pour préparer la mise en oeuvre de Spring. Une fois l'injection de dépendance mise en oeuvre, nous pourrons dans le TP suivant déployer le framework Spring proprement.

Les deux dernières questions montrent l'intérêt de l'injection de dépendance pour "mocker" des composants. Les Mocks sont un outils pour mettre en oeuvre les tests unitaires abordés dans la formation "Tests". Pour plus d'info au besoin : https://blog.ippon.fr/2020/06/05/mockito-pour-les-debutants/

TP 1 
1 - Repérer les dépendances d'EntrepriseServiceImpl et RandomModelGenerator
2 - Mettre en place de l'injection de dépendance via constructeur sur ces classes
3 - Dans les classes AdresseCacheDatabaseDaoImpl, EntrepriseCacheDatabaseDaoImpl, SecteurCacheDatabaseDaoImpl, supprimer l'appel au singleton CacheDatabase.access, et le remplacer par une injection de dépendance à CacheDatase via le contructeur

On cherche à mettre en place des mocks sur l'ensemble des dépendances pour vérifier qu'on passe bien ou pas par les méthodes des composants :
4 - Considérer la classe EntrepriseServiceImplTest. Regarder l'exemple de mock, créer les autres mocks. Créer l'instance d'EntrepriseServiceImplTest avec ses dépendance mockés dans la méthode setUp().
5 - Regarder l'exemple d'utilisation des mocks et résoudre les FIXME pour tester proprement cette classe.