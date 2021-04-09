package io.nzbee.entity.inventory.location;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IInventoryLocationService extends IService<InventoryLocation> {

	Optional<InventoryLocation> findByCode(String locationCode);

	Optional<InventoryLocation> findById(Long id);
	
}
