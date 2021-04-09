package io.nzbee.entity.inventory.type;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryTypeServiceImpl implements IInventoryTypeService {

	@Autowired
	private IInventoryTypeRepository inventoryTypeRepository;

	@Override
	public Optional<InventoryType> findByCode(String code) {
		return inventoryTypeRepository.findByInventoryTypeCode(code);
	}

	@Override
	public void save(InventoryType t) {
		inventoryTypeRepository.save(t);
	}

	@Override
	public void update(InventoryType t) {
		inventoryTypeRepository.save(t);
	}

	@Override
	public void delete(InventoryType t) {
		inventoryTypeRepository.delete(t);
	}

}
