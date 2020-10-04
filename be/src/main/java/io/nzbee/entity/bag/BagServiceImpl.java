package io.nzbee.entity.bag;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="bagEntityService")
public class BagServiceImpl implements IBagService {

	@Autowired
	private IBagRepository bagRepository;
	
	@Override
	public List<Bag> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Bag> findById(long id) {
		return bagRepository.findById(id);
	}

	@Override
	public Optional<Bag> findByCode(String userName) {
		return bagRepository.findByPartyPartyUserUsername(userName);
	}

	@Override
	public void save(Bag t) {
		bagRepository.save(t);
	}

	@Override
	public void update(Bag t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Bag t) {
		// TODO Auto-generated method stub
		
	}

}
