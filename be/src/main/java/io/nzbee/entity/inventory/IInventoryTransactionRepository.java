package io.nzbee.entity.inventory;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface IInventoryTransactionRepository extends CrudRepository<InventoryTransaction, Long> {
	
	List<InventoryTransaction> findByProductProductUPC(String productCode);
	
}

