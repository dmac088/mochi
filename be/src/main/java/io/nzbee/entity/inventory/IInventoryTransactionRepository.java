package io.nzbee.entity.inventory;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IInventoryTransactionRepository extends CrudRepository<InventoryTransaction, Long> {
	
	Set<InventoryTransaction> findByProductProductUPC(String productCode);
	
}

