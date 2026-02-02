# Initialisation et 
# destruction des beans


--
# Le besoin

* __On peut avoir besoin de configurer un bean après sa création__
* __Exemples typiques :__
    * __Accès à une base de données__
    * __Accès à une API__
    * __Chargement de données de cache__

--
# Le besoin

* __On peut aussi avoir besoin d’effectuer des traitements avant la destruction d’un bean :__
    * __Fermeture de connexions__
    * __Nettoyage dossiers de fichiers temporaires__
    * __Libération sockets réseau__



--
# `@PostConstruct` et `@PreDestroy`

* __2 annotations permettant de configurer ces méthodes directement dans le bean__

* __Ces annotations ne sont pas propres à Spring mais définies par la JSR 250__

* __A partir de java 11, il est nécessaire d’ajouter des dépendances :__  

    * __`javax.annotation-api` pour Spring 5 et versions antérieures__

    * __`jakarta.annotation-api` pour Spring 6 et versions ultérieures__

--
# `@PostConstruct` et `@PreDestroy`

* __Exemple de configuration connexion API :__

```java
@PostConstruct
public void init() {
  restTemplate.getInterceptors().add(
        new ClientHttpRequestInterceptor() {
    @Override
    public ClientHttpResponse intercept
        (HttpRequest request, byte[] body, 
        ClientHttpRequestExecution execution)
         throws IOException {
      request.getHeaders().add("Authorization", "Bearer " + apiKey);
      return execution.execute(request, body);
    }
  });
}
```

--
# Méthodes via interface et XML

* __Spring propose aussi 2 interfaces : `InitializingBean` et `DisposableBean`__

* __Permettent de définir respectivement les méthodes : `afterPropertiesSet()` et `destroy()`__

* __En XML\, on pourra définir les attribut : `init-method=` et `destroy-method=`__

--
# `BeanPostProcessor`

* __Enfin\, pour des besoins de configuration plus poussés\, on pourra définir des `BeanPostProcessor`__

* __Les `BeanPostProcessor` permettent d'intervenir avant et après l'initialisation des beans__

* __Ils sont utiles pour ajouter des fonctionnalités transversales ou modifier le comportement des beans, par exemple pour étendre ou modifier les comportements des beans (proxy\, wrapper…\)__


--
# `BeanPostProcessor`

```java
@Component
public class PrefixBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyService) {
            System.out.println("Avant initialisation : " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyService) {
            System.out.println("Après initialisation : " + beanName);
        }
        return bean;
    }
}
```