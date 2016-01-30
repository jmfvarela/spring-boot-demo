package io.galileo;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.galileo.domain.User;
import io.galileo.repository.UserRepository;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@IntegrationTest("localhost:8080") 
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;
 
    @Before
    public void setup() throws Exception {
    	
		User user= User.builder()
				.username("admin1")
				.password("admin1")
				.enabled(true)
				.authority("ROLE_ADMIN")
				.name("Default")
				.surname("Administrator1")
				.build();
		
		userRepository.save(user);
    }
 
    @Test
    public void test1() throws Exception {
  
    	when().
        	get("/users").
        then().
        	statusCode(HttpStatus.SC_OK)
        	.body("[0].username", equalTo("admin"))
    		.body("[1].username", equalTo("admin1"));

    }
}
