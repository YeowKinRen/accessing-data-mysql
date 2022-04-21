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

You can implement the requests in conjunction with [Projection](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections) and/or [Pagination and Sorting](https://docs.spring.io/spring-data/rest/docs/2.0.0.M1/reference/html/paging-chapter.html#:~:text=4.-,Paging%20and%20Sorting,-4.1%C2%A0Paging).

#### POST

`curl --data "name=Yeow&email=yeow@example.com" http://localhost:8080/demo/add"`

```
```

### DELETE

`curl -X DELETE http://localhost:8080/demo/delete/1`

```
USER_ID: 1 DELETED
```

### PUT

`curl -X PUT -d "name=newName" http://localhost:8080/demo/put/1`

```
USER_ID: 1 UPDATED
```

Notes
--------

### Spring Boot Annotations

- ```@SpringBootApplication```: combinations of 3 annotations namely ```@EnableAutoConfiguration```, ```@ComponentScan```, and ```@Configuration```
  - ```@Configuration```: Tags the class as a source of bean definitions for the application context.

  - ```@EnableAutoConfiguration```: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet.

  - ```@ComponentScan```: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.

- ```@Component```: annotation that allows Spring to automatically detect our custom beans

- ```@Controller```, ```Service```, ```Repository```: specialized form of ```@Component``` and annotations for controller as front controllers and the management of the REST interface, service as business logic implementation
and repository as the access of database and the storage of the entity beans such as CRUD operations

- ```@RestController```: combination of the ```@Controller``` and ```@ResponseBody``` annotation


Resource
--------

- [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)


