package io.nzbee.entity.bag.status;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagItemStatusServiceImpl implements IBagItemStatusService {

	@Autowired
	private IBagItemStatusRepository bagStatusRepository;

	@Override
	public Optional<BagItemStatus> findById(Long id) {
		return bagStatusRepository.findById(id);
	}

	@Override
	public Optional<BagItemStatus> findByCode(String code) {
		return bagStatusRepository.findByBagStatusCode(code);
	}

	@Override
	public void save(BagItemStatus t) {
		bagStatusRepository.save(t);
	}

	@Override
	public void update(BagItemStatus t) {
		bagStatusRepository.save(t);
		
	}

	@Override
	public void delete(BagItemStatus t) {
		bagStatusRepository.delete(t);
	}


}
