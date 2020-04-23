package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
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
	private ICustomerService customerService;

	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;

	@Before
	@WithUserDetails(value = "admin")
	public void setUp() { 
		Customer customer = customerDoBeanFactory.getCustomerDoBean();
	    	
		customerService.save(customer);
	}
	
	@After
    public void clear() {
        SecurityContextHolder.clearContext();
    }
	
	@Test
	@WithUserDetails(value = "admin")
	public void whenFindCustomerByUsername_thenReturnCustomer() {
		
		// when
		Optional<Customer> found = customerService.findByUsername("tst088");

		// then
		assertFound(found);
	}

	private void assertFound(final Optional<Customer> found) {

		assertTrue(found.isPresent());
		
		assertThat(found.get()).isNotNull();
		
		assertThat(found.get().getGivenName())
	    .isEqualTo("test given name");
		
		assertThat(found.get().getFamilyName())
	    .isEqualTo("test family name");
		
		assertThat(found.get().getCustomerID())
	    .isEqualTo("123456789");
		
		assertThat(found.get().getUserName())
	    .isEqualTo("tst088");
		
	}
	
}
