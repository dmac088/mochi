package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.test.unit.domain.beans.CustomerDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_CustomerDoServiceImplIntegrationTest {

	@TestConfiguration
    static class CustomerServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
		
    }

	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private ICustomerPortService customerService;

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
		Customer found = customerService.findByUsername("tst088");

		// then
		assertFound(found);
	}

	private void assertFound(final Customer found) {

		assertNotNull(found);
		
		assertThat(found.getGivenName())
	    .isEqualTo("test given name");
		
		assertThat(found.getFamilyName())
	    .isEqualTo("test family name");
		
		assertThat(found.getCustomerID())
	    .isEqualTo("123456789");
		
		assertThat(found.getUserName())
	    .isEqualTo("tst088");
		
	}
	
}
