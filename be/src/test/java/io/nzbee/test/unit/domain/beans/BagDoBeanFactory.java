package io.nzbee.test.unit.domain.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;

@Service("ut")
@Profile(value = "tst")
public class BagDoBeanFactory {
	
	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;
	
	public final Bag getBagDoBean() {
	
		Bag bag = new Bag(customerDoBeanFactory.getCustomerDoBean());
		
		return bag;
		
	}
	
}
