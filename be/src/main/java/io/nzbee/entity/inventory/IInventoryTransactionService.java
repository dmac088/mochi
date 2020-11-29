package io.nzbee.entity.inventory;

import java.util.List;
import io.nzbee.entity.IService;

public interface IInventoryTransactionService extends IService<InventoryTransaction> {

	List<InventoryTransaction> findByProductCode(String productCode);

	

}
