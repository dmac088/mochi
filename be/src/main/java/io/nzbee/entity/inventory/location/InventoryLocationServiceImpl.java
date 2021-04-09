package io.nzbee.entity.inventory.location;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryLocationServiceImpl implements IInventoryLocationService {

	@Autowired
	private IInventoryLocationRepository inventoryLocationRepository;
	
	@Override
	public Optional<InventoryLocation> findById(Long id) {
		return inventoryLocationRepository.findById(id);
	}

	@Override
	public Optional<InventoryLocation> findByCode(String code) {
		return inventoryLocationRepository.findByLocationCode(code);
	}

	@Override
	public void save(InventoryLocation t) {
		inventoryLocationRepository.save(t);
	}

	@Override
	public void update(InventoryLocation t) {
		inventoryLocationRepository.save(t);
		
	}

	@Override
	public void delete(InventoryLocation t) {
		inventoryLocationRepository.delete(t);
	}

}
