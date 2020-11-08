package io.nzbee.entity.bag.item;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;

public interface IBagItemRepository extends CrudRepository<BagItemEntity, Long> {
	
	Set<BagItemEntity> findAll();
	
	
	//@Query()
//	Set<BagItemDTO> findAllByBagId(Long bagId);
}

