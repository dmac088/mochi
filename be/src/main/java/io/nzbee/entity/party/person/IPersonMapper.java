package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.IMapper;

public interface IPersonMapper extends IMapper<Customer, io.nzbee.entity.party.person.Person> {

	Customer entityToDo(io.nzbee.entity.party.person.Person e);
	
}
