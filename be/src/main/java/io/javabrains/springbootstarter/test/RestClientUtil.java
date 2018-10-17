package io.javabrains.springbootstarter.test;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.javabrains.springbootstarter.domain.Customer;
import io.javabrains.springbootstarter.domain.PartyType;
import io.javabrains.springbootstarter.domain.Person;
import io.javabrains.springbootstarter.domain.Role;
import io.javabrains.springbootstarter.domain.RoleType;

public class RestClientUtil {
	
	
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
	 objPerson.setPassword("password");
	 objPerson.setUserName("dmac0115");
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
