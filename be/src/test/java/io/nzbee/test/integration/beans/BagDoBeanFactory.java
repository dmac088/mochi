package io.nzbee.test.integration.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.IProductService;


@Service
@Profile(value = "tst")
public class BagDoBeanFactory {

	
	public final Bag getBagDoBean(Customer c) {
	
		Bag bag = new Bag(c);
		
		return bag;
		
	}
	
}
