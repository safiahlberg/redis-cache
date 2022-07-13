# Cache poc
Experimenting with Spring Boot caching and Redis as Cache DB.

## Structure
The [Customer](src/main/java/com/wixia/rediscache/persistence/CustomerEo.java)
and [Item](src/main/java/com/wixia/rediscache/persistence/ItemEo.java)
are domain objects, in this case represented by JPA-data.
A customer has a relation to a list of items. A H2 instance is set up and
populated with a few example objects. The Customer has a overridden
repository, which can simulate delays dynamically.

## Copied code from:
* https://docs.liquibase.com/tools-integrations/springboot/springboot.html
* https://spring.io/guides/gs/accessing-data-jpa/
* https://github.com/spring-projects/spring-hateoas-examples/tree/main/simplified
* https://docs.spring.io/spring-hateoas/docs/current/reference/html/
* https://stackoverflow.com/questions/11880924/how-to-add-custom-method-to-spring-data-jpa
* https://www.baeldung.com/spring-boot-redis-cache
* https://www.baeldung.com/spring-data-rest-intro
* https://www.baeldung.com/spring-data-rest-relationships
* https://www.baeldung.com/jpa-unique-constraints
