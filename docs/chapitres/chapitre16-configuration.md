# Configurations hybrides
--
# Mix or not mix

* __Dans l’absolu : préférable de ne pas mixer les différents types de configuration__
* __Peut être nécessaire :__
    * __pour migrer une application ancienne en XML vers une config annotation/Java based__
    * __pour avoir une séparation claire du code métier et de considérations techniques__
    * __pour utiliser XML avec certaines librairies pour gagner en lisibilité \(ex\. librairie gérant des flux Spring Batch\, Apache Camel\)__
--
# Mix or not mix

* __La documentation officielle propose alors 2 approches : https://docs.spring.io/spring-framework/reference/core/beans/java/composing-configuration-classes.html#beans-java-combining__

--
# XML Centric configuration

* __On peut choisir une configuration centrée sur les fichiers XML__

* __On va avoir un fichier XML contenant le tag :__
```xml
<context:component-scan base-package=«…»/>
```

* __On aura des beans déclarés en XML et classes annotées\, ex\. avec `@Configuration`__


--
# XML Centric configuration


* __On créera le context à partir du fichier XML avec `ClassPathXmlApplicationContext`__

* __On peut mettre en œuvre cette approche pour migrer des applications anciennes__

--
# `@Configuration` 
# centric configuration

* __On aura une configuration centrée sur des classes `@Configuration`__

* __Et on importera le contenu de fichiers de config XML dans ces classes avec `@ImportResource(«classpath:fichier.xml»)`__ 

--
# `@Configuration`
# centric configuration

* __Ce type de configuration se prête plus à l’intégration d’XML pour certaines librairies__

* __Par exemple la création des beans spring\-batch en annotation/Java\-based…__

* __Et la constitution du flux des batch en XML__

