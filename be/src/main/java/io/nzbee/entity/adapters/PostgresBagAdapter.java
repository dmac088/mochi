package io.nzbee.entity.adapters;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.entity.bag.IBagMapper;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.role.customer.Customer;

@Service
public class PostgresBagAdapter implements IBagPortService {

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IBagService bagService;
	
	@Autowired
	private IBagMapper bagMapper;
	
	
	@Override
	public Bag findByCode(String userName) {
		Optional<io.nzbee.entity.bag.Bag> ob = bagService.findByCode(userName);
		
		Person p = (ob.isPresent())
				   ? (Person) ob.get().getParty()
				   : personService.findByUsernameAndRole(userName, Customer.class).get();
		
		//if there is no current bag, get a new one
		io.nzbee.entity.bag.Bag b = (ob.isPresent())
									? ob.get()
									: new io.nzbee.entity.bag.Bag();
	
		//map the bag to a domain object
		return bagMapper.entityToDo(p, b);
	}
	
	@Override
	public Bag addItemToBag(BagItem bagItem) {
		//in order to construct a bagItem the bagItem must already be linked to a bag
		//so we simply need to persist the bag
		Bag b = bagItem.getBag();
		this.save(b);
		return b;
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
		io.nzbee.entity.bag.Bag b = bagMapper.doToEntity(domainObject);
		bagService.save(b);
	}


	
}
