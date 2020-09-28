package io.nzbee.entity.inventory;

import org.springframework.data.repository.CrudRepository;

public interface IInventoryTransactionRepository extends CrudRepository<InventoryTransaction, Long> {
	
}

