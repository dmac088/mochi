package io.nzbee.entity.party.person;

import io.nzbee.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component(value="customerMapper")
public class CustomerMapper implements ICustomerMapper {

	@Override
	public Customer DTOToDo(CustomerDTO dto) {

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
	
}
