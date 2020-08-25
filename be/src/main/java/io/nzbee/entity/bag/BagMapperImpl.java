package io.nzbee.entity.bag;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.bag.item.IBagItemMapper;
import io.nzbee.entity.party.person.IPersonMapper;
import io.nzbee.entity.party.person.Person;

@Component
public class BagMapperImpl implements IBagMapper {
	
	@Autowired
	private IPersonMapper personMapper;
	
	@Autowired
	private IBagItemMapper bagItemMapper;

	@Override
	public io.nzbee.entity.bag.Bag doToEntity(Bag d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bag entityToDo(io.nzbee.entity.bag.Bag e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bag entityToDo(Person p, io.nzbee.entity.bag.Bag e) {
		Customer c = personMapper.entityToDo(p);
		
		Bag b = new Bag(c);
		
		e.getBagItems().stream()
		 .map(bi -> bagItemMapper.entityToDo(bi))
		 .collect(Collectors.toSet());
		
		return b;
	}


}
