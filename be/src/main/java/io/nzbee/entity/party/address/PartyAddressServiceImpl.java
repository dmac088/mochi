package io.nzbee.entity.party.address;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyAddressServiceImpl implements IPartyAddressService {

	@Autowired
	private IPartyAddressEntityRepository partyAddressRepository;
	
	@Override
	public Optional<PartyAddressEntity> findById(long id) {
		return partyAddressRepository.findById(id);
	}

	@Override
	public Optional<PartyAddressEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PartyAddressEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PartyAddressEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
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
