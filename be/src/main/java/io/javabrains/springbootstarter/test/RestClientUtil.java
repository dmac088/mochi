package io.javabrains.springbootstarter.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import io.javabrains.springbootstarter.domain.Customer;
import io.javabrains.springbootstarter.domain.PartyType;
import io.javabrains.springbootstarter.domain.Person;
import io.javabrains.springbootstarter.domain.Role;
import io.javabrains.springbootstarter.domain.RoleType;
import io.javabrains.springbootstarter.security.Encoders;
import io.javabrains.springbootstarter.security.User;

/*

Use the following class to configure unit tests as per the following link:

https://www.baeldung.com/spring-data-rest-relationships
 
*/
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Encoders.class, UnitTestConfig.class})
public class RestClientUtil {
	
	@Autowired
	@Qualifier("userPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    @Qualifier("unitTestTemplate")
    private RestTemplate template;
 
    private static String TOKEN_ENDPOINT = "https://localhost:8090/oauth/token";
    private static String OAUTH_AUTHORIZATION = "Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA==";
    private static String OAUTH_CACHE_CONTROL = "no-cache";
    private static String OAUTH_TOKEN_USERNAME = "admin";
    private static String OAUTH_TOKEN_PASSWORD = "admin1234";
    private static String OAUTH_TOKEN_CLIENTID = "spring-security-oauth2-read-write-client";
    private static String OAUTH_TOKEN_GRANT_TYPE = "password";
    
    private static String CUSTOMER_ENDPOINT = "https://localhost:8090/api/Customer";
    private static String PERSON_ENDPOINT = "https://localhost:8090/api/Person";
    private static String CUSTOMER_GIVEN_NAME_EN = "Daniel";
    private static String CUSTOMER_FAMILY_NAME_EN = "Mackie";
    private static String CUSTOMER_NAME_CN = "丹尼爾麥基";
    private static String CUSTOMER_ID = "0123456789";
    private static Date   CUSTOMER_START_DATE = new Date();


    private HttpHeaders getHeaders() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	headers.add("authorization", OAUTH_AUTHORIZATION);
    	headers.add("cache-control", OAUTH_CACHE_CONTROL);
    	return headers;
    }
    
    private MultiValueMap<String, String> getMap() {
    	MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    	map.add("client_id", OAUTH_TOKEN_CLIENTID);
    	map.add("username", OAUTH_TOKEN_USERNAME);
    	map.add("password", OAUTH_TOKEN_PASSWORD);
    	map.add("grant_type", OAUTH_TOKEN_GRANT_TYPE);
    	return map;
    }
    
    public String getToken() {
    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(getMap(), getHeaders());
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_ENDPOINT, request , String.class );
    	JSONObject jObj = new JSONObject(response.getBody());
    	return jObj.getString("access_token");
    }
    
    
    
    @Test
    public void verifyBeansConfigured() {
        assertNotNull(passwordEncoder);
        assertNotNull(template);
    }
    
    @Test
    public void retrieveToken() {
    	System.out.println(getToken().length());
    	System.out.println("baa0bbbd-742b-4225-ad5e-dfc74f38571b".length());
    	Assert.assertTrue(getToken().length()=="baa0bbbd-742b-4225-ad5e-dfc74f38571b".length());
    }
    
	@Test
	public void addPersonCustomer() {
		
	     RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
	     List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		 interceptors.add(new LoggingRequestInterceptor());
		 restTemplate.setInterceptors(interceptors);
	     HttpHeaders headers = new HttpHeaders();
	     headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
	     headers.add("authorization", "Bearer " + getToken());
	     headers.add("cache-control", "no-cache");
		 
	     
	     //Create the customer
	     RoleType objRoleType = new RoleType();
	     objRoleType.setRoleTypeId((long)1);
		 Customer objCustomer = new Customer();
		 objCustomer.setCustomerId(this.CUSTOMER_ID);
		 objCustomer.setRoleStart(this.CUSTOMER_START_DATE);
		 objCustomer.setRoleType(objRoleType);
		 
		 //Create the person
		 PartyType objPartyType = new PartyType();
		 objPartyType.setPartyTypeId((long)1);
		 Person objPerson = new Person();
		 objPerson.setPartyType(objPartyType);
		 objPerson.setGivenNameEn(this.CUSTOMER_GIVEN_NAME_EN);
		 objPerson.setFamilyNameEn(this.CUSTOMER_FAMILY_NAME_EN);
		 objPerson.setNameCn(this.CUSTOMER_NAME_CN);
		
		 
		 //Create the user
		 User objUser = new User();
		 objUser.setPassword(passwordEncoder.encode("password"));
		 objUser.setUsername("dmac094");
		 objUser.setEnabled(true);
		 
		 //add the user to the person
		 objPerson.addUser(objUser);
		 
		 //add role to person
		 List<Role> r = new ArrayList<Role>();
		 r.add(objCustomer);
		 objPerson.setPartyRole(r);
		
		 HttpEntity<Person> requestEntity = new HttpEntity<Person>(objPerson, headers);
		 System.out.println(requestEntity.getBody().toString());
	     ResponseEntity<Person> uri = restTemplate.postForEntity(this.CUSTOMER_ENDPOINT, requestEntity, Person.class);
	     
	     System.out.println(uri.getBody());	
	}
}
