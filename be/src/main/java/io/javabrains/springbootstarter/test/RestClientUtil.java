package io.javabrains.springbootstarter.test;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties.Template;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import io.javabrains.springbootstarter.domain.Customer;
import io.javabrains.springbootstarter.domain.PartyType;
import io.javabrains.springbootstarter.domain.Person;
import io.javabrains.springbootstarter.domain.Role;
import io.javabrains.springbootstarter.domain.RoleType;
import io.javabrains.springbootstarter.security.User;

/*

Use the following class to configure unit tests as per the following link:

https://www.baeldung.com/spring-data-rest-relationships
 
*/
 

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplication.class, 
 webEnvironment=WebEnvironment.DEFINED_PORT)
public class RestClientUtil {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    private Template template;
 
    private static String PERSON_ENDPOINT = "http://localhost:8090/Person";
    private static String CUSTOMER_ENDPOINT = "http://localhost:8090/Customer";
 
    private static String CUSTOMER_GIVEN_NAME_EN = "Daniel";
    private static String CUSTOMER_FAMILY_NAME_EN = "Mackie";
    private static String CUSTOMER_NAME_CN = "丹尼爾麥基";
    private static String CUSTOMER_ID = "0123456789";
    private static Date CUSTOMER_START_DATE = new Date();

	@Test
	public void addPersonCustomer() {
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 RestTemplate restTemplate = new RestTemplate();
		 Person objPerson = new Person();
		 User objUser = new User();
		 Customer objCustomer = new Customer();
		 PartyType objPartyType = new PartyType();
		 RoleType objRoleType = new RoleType();
		 objRoleType.setRoleTypeId((long)1);
		 objPartyType.setPartyTypeId((long) 1);
		 objPerson.setPartyType(objPartyType);
		 objPerson.setGivenNameEn(this.CUSTOMER_GIVEN_NAME_EN);
		 objPerson.setFamilyNameEn(this.CUSTOMER_FAMILY_NAME_EN);
		 objPerson.setNameCn(this.CUSTOMER_NAME_CN);
		 
		 objUser.setPassword(passwordEncoder.encode("password"));
		 objUser.setUsername("dmac088");
		 objPerson.setPartyRole(new ArrayList<Role>());
		 objCustomer.setCustomerId(this.CUSTOMER_ID);
		 objCustomer.setRoleStart(this.CUSTOMER_START_DATE);
		 objCustomer.setRoleType(objRoleType);
		 objPerson.addRole(objCustomer); 
		 HttpEntity<Person> requestEntity = new HttpEntity<Person>(objPerson, headers);
	     ResponseEntity<Person> uri = restTemplate.exchange(this.CUSTOMER_ENDPOINT, HttpMethod.POST, requestEntity, Person.class);
	     System.out.println(uri.getBody());    	
	}
 
 
	public static void main(String args[]) {
	 	RestClientUtil util = new RestClientUtil();
	 	util.addPersonCustomer();
	 	System.out.println("Run tests complete!");
	}    
 
 
}
