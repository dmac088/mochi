package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.inventory.location.InventoryLocation;

@Service(value = "inventoryLocationEntityBeanFactory")
@Profile(value = "tst")
public class InventoryLocationEntityBeanFactory {

	@Bean
	public final InventoryLocation getInventoryLocationEntityBean() {
		InventoryLocation inventoryLocation = new InventoryLocation();
		
		inventoryLocation.setLocationCode("TST01");
		inventoryLocation.setLocationDesc("test location");
		
		return inventoryLocation;
	}
	
}
