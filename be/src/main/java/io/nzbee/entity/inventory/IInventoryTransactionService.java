package io.nzbee.entity.inventory;

import java.util.Set;

import io.nzbee.entity.IService;

public interface IInventoryTransactionService extends IService<InventoryTransaction> {

	Set<InventoryTransaction> findByProductCode(String productCode);

	

}
