package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.test.integration.beans.CustomerDoBeanFactory;

public class IT_CustomerDoServiceImplIntegrationTest {


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
	
	@Before
	public void setUp() { 
		this.persistNewCustomer();
	}
	
	@Test
	public void whenFindCustomerByUsername_thenReturnCustomer() {

		// when
		Customer found = customerService.findByUsername("dmac088").get();

		// then
		assertFound(found);
	}

	private void assertFound(final Customer found) {

		assertThat(found).isNotNull();
		
	}
	
}
