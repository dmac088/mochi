package io.nzbee.entity.party.address;

import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IPartyAddressService extends IDao<PartyAddressEntity> {
	
	Optional<PartyAddressEntity> findByUsername(String userName);
	
}
