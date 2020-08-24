package io.nzbee.domain.bag;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.ports.IBagPortService;

public class BagServiceImpl implements IBagService {

	@Autowired
	private IBagPortService bagService;
	
	
	@Override
	public Bag findByUsername(String userName) {
		return bagService.findByUsername(userName);
	}
	
}
