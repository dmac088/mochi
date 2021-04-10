package io.nzbee.view.customer;

import org.springframework.stereotype.Component;
import io.nzbee.domain.customer.Customer;

@Component
public class CustomerDTOMapperImpl implements ICustomerDTOMapper {

	@Override
	public CustomerDTOOut toView(Customer d) {
		CustomerDTOOut cdo = new CustomerDTOOut();
		cdo.setCustomerId(d.getCustomerID());
		cdo.setGivenName(d.getGivenName());
		cdo.setFamilyName(d.getFamilyName());
		return cdo;
	}


}
