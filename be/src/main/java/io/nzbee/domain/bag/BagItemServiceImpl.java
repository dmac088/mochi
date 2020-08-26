package io.nzbee.domain.bag;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.ports.IBagItemPortService;


public class BagItemServiceImpl implements IBagItemService{

	@Autowired
	private IBagItemPortService bagItemService;
	
	@Override
	public void save(BagItem object) {
		bagItemService.save(object);
	}

	@Override
	public void delete(BagItem object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BagItem object) {
		// TODO Auto-generated method stub
		
	}

}
