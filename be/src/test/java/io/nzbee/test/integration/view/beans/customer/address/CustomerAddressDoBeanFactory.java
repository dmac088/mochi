package io.nzbee.test.integration.view.beans.customer.address;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.address.Address;

@Service
@Profile("it")
public class CustomerAddressDoBeanFactory implements ICustomerAddressDoBeanFactory {

	@Override
	public Address getBean() {
		
		Address a = 
				new Address(
						new Customer(
								"Test",
								"Test",
								"bob@bob",
								"12345",
								true
						),
						"Test Line 1",
						"Test Line 2",
						"Test Line 3",
						"Test Country",
						"Test PC",
						"BIL01", 
						"Billing Address"
				);
			
		return a;
	}
	
}
