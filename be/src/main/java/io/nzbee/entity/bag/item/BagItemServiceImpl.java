package io.nzbee.entity.bag.item;

import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagItemServiceImpl implements IBagItemService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagItemRepository bagItemRepository;
	
	@Override
	public Set<BagItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagItem> findById(Long id) {
		return bagItemRepository.findById(id);
	}

	@Override
	public Optional<BagItem> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BagItem t) {
		bagItemRepository.save(t);
		
	}

	@Override
	public void update(BagItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BagItem t) {
		LOGGER.debug("call BagItemServiceImpl.delete with parameters {}", t.getBagItemId());
		bagItemRepository.delete(t);
	}

}
