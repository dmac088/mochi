package io.nzbee.entity.party.person;

import java.util.Optional;

import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.IMapper;

public interface IPersonMapper extends IMapper<Customer, io.nzbee.entity.party.person.Person> {

	Optional<Customer> entityToDo(Optional<Person> e);
	
}
