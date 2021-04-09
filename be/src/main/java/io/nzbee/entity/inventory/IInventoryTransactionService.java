package io.nzbee.entity.inventory;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IService;

public interface IInventoryTransactionService extends IService<InventoryTransaction> {

	List<InventoryTransaction> findByProductCode(String productCode);

	Optional<InventoryTransaction> findById(Long id);

	

}
