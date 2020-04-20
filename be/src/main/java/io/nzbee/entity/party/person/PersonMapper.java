package io.nzbee.entity.party.person;


import io.nzbee.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component(value="personMapper")
public class PersonMapper implements IPersonMapper {

	@Override
	public Customer entityToDo(Person e) {
		// TODO Auto-generated method stub
		return null;
	}

}
