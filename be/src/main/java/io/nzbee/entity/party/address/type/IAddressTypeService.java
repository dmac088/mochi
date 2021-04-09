package io.nzbee.entity.party.address.type;

import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IAddressTypeService extends IDao<AddressTypeEntity> {

	Optional<AddressTypeEntity> findById(long id);

	Optional<AddressTypeEntity> findByCode(String code);
	
	
}
