package io.nzbee.entity.inventory.location;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

public class InventoryLocationServiceImpl implements IInventoryLocationService {

	@Autowired
	private IInventoryLocationRepository inventoryLocationRepository;
	
	@Override
	public List<InventoryLocation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InventoryLocation> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InventoryLocation> findById(long id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(InventoryLocation t) {
		// TODO Auto-generated method stub
		
	}

}
