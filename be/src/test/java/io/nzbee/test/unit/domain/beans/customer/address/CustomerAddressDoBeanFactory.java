package io.nzbee.test.unit.domain.beans.customer.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.customer.address.Address;
import io.nzbee.test.unit.domain.beans.customer.ICustomerDoBeanFactory;

@Service
@Profile(value = "ut")
public class CustomerAddressDoBeanFactory implements ICustomerAddressDoBeanFactory {

	@Autowired
	private ICustomerDoBeanFactory customerBeanFactory;
	
	@Override
	public final Address getBean() {
		
		Address a = 
				new Address(
						customerBeanFactory.getBean(),
						"Test Line 1",
						"Test Line 2",
						"Test Line 3",
						"Test Country",
						"Test Post Code",
						"TST01", 
						"Test Address Type Desc"
				);

			
		return a;
	}
	
}
