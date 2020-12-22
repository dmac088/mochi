package io.nzbee.entity.party.address;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IPartyAddressEntityRepository extends CrudRepository<PartyAddressEntity, Long> {

	Optional<PartyAddressEntity> findByPartyPartyUserUsername(String userName);

}
 