package io.nzbee.entity.inventory.type;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IInventoryTypeRepository extends CrudRepository<InventoryType, Long>  {

	Optional<InventoryType> findByInventoryTypeCode(String code);
	
}
