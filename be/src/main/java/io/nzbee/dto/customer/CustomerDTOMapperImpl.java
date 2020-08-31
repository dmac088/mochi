package io.nzbee.dto.customer;

import io.nzbee.domain.customer.Customer;

public class CustomerDTOMapperImpl implements ICustomerDTOMapper {

	@Override
	public CustomerDTOOut doToDto(Customer d) {
		CustomerDTOOut cdo = new CustomerDTOOut();
		cdo.setCustomerId(d.getCustomerID());
		cdo.setGivenName(d.getGivenName());
		return cdo;
	}

	@Override
	public Customer dtoToDo(CustomerDTOIn dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
