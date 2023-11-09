# JDevs

## Requirement
* Java 18
* MySQL 8.0

## Development

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.yml` file to override settings for development.

Lombok must be supported by your IDE. For IntelliJ install the Lombok plugin and enable annotation processing.

## Build

The application can be built using the following command:

```
mvnw clean install
```

Start your application with the following command - here with the profile `production`:

```
mvnw spring-boot:run
```

After starting the application it is accessible under `localhost:8080`.

Login with credentials that you can find in the database `users` table.

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
