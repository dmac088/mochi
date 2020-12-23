package io.nzbee.test.unit.domain.beans.bag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.test.unit.domain.beans.CustomerDoBeanFactory;

@Service
@Profile(value = "tst")
public class BagDoBeanFactory implements IBagDoBeanFactory {
	
	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;
	
	@Override
	public Bag getBean() {
		Bag bag = new Bag(customerDoBeanFactory.getCustomerDoBean());
		
		return bag;
	}
	
}
