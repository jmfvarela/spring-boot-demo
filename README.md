# Intro 
This project has been generated with Spring Boot following theese steps:
* Download and install Spring Boot CLI from:

http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-installing-the-cli

You can do the manual installation. Remember to set the PATH variable if you want to run it more easily.

* Check the installation with:
```
spring --version
```
You can get command completion if you run the Spring Boot CLI shell:
```
spring shell
```

* Create a new project with Spring Initializr in:

http://start.spring.io

This app has been created with these data:
```
- Type: Gradle Project
- Spring Boot version: 1.3.2
- Group: io.galileo
- Artifact: demo
- Name: demo
- Descripcion: Demo project for Spring Boot
- Package Name: io.galileo
- Packaging: Jar
- Java Version: 1.8
- Languaje: Java
- Selected Starters: Web, AOP, Cache, DevTools, Validation, Session, Lombok, Rest Repositories, JDBC, JPA, Velocity, HSQLDB, MySQL, Mail, Actuator
```

* Create a simple controller in
```Java
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
```

* Running de application:
```
gradle bootRun
```

* Alternatively, you can build the project with Gradle and run it with java at the command line:
```
gradle build
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

* Check de App in:

http://localhost:8080/hello/world

* Import with Eclipse: 
```
- File > Import > Gradle project
- Run As > Spring Boot App
```

* Check de App in:

http://localhost:8080/hello/world

# Installing lombok
Download lombok.jar from:

https://projectlombok.org/download.html

Install in Eclipse with:
```
java -jar lombok.jar install $eclipseDir
```
Remember to exit from Eclipse and enter again.

# Next steps
- Devtools
- Spring security (//	compile('org.springframework.boot:spring-boot-starter-security'))
see https://github.com/spring-projects/spring-data-examples/tree/master/rest/security
- Cache
- Plantillas velocity
- AOP / AspectJ
- Session?
- Mail
- Actuator
- MySQL
- JDBC
