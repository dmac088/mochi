package io.nzbee.entity.bag;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="bagEntityService")
public class BagServiceImpl implements IBagService {

	@Autowired
	private IBagRepository bagRepository;
	
	@Override
	public Set<Bag> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Bag> findById(Long id) {
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
