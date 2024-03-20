Intro :

L'objectif est de réaliser petit à petit une mini API-Sirene. Pour la formation Spring-Initiation nous n'utiliserons que le module core, les autres seront utilisés lors des formation Spring-Data, Spring-REST, Spring-Boot.

Pour les besoins de cette formation une base de données en mémoire a été réalisé. Il s'agit simplement de stocker les données sous la forme de collection d'objets dans une classe dédiée, la classe CacheDatabase (une vrai BDD sera mise en place avec Spring-Data) qui est un singleton.

La librairie JavaFaker est utilisée pour générer des données aléatoirement.

TP 1 
1 - Repérer les dépendances d'EntrepriseServiceImpl et RandomModelGenerator
2 - Mettre en place de l'injection de dépendance via constructeur sur ces classes
3 - Dans les classes AdresseCacheDatabaseDaoImpl, EntrepriseCacheDatabaseDaoImpl, SecteurCacheDatabaseDaoImpl, remplacer de même l'appel au singleton CacheDatabase par une injection de dépendance via le contructeur
4 - On cherche à mettre en place des mocks sur l'ensemble des dépendances pour tester EntrepriseServiceImplTest. Regarder l'exemple de mock, créer les autres mocks. Créer l'instance d'EntrepriseServiceImplTest avec ses dépendance mockés dans la méthode setUp().
5 - Regarder l'exemple d'utilisation des mocks et résoudre les FIXME pour tester proprement cette classe.