package io.nzbee.entity.bag;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IBagRepository extends CrudRepository<BagEntity, Long> {
	
	Optional<BagEntity> findByPartyPartyUserUsername(String userName);

}

