package io.nzbee.entity.party.address;

import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IPartyAddressService extends IDao<PartyAddressEntity> {
	
	Optional<PartyAddressEntity> findEntityByUsername(String userName);

	Optional<PartyAddressDTO> findByUsernameAndRole(String userName, String roleName);
	
}
