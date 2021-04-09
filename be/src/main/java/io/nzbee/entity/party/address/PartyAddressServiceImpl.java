package io.nzbee.entity.party.address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyAddressServiceImpl implements IPartyAddressService {

	@Autowired
	private IPartyAddressEntityRepository partyAddressRepository;
	
	@Override
	public Optional<PartyAddressEntity> findById(Long id) {
		return partyAddressRepository.findById(id);
	}

	@Override
	public void save(PartyAddressEntity t) {
		partyAddressRepository.save(t);
	}

	@Override
	public void update(PartyAddressEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PartyAddressEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<PartyAddressEntity> findByUsernameAndType(String userName, String addressTypeCode) {
		return partyAddressRepository.findByPartyPartyUserUsernameAndTypeAddressTypeCode(userName, addressTypeCode);
	}

	@Override
	public Optional<PartyAddressDTO> findByUsernameAndRoleAndType(String userName, String roleName, String addressTypeCode) {
		return partyAddressRepository.findByUsernameAndRole(userName, roleName, addressTypeCode);
	}
	
	

}
