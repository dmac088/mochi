package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.customer.Customer;

@Service
@Profile(value = "dev")
public class CustomerDoBeanFactory {

	public final Customer getCustomerDoBean() {
		
		Customer c = 
				new Customer(
				"daniel",
				"mackie",
				"dmac088",
				"123456789"
				);
		c.setPassword("1234", "1234");
		
		return c;
	}
	
}
