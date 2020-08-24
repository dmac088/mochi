package io.nzbee.entity.bag.status;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
public interface IBagStatusRepository extends CrudRepository<BagStatus, Long>  {

	Optional<BagStatus> findByBagStatusCode(String code);
	
}
