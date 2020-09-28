package io.nzbee.entity.inventory.location;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IInventoryLocationRepository extends CrudRepository<InventoryLocation, Long> {
	
	Optional<InventoryLocation> findByLocationCode(String locationCode);
	
}

