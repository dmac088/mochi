package io.nzbee.entity.party.address;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<Address, Long> {

	Optional<Address> findByPartyUserUsername(String userName);

}
 