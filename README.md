
Download and install Spring Boot CLI: 
http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-installing-the-cli
You can do the manual installation. Remember to set the PATH variable if you want to run it more easily.

Check the installation with:
spring --version

You can get command completion if you run the Spring Boot CLI shell:
spring shell

Create a new proyect with Spring Initializr:
http://start.spring.io

This app has been created with these data:
* Type: Gradle Project
* Spring Boot version: 1.3.2
* Group: io.galileo
* Artifact: demo
* Name: demo
* Descripcion: Demo project for Spring Boot
* Package Name: io.galileo
* Packaging: Jar
* Java Version: 1.8
* Languaje: Java
* Selected Starters: Web, AOP, Cache, DevTools, Validation, Session, Lombok, Rest Repositories, JDBC, JPA, Velocity, HSQLDB, MySQL, Mail, Actuator

Create a simple controller in
´´´Java
package io.galileo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/hello/")
public class HelloWorldController {

	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody String get(@PathVariable String name) {

		return "Hello " + name;
	}
}
´´´

Running de application:
gradle bootRun

Alternatively, you can build the project with Gradle and run it with java at the command line:
gradle build
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar

Check de App in:
http://localhost:8080/hello/world


Eclipse: 
* File > Import > Gradle project
* Run As > Spring Boot App

http://localhost:8080/hello/world



Próximos pasos:
- Abrir con eclipse
- Entidad con validaciones y lombok
- Crear controlador CRUD json
- Crear repositorio rest CRUD de bbdd HSQLDB mediante JPA 
- Bootstrap de carga de datos
- Devtools
- Spring security (//	compile('org.springframework.boot:spring-boot-starter-security'))
ver:
https://github.com/spring-projects/spring-data-examples/tree/master/rest/security


A probar a futuro:
- Cache
- Plantillas velocity
- AOP / AspectJ
- Session?
- Mail
- Actuator
- MySQL
- JDBC



Instalar lombok en eclipse simplemente descargando lombok.jar de:
https://projectlombok.org/download.html
Y ejecutando:
java -jar lombok.jar install $eclipseDir
Recuerda salir de eclipse y volver a entrar.

