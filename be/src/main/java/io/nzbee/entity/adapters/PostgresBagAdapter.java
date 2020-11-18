package io.nzbee.entity.adapters;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.entity.bag.BagDTO;
import io.nzbee.entity.bag.BagEntity;
import io.nzbee.entity.bag.IBagMapper;
import io.nzbee.entity.bag.IBagService;

@Service
public class PostgresBagAdapter implements IBagPortService {
	
	@Autowired
	private IBagService bagService;
	
	@Autowired
	private IBagMapper bagMapper;
	
	
	@Override
	public Bag findByCode(String locale, String currency, String userName) {
		Optional<BagDTO> ob = bagService.findByCode(locale, currency, userName);
		
		
		//if there is no current bag, get a new one
		BagDTO b = 	ob.get();
	
		//map the bag to a domain object
		return bagMapper.DTOToDo(b);
	}

	@Override
	public void update(Bag domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bag domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Bag domainObject) {
		BagEntity b = bagMapper.doToEntity(domainObject);
		bagService.save(b);
	}

	@Override
	public Bag findByCode(String userName) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
