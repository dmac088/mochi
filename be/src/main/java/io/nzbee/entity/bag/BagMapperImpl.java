package io.nzbee.entity.bag;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag; 
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.bag.item.BagItem;
import io.nzbee.entity.bag.item.IBagItemMapper;
import io.nzbee.entity.bag.status.IBagStatusService;
import io.nzbee.entity.party.person.IPersonMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;

@Component
public class BagMapperImpl implements IBagMapper {
	
	@Autowired
	private IPersonMapper personMapper;
	
	@Autowired
	private IBagItemMapper bagItemMapper;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IBagStatusService bagStatusService;
	
	@Autowired
	private IBagService bagService;

	@Override
	public io.nzbee.entity.bag.Bag doToEntity(String userName, Bag d) {
		
		//get the bag, status, and customer from the database
		Optional<io.nzbee.entity.bag.Bag> obe = bagService.findByCode(userName);
		Optional<io.nzbee.entity.bag.status.BagStatus> obs = bagStatusService.findByCode(Constants.bagStatusCodeNew);
		Optional<io.nzbee.entity.party.person.Person> op = personService.findByUsernameAndRole(userName, Customer.class);
		
		
		io.nzbee.entity.bag.Bag nbe = new io.nzbee.entity.bag.Bag();
		nbe.setBagStatus(obs.get());
		
		//use the existing bag if it exists otherwise use newly created
		io.nzbee.entity.bag.Bag b = (obe.isPresent())
									 ? obe.get()
									 : nbe;
		
		//map the domain bagItems to entity bagItems
		Set<BagItem> sbi = d.getBagItems().stream()
					   		.map(bi -> bagItemMapper.doToEntity(bi))
					   		.collect(Collectors.toSet());
		
		//add the bag items to the bag
		sbi.stream()
			.forEach(bi -> {
				b.addItem(bi);
			});
		
		//set the customer of the bag
		b.setParty(op.get());
		
		//persist the bag
		return b;
	}

	@Override
	public Bag entityToDo(io.nzbee.entity.bag.Bag e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bag entityToDo(Person p, io.nzbee.entity.bag.Bag e) {
		
		//we need a customer to instantiate a new bag
		Customer c = personMapper.entityToDo(p);
		
		//create a new bag domain object
		Bag b = new Bag(c);
		
		//map the entity bagItems to the domain bagItems
		Set<io.nzbee.domain.bag.BagItem> sbi = e.getBagItems().stream()
							 .map(bi -> bagItemMapper.entityToDo(bi))
							 .collect(Collectors.toSet());
		
		//use the add item method on the domain object to 
		//ensure business rules are fired against each added bagItem
		sbi.forEach(bi -> {
			b.addItem(bi);
		});
		
		return b;
	}

	@Override
	public io.nzbee.entity.bag.Bag doToEntity(Bag d) {
		// TODO Auto-generated method stub
		return null;
	}


}
