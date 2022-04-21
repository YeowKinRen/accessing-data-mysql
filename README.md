# accessing-data-mysql

Basic Spring Data JPA with MySQL Database

```
mysql> create database db_example; -- Creates the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
```

You can perform RESTful API requests such as GET, POST, PUT, PATCH, and DELETE requests via Postman or cURL commands. The following are a sample of requests and responses.

#### GET

`curl http://localhost:8080/demo/all`

```
{"id":1,"name":"julio.fletcher@example.com","email":"Julio Fletcher"},{"id":2,"name":"alison.grant@example.com","email":"Alison Grant"},{"id":3,"name":"magdalena.campos@example.com","email":"Magdalena Campos"},{"id":4,"name":"alfons.stemmler@example.com","email":"Alfons Stemmler"},{"id":5,"name":"milana.martin@example.com","email":"Milana Martin"}
```

You can implement the requests with [Projection](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections) and/or [Pagination and Sorting](https://docs.spring.io/spring-data/rest/docs/2.0.0.M1/reference/html/paging-chapter.html#:~:text=4.-,Paging%20and%20Sorting,-4.1%C2%A0Paging).

#### POST

`curl --data "name=Yeow&email=yeow@example.com" http://localhost:8080/demo/add"`

```
```

Notes
--------

### Spring Boot Annotations

* ```@SpringBootApplication``` - combinations of three annotations ```@EnableAutoConfiguration```, ```@ComponentScan```, and ```@Configuration```

⋅⋅⋅ ```@Configuration```: Tags the class as a source of bean definitions for the application context.

⋅⋅⋅ ```@EnableAutoConfiguration```: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

⋅⋅⋅ ```@ComponentScan```: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.

<!-- ```configure(HttpSecurity http)```

- used for configuration of web-based security at a resource level, based on a selection match. Requests will be allowed to be accessed from the Spring Security Filter Chain.

```configure(WebSecurity web)```

- used for configuration settings that impact global security (ignore resources, set debug mode, by-pass the Spring Security Filter Chain, reject requests by implementing a custom firewall definition).

```configure(AuthenticationManagerBuilder auth)```

- Allows for easily building in memory authentication, LDAP authentication, JDBC based authentication, adding UserDetailsService, and adding AuthenticationProvider's.

[HTTPSecurity vs WebSecurity](https://ravthiru.medium.com/springboot-security-configuration-using-httpsecurity-vs-websecurity-1a7ec6a23273)<br />
[Configure](https://stackoverflow.com/questions/22998731/httpsecurity-websecurity-and-authenticationmanagerbuilder#:~:text=General%20use%20of,passwordEncoder(new%20BCryptPasswordEncoder())%3B%0A%20%20%20%20%20%7D)<br />
[Spring Security](https://boudhayan-dev.medium.com/demystifying-spring-security-setup-e0491acc7df7)<br /> -->

Resource
--------

- [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

<!-- 
- [HTTPSecurity vs WebSecurity](https://ravthiru.medium.com/springboot-security-configuration-using-httpsecurity-vs-websecurity-1a7ec6a23273)
- [Configure](https://stackoverflow.com/questions/22998731/httpsecurity-websecurity-and-authenticationmanagerbuilder#:~:text=General%20use%20of,passwordEncoder(new%20BCryptPasswordEncoder())%3B%0A%20%20%20%20%20%7D)
- [Spring Security](https://boudhayan-dev.medium.com/demystifying-spring-security-setup-e0491acc7df7) -->
