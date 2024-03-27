1 - Charger les properties du fichier database.properties (situé dans fr/insee/config) avec l'annotation appropriée, au niveau de votre classe CoreApplication
2 - Récupérer l'objet Environment et afficher dans la console la valeur de la properties "fr.insee.database.database_pool.url". Afficher aussi la liste des profiles actifs (aucun pour l'instant).
3 - Créer une classe de configuration nommée DatasourceConfiguration dans le package org.formation.spring.core.persistence.database
4 - Injecter les valeurs des properties du fichier database.properties dans des attributs
5 - Créer un bean Datasource en lui passant ces valeurs. Pour info voilà le code pour créer un objet Datasource :

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(urlConnexion);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

Vérifier en lançant CoreApplication qu'il apparaît bien dans la liste des bean.

6 - Définisser ce bean comme appartenant au Profile "prod". Lancer CoreApplication, vérifier que le bean n'apparaît plus.
7 - Configurer le lancement de CoreApplication en activant le profile "prod" avec l'argument -Dspring.profiles.active au niveau de la JVM. Vérifier que le bean apparaît à nouveau.
8 - Ouvrer et liser le code de la classe classe TestProdProfile située dans src/test/java. Lancer le test. Constater le plantage du test.
9 - Activer le profile "prod" au niveau de la classe de test. Vérifier que le test passe.