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
	public Set<BagEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagEntity> findById(Long id) {
		return bagRepository.findById(id);
	}

	@Override
	public Optional<BagEntity> findByCode(String userName) {
		return bagRepository.findByPartyPartyUserUsername(userName);
	}

	@Override
	public Set<BagEntity> findAll(Set<String> userNames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(BagEntity t) {
		bagRepository.save(t);
	}

	@Override
	public void update(BagEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BagEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<BagDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<BagDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<BagDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagDTO> findByCode(String locale, String currency, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagDTO> findDTOByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagEntity> findEntityByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagEntity> findEntityByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

}
