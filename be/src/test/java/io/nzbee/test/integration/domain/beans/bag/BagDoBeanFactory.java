package io.nzbee.test.integration.domain.beans.bag;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;

@Service
@Profile(value = "tst")
public class BagDoBeanFactory implements IBagDoBeanFactory {
	
	@Override
	public Bag getBean(Customer c) {
	
		Bag bag = new Bag(c);
		
		return bag;
		
	}

	@Override
	public Bag getBean() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
