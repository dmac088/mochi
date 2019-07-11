package io.nzbee.test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import io.nzbee.domain.Customer;
import io.nzbee.entity.Party;
import io.nzbee.entity.PartyPerson;
import io.nzbee.entity.PartyPersonRepository;
import io.nzbee.entity.PartyRepository;
import io.nzbee.entity.Role;
import io.nzbee.entity.RoleCustomer;
import io.nzbee.security.User;
import io.nzbee.security.UserRole;
import io.nzbee.security.UserRoleService;
import io.nzbee.services.ICustomerService;
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UT_REST_Config.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UT_REST_CRUD_Customer {
	
	@Autowired
	@Qualifier("userPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private PartyRepository partyRepository;
	
	@Autowired
	private PartyPersonRepository partyPersonRepository;
	
    @Autowired
    @Qualifier("unitTestTemplate")
    private RestTemplate template;
 
    //oAuth configuration
    private static String TOKEN_ENDPOINT 					= "https://localhost:8090/oauth/token";
    private static String OAUTH_AUTHORIZATION 				= "Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA==";
    private static String OAUTH_CACHE_CONTROL 				= "no-cache";
    private static String OAUTH_TOKEN_USERNAME 				= "admin";
    private static String OAUTH_TOKEN_PASSWORD 				= "admin1234";
    private static String OAUTH_TOKEN_CLIENTID 				= "spring-security-oauth2-read-write-client";
    private static String OAUTH_TOKEN_GRANT_TYPE 			= "password";
    
    //Customer end points
    private static String CUSTOMER_CREATE_ENDPOINT 			= "https://localhost:8090/api/Customer/Signup";
    private static String CUSTOMER_READ_ENDPOINT 			= "https://localhost:8090/api/Customer/UserName/";
    private static String CUSTOMER_UPDATE_ENDPOINT 			= "https://localhost:8090/api/Customer/Update";
    private static String CUSTOMER_DELETE_ENDPOINT			= "https://localhost:8090/api/Customer/Delete";
    
    //Customer properties
    private static String CUSTOMER_GIVEN_NAME_EN 			= "Daniel";
    private static String CUSTOMER_UPDATE_GIVEN_NAME_EN 	= "Robert";
    private static String CUSTOMER_FAMILY_NAME_EN 			= "Mackie";
    //private static String CUSTOMER_NAME_CN 				= "丹尼爾麥基";
    private static Date   CUSTOMER_START_DATE 				= new Date();
    
    private static String CUSTOMER_USERNAME 				= "dmac1112";
    private static String CUSTOMER_PASSWORD 				= "password";
    private static String USER_ROLE							= "Customer";

    private HttpHeaders getTokenHeaders() {
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
    
    private String getToken() {
    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(getMap(), getTokenHeaders());
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_ENDPOINT, request , String.class );
    	JSONObject jObj = new JSONObject(response.getBody());
    	return jObj.getString("access_token");
    }
    
    private HttpHeaders getRestHeaders() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
	    headers.add("authorization", "Bearer " + getToken());
	    headers.add("cache-control", "no-cache");
    	return headers;
    }
    
    @Test
    @Rollback(false)
    public void verifyBeansConfigured() {
        assertNotNull(passwordEncoder);
        assertNotNull(template);
    }
    
    private Customer customerDefinition() {
    	//create a person object
	     PartyPerson p1 = new PartyPerson();
	     p1.setGivenName(CUSTOMER_GIVEN_NAME_EN);
	     p1.setFamilyName(CUSTOMER_FAMILY_NAME_EN);
	    
	        
	     //create the role object
		 p1.setPartyRoles(new ArrayList<Role>());
		 RoleCustomer c1 = new RoleCustomer();
		 c1.setRoleStart(CUSTOMER_START_DATE);
			
		 //create a new user object
		 User u1 = new User();
		 u1.setUsername(CUSTOMER_USERNAME);
		 u1.setEnabled(true);
		 u1.setUserRoles(new ArrayList<UserRole>());
		 u1.addUserRole(userRoleService.loadUserRoleByRoleName(USER_ROLE));
		 u1.setPassword(CUSTOMER_PASSWORD);
			
		 //add user to person 
		 p1.addUser(u1);
			
		 //addPartytoUser
		 u1.setUserParty(p1);
			
		 //add the role to person
		 p1.addRole(c1);
			
		 //add the person to role
		 c1.setRoleParty(p1);
			 
		 //add role to person
		 p1.addRole(c1);
		 c1.setRoleParty(p1);
		 
		 return customerService.convertToCustomerDO(p1);
    }
    
    @Test
    @Rollback(false)
	public void createCustomer() {
    	
	     RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
	     List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		 interceptors.add(new LoggingRequestInterceptor());
		 restTemplate.setInterceptors(interceptors);
	     HttpHeaders headers = this.getRestHeaders();
	
		 //convert to a Customer domain object 
		 Customer customer = this.customerDefinition();
		 
		 HttpEntity<Customer> customerEntity = new HttpEntity<Customer>(customer, headers);
		 ResponseEntity<Customer> uri = restTemplate.exchange(UT_REST_CRUD_Customer.CUSTOMER_CREATE_ENDPOINT, HttpMethod.POST, customerEntity, Customer.class);
		 assertEquals(uri.getStatusCodeValue(), HttpStatus.OK.value());
		 
		 uri = restTemplate.exchange(UT_REST_CRUD_Customer.CUSTOMER_READ_ENDPOINT + CUSTOMER_USERNAME, HttpMethod.GET, customerEntity, Customer.class);
		 assertEquals(uri.getStatusCodeValue(), HttpStatus.OK.value());
		 assertEquals(uri.getBody().getGivenName(), CUSTOMER_GIVEN_NAME_EN);
		 assertEquals(uri.getBody().getFamilyName(), CUSTOMER_FAMILY_NAME_EN);
		 
		 //delete
		 uri = restTemplate.exchange(UT_REST_CRUD_Customer.CUSTOMER_DELETE_ENDPOINT, HttpMethod.POST, customerEntity, Customer.class);
		 assertEquals(uri.getStatusCodeValue(), HttpStatus.OK.value()); 
		 
    }
   

    @Test
    @Rollback(false)
    public void updateCustomer() {
    	RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
	    HttpHeaders headers = this.getRestHeaders();
	
		 //convert to a Customer domain object 
		Customer customer = this.customerDefinition();
		 
		HttpEntity<Customer> customerEntity = new HttpEntity<Customer>(customer, headers);
		ResponseEntity<Customer> uri = restTemplate.exchange(UT_REST_CRUD_Customer.CUSTOMER_CREATE_ENDPOINT, HttpMethod.POST, customerEntity, Customer.class);
		assertEquals(uri.getStatusCodeValue(), HttpStatus.OK.value());
    	
    	customer.setGivenName(CUSTOMER_UPDATE_GIVEN_NAME_EN);
		
		ResponseEntity<Customer> postUri = restTemplate.exchange(UT_REST_CRUD_Customer.CUSTOMER_UPDATE_ENDPOINT, HttpMethod.POST, customerEntity, Customer.class);
		assertEquals(postUri.getStatusCodeValue(), HttpStatus.OK.value()); 
		
		ResponseEntity<Customer> getUri = restTemplate.exchange(UT_REST_CRUD_Customer.CUSTOMER_READ_ENDPOINT + CUSTOMER_USERNAME, HttpMethod.GET, customerEntity, Customer.class);
		assertEquals(CUSTOMER_UPDATE_GIVEN_NAME_EN, getUri.getBody().getGivenName());
		
		//delete
		uri = restTemplate.exchange(UT_REST_CRUD_Customer.CUSTOMER_DELETE_ENDPOINT, HttpMethod.POST, customerEntity, Customer.class);
		assertEquals(uri.getStatusCodeValue(), HttpStatus.OK.value()); 
    }
}
