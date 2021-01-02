package io.nzbee.entity.party.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.customer.address.Address;
import io.nzbee.entity.party.person.ICustomerMapper;

@Component(value="addressMapper")
public class AddressMapperImpl implements IAddressMapper {

	@Autowired
	private ICustomerMapper personMapper;
	
	@Override
	public Address DTOToDo(PartyAddressDTO dto) {
		return new Address(
				personMapper.DTOToDo(dto.getPerson()), 
				dto.getAddressLine1(),
				dto.getAddressLine2(),
				dto.getAddressLine3(),
				dto.getCountry(),
				dto.getPostcode(),
				dto.getAddressType().getAddressTypeCode(), 
				dto.getAddressType().getAddressTypeDesc()
		);
	}

	@Override
	public PartyAddressEntity doToEntity(Address d) {
		// TODO Auto-generated method stub
		return null;
	}

}
