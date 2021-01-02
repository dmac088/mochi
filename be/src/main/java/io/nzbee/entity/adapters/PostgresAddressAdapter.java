package io.nzbee.entity.adapters;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.domain.customer.address.Address;
import io.nzbee.domain.ports.IAddressPortService;
import io.nzbee.entity.party.address.IAddressMapper;
import io.nzbee.entity.party.address.IPartyAddressService;
import io.nzbee.entity.party.address.PartyAddressDTO;

@Service
public class PostgresAddressAdapter implements IAddressPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPartyAddressService addressService;
	
	@Autowired 
	private IAddressMapper addressMapper;
	
	@Override
	public Address findByUsername(String userName) {
		LOGGER.debug("call PostgresAddressAdapter.findByUsername with parameter {}", userName);
		
		Optional<PartyAddressDTO> oa = addressService.findByUsernameAndRole(userName, Constants.partyRoleCustomer);
		
		PartyAddressDTO a = oa.get();
	
		return addressMapper.DTOToDo(a);
	}
	
	@Override
	public void save(Address domainObject) {
		LOGGER.debug("call PostgresAddressAdapter.save()");
		addressService.save(addressMapper.doToEntity(domainObject));
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
