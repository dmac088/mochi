package io.javabrains.springbootstarter.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties.Template;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

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
 
    private static String PERSON_ENDPOINT = "http://localhost:8090/api/Person";
    private static String CUSTOMER_ENDPOINT = "http://localhost:8090/api/Customer";
 
    private static String CUSTOMER_GIVEN_NAME_EN = "Daniel";
    private static String CUSTOMER_FAMILY_NAME_EN = "Mackie";
    private static String CUSTOMER_NAME_CN = "丹尼爾麥基";
    private static String CUSTOMER_ID = "0123456789";
    private static Date   CUSTOMER_START_DATE = new Date();

    @Test
    public void verifyBeansConfigured() {
        assertNotNull(passwordEncoder);
        assertNotNull(template);
    }
    
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
		 System.out.println(objUser.getPassword());
		 HttpEntity<Customer> requestEntity = new HttpEntity<Customer>(objCustomer, headers);
	     ResponseEntity<Customer> uri = restTemplate.exchange(this.CUSTOMER_ENDPOINT, HttpMethod.POST, requestEntity, Customer.class);
	     System.out.println(uri.getBody());    	
	}
}
