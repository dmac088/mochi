package io.nzbee.entity.bag.status;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagStatusServiceImpl implements IBagStatusService {

	@Autowired
	private IBagStatusRepository bagStatusRepository;
	
	@Override
	public List<BagStatus> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<BagStatus> findById(long id) {
		return bagStatusRepository.findById(id);
	}

	@Override
	public Optional<BagStatus> findByCode(String code) {
		return bagStatusRepository.findByBagStatusCode(code);
	}

	@Override
	public void save(BagStatus t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BagStatus t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BagStatus t) {
		// TODO Auto-generated method stub
		
	}


}
