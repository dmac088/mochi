package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.inventory.InventoryTransaction;


@Service(value = "inventoryTransactionEntityBeanFactory")
@Profile(value = "tst")
public class InventoryTransactionEntityBeanFactory {

	@Bean
	public final InventoryTransaction getInventoryTransactionEntityBean() {
		final InventoryTransaction inventoryTransaction = new InventoryTransaction();
		
		return inventoryTransaction;
	}
	
}
