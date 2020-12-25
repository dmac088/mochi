package io.nzbee.test.unit.domain.beans.customer;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.customer.Customer;

@Service
@Profile(value = "ut")
public class CustomerDoBeanFactory implements ICustomerDoBeanFactory {

	@Override
	public final Customer getBean() {
		
		Customer c = 
				new Customer(
				"test given name",
				"test family name",
				"tst088",
				"123456789",
				true
				);
		c.setPassword("1234", "1234");
			
		return c;
	}
	
}
