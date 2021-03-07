package io.nzbee.entity.adapters;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.domain.customer.address.Address;
import io.nzbee.domain.ports.IAddressPortService;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.IAddressMapper;
import io.nzbee.entity.party.address.IPartyAddressService;
import io.nzbee.entity.party.address.PartyAddressDTO;
import io.nzbee.entity.party.address.PartyAddressEntity;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.IAddressTypeService;

@Service
public class PostgresAddressAdapter implements IAddressPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPartyAddressService addressService;
	
	@Autowired
	private IPartyService partyService;
	
	@Autowired
	private IAddressTypeService addressTypeService;
	
	@Autowired 
	private IAddressMapper addressMapper;
	
	@Override
	public Address findByUsernameAndType(String userName, String addressTypeCode) {
		LOGGER.debug("call PostgresAddressAdapter.findByUsername with parameter {}", userName);
		
		Optional<PartyAddressDTO> oa = addressService.findByUsernameAndRoleAndType(userName, Constants.partyRoleCustomer, addressTypeCode);
		
		PartyAddressDTO a = oa.get();
	
		return addressMapper.DTOToDo(a);
	}
	
	@Override
	public void save(Address domainObject) {
		LOGGER.debug("call PostgresAddressAdapter.save()");
		Optional<PartyAddressEntity> opa = addressService.findByUsernameAndType(domainObject.getCustomer().getUserName(), domainObject.getAddressTypeCode());
		
		Optional<AddressTypeEntity> oat = addressTypeService.findByCode(domainObject.getAddressTypeCode());
		
		Optional<Party> op = partyService.findByUsername(domainObject.getCustomer().getUserName());
		
		PartyAddressEntity pa = opa.isPresent() 
		? opa.get()
		: new PartyAddressEntity();
		
		pa.setAddressLine1(domainObject.getAddressLine1());
		pa.setAddressLine2(domainObject.getAddressLine2());
		pa.setAddressLine3(domainObject.getAddressLine3());
		pa.setAddressCountry(domainObject.getCountry());
		pa.setAddressPostCode(domainObject.getPostCode());
		pa.setParty(op.get());
		pa.setType(oat.get());
		
		addressService.save(pa);
	}

	@Override
	public void update(Address domainObject) {
		LOGGER.debug("call PostgresAddressAdapter.update()");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Address domainObject) {
		LOGGER.debug("call PostgresAddressAdapter.delete()");
		// TODO Auto-generated method stub
		
	}

}
