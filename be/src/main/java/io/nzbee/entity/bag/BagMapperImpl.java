package io.nzbee.entity.bag;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.party.person.IPersonMapper;
import io.nzbee.entity.party.person.Person;

public class BagMapperImpl implements IBagMapper {
	
	private IPersonMapper personMapper;

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
		return b;
	}


}
