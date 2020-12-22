package io.nzbee.entity.party.address.type;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IAddressTypeEntityRepository extends CrudRepository<AddressTypeEntity, Long> {

	Optional<AddressTypeEntity> findByAddressTypeCode(String addressTypeCode);
	
	Optional<AddressTypeEntity> findByAddressTypeDesc(String addressTypeDesc);

}
 