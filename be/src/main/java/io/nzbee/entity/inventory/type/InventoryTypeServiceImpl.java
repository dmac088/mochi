package io.nzbee.entity.inventory.type;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryTypeServiceImpl implements IInventoryTypeService {

	@Autowired
	private IInventoryTypeRepository inventoryTypeRepository;
	
	@Override
	public Set<InventoryType> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InventoryType> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InventoryType> findByCode(String code) {
		return inventoryTypeRepository.findByInventoryTypeCode(code);
	}

	@Override
	public void save(InventoryType t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(InventoryType t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(InventoryType t) {
		// TODO Auto-generated method stub
		
	}

}
