package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component(value="personMapper")
public class PersonMapper implements IPersonMapper {

	@Override
	public Customer DTOToDo(PersonDTO dto) {

		Customer co = new Customer(
			dto.getGivenName(),
			dto.getFamilyName(),
			e.getUser().getUsername(),
			((io.nzbee.entity.role.customer.Customer) 
				e.getPartyRoles().stream().filter(r -> r.getRoleType().getRoleTypeDesc().equals(Customer.class.getSimpleName())).findFirst().get()).getCustomerNumber(),
			e.getUser().isEnabled()
		);		
		return co;	
	}

	@Override
	public PersonEntity doToEntity(Customer d) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
