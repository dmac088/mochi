package io.nzbee.test.integration.domain.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;

@Service
@Profile(value = "tst")
public class BagDoBeanFactory {
	
	public final Bag getBagDoBean(Customer c) {
	
		Bag bag = new Bag(c);
		
		return bag;
		
	}
	
}
