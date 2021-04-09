package io.nzbee.entity.party.address;

import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IPartyAddressService extends IDao<PartyAddressEntity> {

	Optional<PartyAddressEntity> findByUsernameAndType(String userName, String addressTypeCode);

	Optional<PartyAddressDTO> findByUsernameAndRoleAndType(String userName, String roleName, String addressTypeCode);

	Optional<PartyAddressEntity> findById(Long id);
	
}
