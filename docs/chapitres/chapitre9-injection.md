# Injection
# Constructor VS Setter

--
# Autowiring : Attributs/Setter
# VS Constructeur

* __Face aux multiples possibilités offertes par Spring : Quelles sont les pratiques recommandées ?__

* __En mode annotation/Java\-based\, l’autowiring au niveau attribut est peu verbeux : une seule annotation\, pas besoin de définir de constructeur__

--
# Autowiring : Attributs/Setter
# VS Constructeur

* __Pas de problème avec les dépendances circulaires__

* __Le recours à l’injection au niveau attribut\, ou setter est tentant…__

* __Mais en termes de bonnes pratiques\, c’est l’injection de dépendances via le constructeur qui est recommandée__

--
# Pourquoi préférer
# le mode constructeur ?

* __Gestion robuste des dépendances : l’objet est créé avec toutes ses dépendances d’emblée\, pas d’états inconsistants ou partiels__

* __Lisibilité du code : les dépendances entre les classes apparaissent clairement à travers le constructeur__

--
# Pourquoi préférer
# le mode constructeur ?

* __Immutabilité : permet de déclarer les attributs en `final`. C’est une propriété recherchée, elle permet par ex\. de disposer d’objets multi\-thread et plus simple à tester__

* __Testabilité : l’injection par constructeur permet de tester plus facilement\, par exemple en injectant les mocks des dépendances__

* __Découplage : pas de setter déclaré\, donc couplage plus faible__