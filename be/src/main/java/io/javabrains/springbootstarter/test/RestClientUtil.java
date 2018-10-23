package io.javabrains.springbootstarter.test;

import java.util.ArrayList;
import java.util.Date;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import io.javabrains.springbootstarter.domain.Customer;
import io.javabrains.springbootstarter.domain.PartyType;
import io.javabrains.springbootstarter.domain.Person;
import io.javabrains.springbootstarter.domain.Role;
import io.javabrains.springbootstarter.domain.RoleType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplication.class, 
 webEnvironment=WebEnvironment.DEFINED_PORT)
public class RestClientUtil {
	
    @Autowired
    private Template template;
 
    private static String BOOK_ENDPOINT = "http://localhost:8080/books/";
    private static String AUTHOR_ENDPOINT = "http://localhost:8080/authors/";
    private static String ADDRESS_ENDPOINT = "http://localhost:8080/addresses/";
    private static String LIBRARY_ENDPOINT = "http://localhost:8080/libraries/";
 
    private static String LIBRARY_NAME = "My Library";
    private static String AUTHOR_NAME = "George Orwell";
	
 public void addPersonCustomer() {
	 HttpHeaders headers = new HttpHeaders();
	 headers.setContentType(MediaType.APPLICATION_JSON);
	 RestTemplate restTemplate = new RestTemplate();
	 String url = "http://localhost:8090/Customer";
	 Person objPerson = new Person();
	 Customer objCustomer = new Customer();
	 PartyType objPartyType = new PartyType();
	 RoleType objRoleType = new RoleType();
	 objRoleType.setRoleTypeId((long)1);
	 objPartyType.setPartyTypeId((long) 1);
	 objPerson.setPartyType(objPartyType);
	 objPerson.setGivenNameEn("Daniel");
	 objPerson.setFamilyNameEn("Mackie");
	 objPerson.setNameCn("丹尼爾麥基");
	 //objPerson.setPassword("password");
	 //objPerson.setUserName("dmac0115");
	 objPerson.setPartyRole(new ArrayList<Role>());
	 objCustomer.setCustomerId("0123456789");
	 objCustomer.setRoleStart(new Date());
	 objCustomer.setRoleType(objRoleType);
	 objPerson.addRole(objCustomer); 
	 HttpEntity<Person> requestEntity = new HttpEntity<Person>(objPerson, headers);
     ResponseEntity<Person> uri = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Person.class);
     System.out.println(uri.getBody());    	
 }
 
 
 public static void main(String args[]) {
 	RestClientUtil util = new RestClientUtil();
 	util.addPersonCustomer();
 	System.out.println("Run tests complete!");
 }    
 
 
}
