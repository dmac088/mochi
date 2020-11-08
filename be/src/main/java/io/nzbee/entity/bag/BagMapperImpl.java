package io.nzbee.entity.bag;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.bag.item.BagItemEntity;
import io.nzbee.entity.bag.item.IBagItemMapper;
import io.nzbee.entity.party.person.ICustomerMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.CustomerDTO;

@Component
public class BagMapperImpl implements IBagMapper {
	
	@Autowired
	private ICustomerMapper personMapper;
	
	@Autowired
	private IBagItemMapper bagItemMapper;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IBagService bagService;
	
	@Override
	public Bag DTOToDo(String locale, String currency, CustomerDTO pDto, BagDTO bDto) {
		
		//we need a customer to instantiate a new bag
		Customer c = personMapper.DTOToDo(pDto);
		
		//create a new bag domain object
		Bag b = new Bag(c);
		
		
		//bagItemService
		
		
		
		//map the entity bagItems to the domain bagItems
		Set<BagItem> sbi = 	 bDto.getBagItems().stream()
							 .map(bi -> bagItemMapper.DTOToDo(bi))
							 .collect(Collectors.toSet());
		
		//use the add item method on the domain object to 
		//ensure business rules are fired against each added bagItem
		sbi.forEach(bi -> {
			b.addItem(bi.getProduct(), bi.getQuantity());
		});
		
		return b;
	}

	@Override
	public io.nzbee.entity.bag.BagEntity doToEntity(Bag d) {
		//get the bag, status, and customer from the database
		Optional<io.nzbee.entity.bag.BagEntity> obe 					= bagService.findByCode(d.getCustomer().getUserName());		
		Optional<io.nzbee.entity.party.person.PersonEntity> op	 		= personService.findByUsernameAndRole(d.getCustomer().getUserName(), Constants.partyRoleCustomer);
					
		io.nzbee.entity.bag.BagEntity nbe = new io.nzbee.entity.bag.BagEntity();
		nbe.setBagCreatedDateTime(LocalDateTime.now());
		nbe.setBagUpdatedDateTime(LocalDateTime.now());
				
		//use the existing bag if it exists otherwise use newly created
		io.nzbee.entity.bag.BagEntity b = (obe.isPresent())
									? obe.get()
									: nbe;		
									
		b.setBagUpdatedDateTime(LocalDateTime.now());							
									
		//map the domain bagItems to entity bagItems
		Set<BagItemEntity> sbi = d.getBagItems().stream()
					   		.map(bi -> {
					   			Optional<BagItemEntity> obi = b.getBagItems().stream().filter(i -> i.getProduct().getProductUPC().equals(bi.getProduct().getProductUPC())).findAny();
					   			
					   			if (obi.isPresent()) {
					   				BagItemEntity bie = obi.get();
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
	public Bag DTOToDo(BagDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
