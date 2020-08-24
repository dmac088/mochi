package io.nzbee.domain.bag;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.ports.ICustomerPortService;

public class BagServiceImpl implements IBagService {

	@Autowired
	private ICustomerPortService customerService;
	
	
	@Override
	public Bag findByUsername(String userName) {
		return customerService;
	}

	
	
}
