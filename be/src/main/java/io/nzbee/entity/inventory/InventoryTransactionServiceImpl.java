package io.nzbee.entity.inventory;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

public class InventoryTransactionServiceImpl implements IInventoryTransactionService {

	@Autowired
	private IInventoryTransactionRepository inventoryTransactionRepository;
	
	@Override
	public List<InventoryTransaction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InventoryTransaction> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InventoryTransaction> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InventoryTransaction> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
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
