package io.nzbee.test.integration.entity.beans.inventory.location;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.inventory.location.InventoryLocation;

@Service
@Profile(value = "it")
public class InventoryLocationEntityBeanFactory implements IInventoryLocationEntityBeanFactory {

	@Override
	public final InventoryLocation getBean() {
		InventoryLocation inventoryLocation = new InventoryLocation();
		
		inventoryLocation.setLocationCode("TST01");
		inventoryLocation.setLocationDesc("test location");
		inventoryLocation.setLocationIsActive(true);
		
		return inventoryLocation;
	}
	
}
