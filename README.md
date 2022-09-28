# Cache poc
Experimenting with Spring Boot caching and Redis as Cache DB.

## TL;DR
Cache is implemented with Spring Frameworks annotations on
the service implementations. It could be done elswhere, but this
was a starting point...
Redis is configured as a central cache server.

## Structure
### Domain layer
The [Customer](src/main/java/com/wixia/domain/Customer.java)
and [Item](src/main/java/com/wixia/domain/Item.java)
are domain objects, in this case represented by JPA-data.
A customer has a relation to a list of items. A H2 instance is set up and
populated with a few example objects.
The Repository interfaces have an
[aspect](src/main/java/com/wixia/domain/repositorydelay/RepositoryDelayAspect.java) applied,
which can simulate delays dynamically.

### Service layer
The [service layer](src/main/java/com/wixia/service/package-info.java)
is a layer between the controller and domain, and this is
where the cache capability is applied.
The service classes
[CustomerService](src/main/java/com/wixia/service/CustomerService.java)
and
[ItemService](src/main/java/com/wixia/service/ItemService.java)
are responsible for calling the repositories in the domain
and the methods within the service implementations are
annotated with Spring Frameworks cache annotations.

## Redis
An instance of Redis is available as a docker container
which can be started with
```shell
$ docker-compose up
```
The definition is in [docker-compose.yml](docker-compose.yml)

The client basic configuration for Redis is in
[application.properties](src/main/resources/application.properties)
and further configuration is done in
[CacheConfig](src/main/java/com/wixia/configuration/CacheConfig.java)
where a
[custom error handler](src/main/java/com/wixia/configuration/RedisCacheErrorHandler.java) takes care of the case when
Redis is unavailable.

## Copied code from:
* https://docs.liquibase.com/tools-integrations/springboot/springboot.html
* https://spring.io/guides/gs/accessing-data-jpa/
* https://github.com/spring-projects/spring-hateoas-examples/tree/main/simplified
* https://docs.spring.io/spring-hateoas/docs/current/reference/html/
* https://stackoverflow.com/questions/11880924/how-to-add-custom-method-to-spring-data-jpa
* https://www.baeldung.com/spring-boot-redis-cache
* https://www.baeldung.com/jpa-unique-constraints
