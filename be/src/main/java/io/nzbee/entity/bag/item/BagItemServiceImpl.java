package io.nzbee.entity.bag.item;

import java.util.List;
import java.util.Optional;
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
	public List<BagItemEntity> findAll() {
		return bagItemRepository.findAll();
	}

	@Override
	public Optional<BagItemEntity> findById(Long id) {
		return bagItemRepository.findById(id);
	}

	@Override
	public Optional<BagItemEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BagItemEntity t) {
		bagItemRepository.save(t);
		
	}

	@Override
	public void update(BagItemEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BagItemEntity t) {
		LOGGER.debug("call BagItemServiceImpl.delete with parameters {}", t.getBagItemId());
		bagItemRepository.delete(t);
	}

}
