package io.nzbee.entity.adapters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.entity.bag.IBagService;

public class PostgredBagAdapter implements IBagPortService {

	@Autowired
	private IBagService bagService;
	
	@Override
	public Bag findByUsername(String userName) {
		Optional<io.nzbee.entity.bag.Bag> ob = bagService.findByUsername(userName);
		
		io.nzbee.entity.bag.Bag b = 
		(ob.isPresent())
		? ob.get()
		: new io.nzbee.entity.bag.Bag();
		
	}

}
