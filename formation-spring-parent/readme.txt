1 - Charger dans le contexte Spring les properties du fichier database.properties (situé dans fr/insee/config) avec l'annotation appropriée, au niveau de votre classe CoreApplication
2 - Récupérer l'objet Environment et afficher dans la console la valeur de la properties "fr.insee.database.database_pool.url". Récupérer et afficher la liste des profiles actifs (aucun pour l'instant normalement).
3 - Créer une classe de configuration nommée DatasourceConfiguration dans le package org.formation.spring.core.persistence.database
4 - Injecter les valeurs des properties du fichier database.properties dans des attributs
5 - Créer un bean de type BasicDataSource. Configurer le avec les properties : url de connexion, le nom du driver (driveClassName), le nom et le mot de passe de l'utilisateur. Vérifier en lançant CoreApplication qu'il apparaît bien dans la liste des bean.
6 - Définisser ce bean comme appartenant au Profile "prod". Lancer CoreApplication, vérifier que le bean n'apparaît plus.
7 - Configurer le lancement de CoreApplication en activant le profile "prod" avec les différentes méthodes vues en cours. Vérifier que le bean apparaît à nouveau.
8 - Créer un test Spring pour vérifier la présence de ce bean au sein du contexte (par injection d'attribut par ex.). Lancer le test. Constater le plantage du test.
9 - Activer le profile "prod" au niveau de la classe de test. Vérifier que le test passe.