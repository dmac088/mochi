package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.test.integration.beans.CustomerDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_CustomerDoServiceImplIntegrationTest {

	@TestConfiguration
    static class CustomerServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
		
    }


	@Autowired
    private AuthenticationManager am;
	
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;
	
	Customer customer = null; 

	public Customer persistNewCustomer() {
    	
		customer = customerDoBeanFactory.getCustomerDoBean();
	    	
		customerService.save(customer);
	    	
	    return customer;
	}
	
	protected void login(String name, String password) {
		Authentication auth = new UsernamePasswordAuthenticationToken(name, password);
	    SecurityContextHolder.getContext().setAuthentication(am.authenticate(auth));
	}
	
	@Before
	public void setUp() { 
		this.persistNewCustomer();
	}
	
	@Test
	public void whenFindCustomerByUsername_thenReturnCustomer() {
		login("admin", "admin1234");
		
		// when
		Customer found = customerService.findByUsername("dmac088").get();

		// then
		assertFound(found);
	}

	private void assertFound(final Customer found) {

		assertThat(found).isNotNull();
		
	}
	
}
