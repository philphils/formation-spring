# Le scope

--
# Notion de « scope »

* __Le scope\, c\-à\-d la « portée » d’un bean\, est comparable à la notion de durée de vie__

* __On a vu que l’injection de dépendances permet la mise en œuvre du pattern Singleton__

* __Le scope des beans est configuré en mode « singleton » dans la grande majorité des cas__

--
# Notion de « scope »

* __Mais dans certains cas\, les données portées par le bean ne doivent pas être partagées__

* __Typiquement : dans un contexte multi\-thread\, pour des données liées à la session utilisateur__


--
# `@Scope`

* __Spring propose une annotation `@Scope` pour définir le scope du bean__
* __Par défaut le scope est `singleton`, ie instance unique avec une durée de vie infinie__

--
# `@Scope`

* __Plusieurs valeurs peuvent être utilisées pour définir `@Scope(scopeName= « ... »)` :__
    * __`prototype` : Création d’un nouveau bean à chaque demande__
    * __`request` : Durée de vie correspondant à la requête HTTP__
    * __`session` : Durée de vie correspondant à la durée de la session HTTP__

--
# Cas d’utilisation

* __La modification du scope doit correspondre à des cas relativement rares__

* __Par exemple\, si les classes Controller portent des données utilisateurs\, elles doivent absolument redéfinir un scope__

* __Mais on évite en général de faire porter aux beans des données liées au contexte\, car on nuit alors à l’immutabilité de l’objet__

--
# Cas d’utilisation

* __Cela pose typiquement des problèmes dans un contexte multi\-thread__

* __En XML\, on définira le scope via l’attribut `scope=`__


--
# Scope et Spring Batch

* __Pour ses besoins propres\, Spring Batch propose 2 scopes particuliers__

* __`@StepScope` : Permet de conserver un bean en vie pendant la durée d’une étape d’un job__

* __`@JobScope` : Permet de conserver un bean en vie pendant la durée d’un job__

--
# Scope et Spring Batch

* __Peut\-être utile pour en cas de répétition de job sans redémarrage ou de job complexe__

* __En cas de besoin particulier il est possible de définir ses propres scopes en implémentant la classe `Scope`__