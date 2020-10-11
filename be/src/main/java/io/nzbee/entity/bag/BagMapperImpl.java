package io.nzbee.entity.bag;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag; 
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.bag.item.BagItem;
import io.nzbee.entity.bag.item.IBagItemMapper;
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
	private IBagService bagService;
	

	@Override
	public Bag entityToDo(io.nzbee.entity.bag.Bag e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bag entityToDo(String locale, String currency, Person p, io.nzbee.entity.bag.Bag e) {
		
		//we need a customer to instantiate a new bag
		Customer c = personMapper.entityToDo(p);
		
		//create a new bag domain object
		Bag b = new Bag(c);
		
		//map the entity bagItems to the domain bagItems
		Set<io.nzbee.domain.bag.BagItem> sbi = e.getBagItems().stream()
							 .map(bi -> bagItemMapper.entityToDo(locale, currency, bi))
							 .collect(Collectors.toSet());
		
		//use the add item method on the domain object to 
		//ensure business rules are fired against each added bagItem
		sbi.forEach(bi -> {
			b.addItem(bi.getProduct(), bi.getQuantity());
		});
		
		return b;
	}

	@Override
	public io.nzbee.entity.bag.Bag doToEntity(Bag d) {
		//get the bag, status, and customer from the database
		Optional<io.nzbee.entity.bag.Bag> obe 						= bagService.findByCode(d.getCustomer().getUserName());		
		Optional<io.nzbee.entity.party.person.Person> op	 		= personService.findByUsernameAndRole(d.getCustomer().getUserName(), Customer.class);
					
		io.nzbee.entity.bag.Bag nbe = new io.nzbee.entity.bag.Bag();
		nbe.setBagCreatedDateTime(LocalDateTime.now());
		nbe.setBagUpdatedDateTime(LocalDateTime.now());
				
		//use the existing bag if it exists otherwise use newly created
		io.nzbee.entity.bag.Bag b = (obe.isPresent())
									? obe.get()
									: nbe;		
									
		b.setBagUpdatedDateTime(LocalDateTime.now());							
									
		//map the domain bagItems to entity bagItems
		Set<BagItem> sbi = d.getBagItems().stream()
					   		.map(bi -> {
					   			Optional<BagItem> obi = b.getBagItems().stream().filter(i -> i.getProduct().getProductUPC().equals(bi.getProduct().getProductUPC())).findAny();
					   			
					   			if (obi.isPresent()) {
					   				BagItem bie = obi.get();
					   				bie.setQuantity(bi.getQuantity());
					   				return bie;
					   			}
					   			
					   			return bagItemMapper.doToEntity(bi);
					   		})
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
	public Bag entityToDo(Person p, io.nzbee.entity.bag.Bag e) {
		// TODO Auto-generated method stub
		return null;
	}

}
