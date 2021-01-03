package io.nzbee.dto.customer.address;

import org.springframework.stereotype.Component;
import io.nzbee.domain.customer.address.Address;
import io.nzbee.dto.customer.CustomerDTOIn;

@Component
public class CustomerAddressDTOMapperImpl implements ICustomerAddressDTOMapper {

	@Override
	public CustomerAddressDTOOut doToDto(Address d) {
		CustomerAddressDTOOut cdo = new CustomerAddressDTOOut();
		cdo.setAddressLine1(d.getAddressLine1());
		cdo.setAddressLine2(d.getAddressLine2());
		cdo.setAddressLine3(d.getAddressLine3());
		cdo.setCountry(d.getCountry());
		cdo.setPostCode(d.getPostCode());
		return cdo;
	}

	@Override
	public Address dtoToDo(CustomerDTOIn dto) {
		// TODO Auto-generated method stub
		return null;
	}


}
