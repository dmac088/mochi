package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component(value="personMapper")
public class PersonMapper implements IPersonMapper {

	@Override
	public Customer DTOToDo(Person e) {

		io.nzbee.domain.customer.Customer co 
		= new Customer(
			e.getGivenName(),
			e.getFamilyName(),
			e.getUser().getUsername(),
			((io.nzbee.entity.role.customer.Customer) 
				e.getPartyRoles().stream().filter(r -> r.getRoleType().getRoleTypeDesc().equals(Customer.class.getSimpleName())).findFirst().get()).getCustomerNumber(),
			e.getUser().isEnabled()
		);		
		return co;	
	}

	
}
