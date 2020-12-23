package io.nzbee.entity.bag.item;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IBagItemRepository extends CrudRepository<BagItemEntity, Long> {
	
	List<BagItemEntity> findAll();
	
}

