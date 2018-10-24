package io.javabrains.springbootstarter.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

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
//@ActiveProfiles("dev")
public class RestClientUtil {
	
	@Autowired
	@Qualifier("userPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    @Qualifier("unitTestTemplate")
    private RestTemplate template;
 
    private static String TOKEN_ENDPOINT = "http://localhost:8090/oauth/token";
    private static String OAUTH_TYPE = "Basic Auth";
    private static String OAUTH_CLIENT_USERNAME = "spring-security-oauth2-read-write-client";
    private static String OAUTH_CLIENT_PASSWORD = "spring-security-oauth2-read-write-client-password1234";
    private static String OAUTH_TOKEN_USERNAME = "admin";
    private static String OAUTH_TOKEN_PASSWORD = "admin1234";
    private static String OAUTH_TOKEN_CLIENTID = "spring-security-oauth2-read-write-client";
    private static String OAUTH_TOKEN_GRANT_TYPE = "password";
    
    private static String PERSON_ENDPOINT = "http://localhost:8090/api/Person";
    private static String CUSTOMER_ENDPOINT = "http://localhost:8090/api/Customer";
 
    private static String CUSTOMER_GIVEN_NAME_EN = "Daniel";
    private static String CUSTOMER_FAMILY_NAME_EN = "Mackie";
    private static String CUSTOMER_NAME_CN = "丹尼爾麥基";
    private static String CUSTOMER_ID = "0123456789";
    private static Date   CUSTOMER_START_DATE = new Date();

    @Test
    public void getToken() throws Exception {
    	OkHttpClient client = new OkHttpClient();

    	MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
    	RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\nadmin\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"password\"\r\n\r\nadmin1234\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"client_id\"\r\n\r\nspring-security-oauth2-read-client\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"grant_type\"\r\n\r\npassword\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
    	Request request = new Request.Builder()
    	  .url(TOKEN_ENDPOINT)
    	  .post(body)
    	  .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
    	  .addHeader("authorization", "Basic c3ByaW5nLXNlY3VyaXR5LW9hdXRoMi1yZWFkLWNsaWVudDpzcHJpbmctc2VjdXJpdHktb2F1dGgyLXJlYWQtY2xpZW50LXBhc3N3b3JkMTIzNA==")
    	  .addHeader("cache-control", "no-cache")
    	  .build();

    	Response response = client.newCall(request).execute();
    	System.out.println(response.body());
    }
    
    @Test
    public void verifyBeansConfigured() {
        assertNotNull(passwordEncoder);
        assertNotNull(template);
    }
    
	@Test
	public void addPersonCustomer() {
		 HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.APPLICATION_JSON);
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
		 objUser.setUsername("dmac089");
		 objPerson.setPartyRole(new ArrayList<Role>());
		 objCustomer.setCustomerId(this.CUSTOMER_ID);
		 objCustomer.setRoleStart(this.CUSTOMER_START_DATE);
		 objCustomer.setRoleType(objRoleType);
		 objPerson.addRole(objCustomer); 
		 System.out.println(objUser.getPassword());
		 HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(objCustomer, headers);
	     ResponseEntity<Customer> uri = restTemplate.exchange(this.CUSTOMER_ENDPOINT, HttpMethod.POST, requestEntity, Customer.class);
	     System.out.println(uri.getBody());    	
	}
}
