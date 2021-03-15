package io.nzbee.view.customer.address;

import org.springframework.stereotype.Component;
import io.nzbee.domain.customer.address.Address;

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
		cdo.setAddressTypeCode(d.getAddressTypeCode());
		cdo.setAddressTypeDesc(d.getAddressTypeDesc());
		return cdo;
	}

	@Override
	public Address dtoToDo(CustomerAddressDTOIn dto) {
		return null;
	
	}


}
