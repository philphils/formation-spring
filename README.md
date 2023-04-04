# Formation Spring

## Objectifs

Spring est un ensemble de frameworks Java utilisé à l’Insee et ailleurs depuis maintenant plus de 10 ans. La "galaxie" Spring s'est constituée autour d'un noyau initial  permettant de mettre en œuvre et de gérer « l’injection de dépendance », principe permettant de réduire l'adhérence des différentes couches du code applicatif (notion vue plus en profondeur dans la formation). Autour de ce noyau initial se sont peu à peu greffés de nombreux autres outils.

Un exposé exhaustif des frameworks Spring est impossible et ne répondrait pas au besoin de formation des débutants. Voici donc les différents modules de Spring que cette formation se propose d'aborder dans l'ordre :

- Spring IoC (injection de dépendances)
- Spring Boot (initialisation et configuration)
- Spring Data (gestion de la persistance)
- Spring REST (mise en place d'API)

## Organisation

La formation s'étalera sur 3 journées. Un exposé exhaustif des fonctionnalités des modules Spring est impossible dans ce temps. L'objectif sera donc plus d'aborder les notions foncdamentales des modules Spring cités plus haut, pour éviter que les développeurs parviennent à se repérer en abordant un projet utilisant Spring.

## Travaux pratiques

Un TP sera réalisé au fur et à mesure de chaque module. En partant d'un projet sans Spring nous introduirons l'injection de dépendances. Nous exposerons les possibilités de configuration via XML pour ensuite lui préférer une configuration via annotations.

Nous créérons une application blanche via Spring-Boot et l'interface Spring-Initializer. Nous exposerons les principaux avantages d'une configuration via Spring-Boot plutôt qu'une configuration maison, l'ajout des "starters", le déploiement d'un serveur Tomcat embarqué, etc.

Enfin, nous mettrons en oeuvre de la persistance avec Spring-Data. Spring-Data suppose la maîtrise d'Hibernate et du JPQL, mais son abord assez intuitif devrait pouvoir se faire. Nous concluerons par la mise en place d'une API avec Spring-REST pour exposer les données et permettre la modification de celles-ci.