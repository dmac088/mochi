package io.nzbee.entity.bag.item;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagItemServiceImpl implements IBagItemService {

	@Autowired
	private IBagItemRepository bagItemRepository;
	
	@Override
	public List<BagItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BagItem> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagItem> findById(long id) {
		return bagItemRepository.findById(id);
	}

	@Override
	public Optional<BagItem> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BagItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BagItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BagItem t) {
		// TODO Auto-generated method stub
		
	}

}
