package io.nzbee.test.integration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.json.JSONException;
import org.json.JSONObject;

import io.nzbee.test.LoggingRequestInterceptor;
import io.nzbee.view.customer.CustomerDTOIn;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_CustomerControllerIntegrationTest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("userPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    @Qualifier("unitTestTemplate")
    private RestTemplate template;
 
 
    //oAuth configuration
    private String TOKEN_ENDPOINT 					= "https://localhost:8090/oauth/token";
    private String OAUTH_AUTHORIZATION 				= "Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLXdyaXRlLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtd3JpdGUtY2xpZW50LXBhc3N3b3JkMTIzNA==";
    private String OAUTH_CACHE_CONTROL 				= "no-cache";
    private String OAUTH_TOKEN_USERNAME 			= "bob@bob";
    private String OAUTH_TOKEN_PASSWORD 			= "bob";
    private String OAUTH_TOKEN_CLIENTID 			= "spring-security-oauth2-read-write-client";
    private String OAUTH_TOKEN_GRANT_TYPE 			= "password";
    
    //Customer end points
    private String CUSTOMER_CREATE_ENDPOINT 		= "https://localhost:8090/api/Customer/Signup";
    private String CUSTOMER_READ_ENDPOINT 			= "https://localhost:8090/api/Customer/UserName/";
    private String CUSTOMER_UPDATE_ENDPOINT 		= "https://localhost:8090/api/Customer/Update";
    private String CUSTOMER_DELETE_ENDPOINT			= "https://localhost:8090/api/Customer/Delete/";
    
    //Customer properties
    private String CUSTOMER_GIVEN_NAME_EN 			= "Daniel";
    private String CUSTOMER_UPDATE_GIVEN_NAME_EN 	= "Robert";
    private String CUSTOMER_FAMILY_NAME_EN 			= "Mackie";
    private String CUSTOMER_TYPE_NAME_EN 			= "Person";
    
    //private static String CUSTOMER_NAME_CN 				= "丹尼爾麥基";
    //private Date   CUSTOMER_START_DATE 				= new Date();
    private String CUSTOMER_NUMER 					= "0123498765";
    
    private String CUSTOMER_USERNAME 				= "dmac1119";
    private String CUSTOMER_PASSWORD 				= "password";

    private HttpHeaders getTokenHeaders() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	headers.add("authorization", OAUTH_AUTHORIZATION);
    	headers.add("cache-control", OAUTH_CACHE_CONTROL);
    	headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
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
   
    	ClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory());
    	
    	RestTemplate restTemplate = new RestTemplate(requestFactory);
    	List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new LoggingRequestInterceptor());
	    restTemplate.setInterceptors(interceptors);
	 
	    try {
	    	ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_ENDPOINT, request , String.class );
	    	assertEquals(response.getStatusCodeValue(), HttpStatus.OK.value());
	    	JSONObject jObj = new JSONObject(response.getBody());
			return jObj.getString("access_token");
	    } catch (HttpServerErrorException e ) {
	    	LOGGER.debug(e.getResponseBodyAsString());
	    } catch (JSONException e) {
			e.printStackTrace();
		}
	    
		return null;
    }
    
    private HttpHeaders getRestHeaders(boolean withAuth) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
	    if(withAuth) {
	    	headers.add("authorization", "Bearer " + getToken());
	    }
	    headers.add("cache-control", "no-cache");
	    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    	return headers;
    }
    
    @Test
    public void verifyBeansConfigured() {
        assertNotNull(passwordEncoder);
        assertNotNull(template);
    }
  
    
    private CustomerDTOIn customerDefinition() {
    	CustomerDTOIn c =  new CustomerDTOIn();
    	c.setGivenName(CUSTOMER_GIVEN_NAME_EN);
    	c.setFamilyName(CUSTOMER_FAMILY_NAME_EN);
    	c.setUserName(CUSTOMER_USERNAME);
    	c.setPassword(CUSTOMER_PASSWORD);
    	c.setConfirmPassword(CUSTOMER_PASSWORD);
    	c.setCustomerId(CUSTOMER_NUMER);
    	return c;
    }
    
    @Test
	public void createCustomer() {
	    RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new LoggingRequestInterceptor());
	    restTemplate.setInterceptors(interceptors);
	    HttpHeaders headers = this.getRestHeaders(false);
	
	    //convert to a Customer DTO object 
	    CustomerDTOIn customer = this.customerDefinition();
	    
	    HttpEntity<CustomerDTOIn> customerDTO = new HttpEntity<CustomerDTOIn>(customer, headers);
	    try {
	    	ResponseEntity<CustomerDTOIn> uriDTO = restTemplate.exchange(CUSTOMER_CREATE_ENDPOINT, HttpMethod.POST, customerDTO, CustomerDTOIn.class);
	    	assertEquals(uriDTO.getStatusCodeValue(), HttpStatus.OK.value());
	    } catch (HttpServerErrorException e ) {
	    	LOGGER.debug(e.getResponseBodyAsString());
	    }
	    
	    headers = this.getRestHeaders(true);
	    HttpEntity<CustomerDTOIn> request = new HttpEntity<CustomerDTOIn>(headers);
	    ResponseEntity<CustomerDTOIn> uriDo = restTemplate.exchange(CUSTOMER_READ_ENDPOINT + CUSTOMER_USERNAME, HttpMethod.GET, request, CustomerDTOIn.class);
	    CustomerDTOIn c = uriDo.getBody();
	    
	    assertEquals(uriDo.getStatusCodeValue(), HttpStatus.OK.value());
	    assertEquals(CUSTOMER_GIVEN_NAME_EN, c.getGivenName());
	    assertEquals(CUSTOMER_FAMILY_NAME_EN, c.getFamilyName());
	    assertEquals(CUSTOMER_TYPE_NAME_EN, c.getPartyType());
	    
	    //delete
	    headers = this.getRestHeaders(true);
	    request = new HttpEntity<CustomerDTOIn>(headers);
	    customerDTO = new HttpEntity<CustomerDTOIn>(customer, headers);
	    ResponseEntity<CustomerDTOIn> uri = restTemplate.exchange(CUSTOMER_DELETE_ENDPOINT + CUSTOMER_USERNAME, HttpMethod.POST, customerDTO, CustomerDTOIn.class);
	    assertEquals(HttpStatus.OK.value(), uri.getStatusCodeValue()); 
	    
    }
   
    
    @Test
    public void updateCustomer() {
    	RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new LoggingRequestInterceptor());
	    restTemplate.setInterceptors(interceptors);
	    HttpHeaders headers = this.getRestHeaders(false);
	
	    //convert to a Customer DTO object 
	    CustomerDTOIn customer = this.customerDefinition();
	    
	    //create the customers
	    HttpEntity<CustomerDTOIn> customerDTO = new HttpEntity<CustomerDTOIn>(customer, headers);
	    try {
	    	ResponseEntity<CustomerDTOIn> uriDTO = restTemplate.exchange(CUSTOMER_CREATE_ENDPOINT, HttpMethod.POST, customerDTO, CustomerDTOIn.class);
	    	assertEquals(HttpStatus.OK.value(), uriDTO.getStatusCodeValue());
	    } catch (HttpServerErrorException e ) {
	    	LOGGER.debug(e.getResponseBodyAsString());
	    }
	    
	    //update the customer
	    customer.setGivenName(CUSTOMER_UPDATE_GIVEN_NAME_EN);
	    headers = this.getRestHeaders(true);
	    customerDTO = new HttpEntity<CustomerDTOIn>(customer, headers);
	    try {
	    	ResponseEntity<CustomerDTOIn> uriDTO = restTemplate.exchange(CUSTOMER_UPDATE_ENDPOINT, HttpMethod.POST, customerDTO, CustomerDTOIn.class);
	    	assertEquals(HttpStatus.OK.value(), uriDTO.getStatusCodeValue());
	    } catch (HttpServerErrorException e ) {
	    	LOGGER.debug(e.getResponseBodyAsString());
	    }
	    
	    headers = this.getRestHeaders(true);
	    HttpEntity<CustomerDTOIn> request = new HttpEntity<CustomerDTOIn>(headers);
	    ResponseEntity<CustomerDTOIn> uriDo = restTemplate.exchange(CUSTOMER_READ_ENDPOINT + CUSTOMER_USERNAME, HttpMethod.GET, request, CustomerDTOIn.class);
	    CustomerDTOIn c = uriDo.getBody();
	    
	    assertEquals(uriDo.getStatusCodeValue(), HttpStatus.OK.value());
	    assertEquals(CUSTOMER_UPDATE_GIVEN_NAME_EN, c.getGivenName());
	    assertEquals(CUSTOMER_FAMILY_NAME_EN, c.getFamilyName());
	    assertEquals(CUSTOMER_TYPE_NAME_EN, c.getPartyType());
	    
	    //delete
	    headers = this.getRestHeaders(true);
	    request = new HttpEntity<CustomerDTOIn>(headers);
	    customerDTO = new HttpEntity<CustomerDTOIn>(customer, headers);
	    ResponseEntity<CustomerDTOIn> uri = restTemplate.exchange(CUSTOMER_DELETE_ENDPOINT + CUSTOMER_USERNAME, HttpMethod.POST, customerDTO, CustomerDTOIn.class);
	    assertEquals(HttpStatus.OK.value(), uri.getStatusCodeValue()); 
    }
    
    
    @Test
    public void deleteCustomer() {
    	RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new LoggingRequestInterceptor());
	    restTemplate.setInterceptors(interceptors);
	    HttpHeaders headers = this.getRestHeaders(true);
	
	    //convert to a Customer DTO object 
	    CustomerDTOIn customer = this.customerDefinition();
	    
	    HttpEntity<CustomerDTOIn> customerDTO = new HttpEntity<CustomerDTOIn>(customer, headers);
	    try {
	    	ResponseEntity<CustomerDTOIn> uriDTO = restTemplate.exchange(CUSTOMER_CREATE_ENDPOINT, HttpMethod.POST, customerDTO, CustomerDTOIn.class);
	    	assertEquals(HttpStatus.OK.value(), uriDTO.getStatusCodeValue());
	    } catch (HttpServerErrorException e ) {
	    	LOGGER.debug(e.getResponseBodyAsString());
	    }
	    
	    //delete
	    headers = this.getRestHeaders(true);
	    customerDTO = new HttpEntity<CustomerDTOIn>(customer, headers);
	    ResponseEntity<CustomerDTOIn> uri = restTemplate.exchange(CUSTOMER_DELETE_ENDPOINT + CUSTOMER_USERNAME, HttpMethod.POST, customerDTO, CustomerDTOIn.class);
	    assertEquals(HttpStatus.OK.value(), uri.getStatusCodeValue()); 
    }
    
    
    @Test
	public void whenCustomerNotFound_404Reponse() {
	    RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new LoggingRequestInterceptor());
	    restTemplate.setInterceptors(interceptors);
	    HttpHeaders headers = this.getRestHeaders(true);
	    
	    headers = this.getRestHeaders(true);
	    HttpEntity<CustomerDTOIn> request = new HttpEntity<CustomerDTOIn>(headers);
	    try {
	    	restTemplate.exchange(CUSTOMER_READ_ENDPOINT + "asdf", HttpMethod.GET, request, CustomerDTOIn.class);
	    } catch (final HttpClientErrorException e) {
	    	assertEquals(HttpStatus.NOT_FOUND.value(), e.getStatusCode().value());
	    }
    }
    
    @Test
	public void whenCustomerAlreadyExists_409Reponse() {
    	RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new LoggingRequestInterceptor());
	    restTemplate.setInterceptors(interceptors);
	    HttpHeaders headers = this.getRestHeaders(false);
	
	    //convert to a Customer DTO object 
	    CustomerDTOIn customer = this.customerDefinition();
	    //set to a username of a customer we know already exists
	    customer.setUserName("dmac088");
	    
	    //create the customers
	    HttpEntity<CustomerDTOIn> customerDTO = new HttpEntity<CustomerDTOIn>(customer, headers);
	    try {
	    	restTemplate.exchange(CUSTOMER_CREATE_ENDPOINT, HttpMethod.POST, customerDTO, CustomerDTOIn.class);
	    } catch (final HttpClientErrorException e ) {
	    	assertEquals(HttpStatus.CONFLICT.value(), e.getStatusCode().value());
	    }
    }
    
    @Test
    public void whenUserUnauthoriedForGetCustomer_401Response() {
    	RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new LoggingRequestInterceptor());
	    restTemplate.setInterceptors(interceptors);
	    HttpHeaders headers = this.getRestHeaders(false);
	    HttpEntity<CustomerDTOIn> request = new HttpEntity<CustomerDTOIn>(headers);
	    try {
	    	restTemplate.exchange(CUSTOMER_READ_ENDPOINT + CUSTOMER_USERNAME, HttpMethod.GET, request, CustomerDTOIn.class);
	    } catch (final HttpClientErrorException e ) {
	    	assertEquals(HttpStatus.UNAUTHORIZED.value(), e.getStatusCode().value());
	    }
    }
}
