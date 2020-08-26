package io.nzbee.domain.bag;

import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IBagPortService;

public class BagServiceImpl implements IBagService {

	@Autowired
	private IBagPortService bagService;
	
	
	@Override
	public Bag findByCode(String userName) {
		return bagService.findByCode(userName);
	}


	@Override
	public void save(Bag object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Bag object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Bag object) {
		// TODO Auto-generated method stub
		
	}
}
