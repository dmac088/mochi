package io.nzbee.entity.inventory;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryTransactionServiceImpl implements IInventoryTransactionService {

	@Autowired
	private IInventoryTransactionRepository inventoryTransactionRepository;
	
	@Override
	public Set<InventoryTransaction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InventoryTransaction> findById(Long id) {
		return inventoryTransactionRepository.findById(id);
	}

	@Override
	public Optional<InventoryTransaction> findByCode(String code) {
		//inventoryTransactionRepository.find
		return null;
	}
	
	@Override
	public Set<InventoryTransaction> findByProductCode(String productCode) {
		return inventoryTransactionRepository.findByProductProductUPC(productCode);
	}

	@Override
	public void save(InventoryTransaction t) {
		inventoryTransactionRepository.save(t);
	}

	@Override
	public void update(InventoryTransaction t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(InventoryTransaction t) {
		// TODO Auto-generated method stub
		
	}

}
