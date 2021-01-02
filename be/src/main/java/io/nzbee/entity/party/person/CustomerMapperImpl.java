package io.nzbee.entity.party.person;

import io.nzbee.Constants;
import io.nzbee.domain.customer.Customer;
import io.nzbee.entity.role.customer.CustomerEntity;

import org.springframework.stereotype.Component;

@Component(value="customerMapper")
public class CustomerMapperImpl implements ICustomerMapper {

	@Override
	public Customer DTOToDo(PersonDTO dto) {

		Customer co = new Customer(
			dto.getGivenName(),
			dto.getFamilyName(),
			dto.getUserName(),
			dto.getCustomerNumber(),
			dto.getEnabled()
		);		
		return co;	
	}

	@Override
	public PersonEntity doToEntity(Customer d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	public Customer EntityToDo(PersonEntity person) {
		Customer co = new Customer(
						person.getGivenName(),
						person.getFamilyName(),
						person.getUser().getUsername(),
						((CustomerEntity) person.getPartyRoles().stream().filter(r -> r.getRoleType().getRoleTypeDesc().equals(Constants.partyRoleCustomer)).findAny().get()).getCustomerNumber(),
						person.getUser().isEnabled()
					);
		return co;
	}
}
