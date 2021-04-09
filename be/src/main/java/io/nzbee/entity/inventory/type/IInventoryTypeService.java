package io.nzbee.entity.inventory.type;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IInventoryTypeService extends IService<InventoryType> {

	Optional<InventoryType> findByCode(String code);

}
