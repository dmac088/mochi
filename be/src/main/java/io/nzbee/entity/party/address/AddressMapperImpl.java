package io.nzbee.entity.party.address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.domain.customer.address.Address;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.entity.party.person.ICustomerMapper;

@Component(value="addressMapper")
public class AddressMapperImpl implements IAddressMapper {

	@Autowired
	private ICustomerMapper personMapper;
	
	@Autowired
	private IPartyService partyService;
	
	private IAddressTypeService addressTypeService;
	
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
		Optional<Party> op = partyService.findByUsername(d.getCustomer().getUserName());
		Optional<AddressTypeEntity> oat = addressTypeService.findByCode(d.getAddressTypeCode());
		
		PartyAddressEntity pa = new PartyAddressEntity();
		pa.setAddressLine1(d.getAddressLine1());
		pa.setAddressLine2(d.getAddressLine2());
		pa.setAddressLine3(d.getAddressLine3());
		pa.setAddressCountry(d.getCountry());
		pa.setAddressPostCode(d.getPostCode());
		pa.setParty(op.get());
		pa.setType(oat.get());
		
		return pa;
	}

}
