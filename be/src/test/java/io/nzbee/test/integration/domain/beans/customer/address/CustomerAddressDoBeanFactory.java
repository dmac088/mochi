package io.nzbee.test.integration.domain.beans.customer.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.customer.address.Address;
import io.nzbee.test.integration.domain.beans.customer.ICustomerDoBeanFactory;

@Service
@Profile("it")
public class CustomerAddressDoBeanFactory implements ICustomerAddressDoBeanFactory {

	@Autowired 
	private ICustomerDoBeanFactory customerBeanFactory;
	
	@Override
	public Address getBean() {
		
		Address a = 
				new Address(
						customerBeanFactory.getBean(),
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
