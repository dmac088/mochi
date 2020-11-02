package io.nzbee.entity.adapters;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.entity.bag.IBagMapper;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.role.customer.CustomerEntity;

@Service
public class PostgresBagAdapter implements IBagPortService {

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IBagService bagService;
	
	@Autowired
	private IBagMapper bagMapper;
	
	
	@Override
	public Bag findByCode(String locale, String currency, String userName) {
		Optional<io.nzbee.entity.bag.BagEntity> ob = bagService.findByCode(userName);
		
		PersonEntity p = (ob.isPresent())
				   ? (PersonEntity) ob.get().getParty()
				   : personService.findByUsernameAndRole(userName, CustomerEntity.class).get();
		
		//if there is no current bag, get a new one
		io.nzbee.entity.bag.BagEntity b = (ob.isPresent())
									? ob.get()
									: new io.nzbee.entity.bag.BagEntity();
	
		//map the bag to a domain object
		return bagMapper.entityToDo(locale, currency, p, b);
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
		io.nzbee.entity.bag.BagEntity b = bagMapper.doToEntity(domainObject);
		bagService.save(b);
	}

	@Override
	public Bag findByCode(String userName) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
