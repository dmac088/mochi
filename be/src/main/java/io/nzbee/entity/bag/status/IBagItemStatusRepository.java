package io.nzbee.entity.bag.status;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
public interface IBagItemStatusRepository extends CrudRepository<BagItemStatus, Long>  {

	Optional<BagItemStatus> findByBagStatusCode(String code);
	
}
