package io.nzbee.entity.bag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service(value="bagEntityService")
public class BagServiceImpl implements IBagService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagRepository bagRepository;
	
	@Autowired
	private IBagDao bagDao;
	
	@Override
	public List<BagEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BagEntity> findById(Long id) {
		return bagRepository.findById(id);
	}

	@Override
	public Optional<BagEntity> findByCode(String userName) {
		LOGGER.debug("call BagServiceImpl.findByCode() with parameter {}", userName);
		return bagRepository.findByPartyPartyUserUsername(userName);
	}

	@Override
	public List<BagEntity> findAll(Set<String> userNames) {
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
	public List<BagDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BagDTO> findAll(String locale, StringCollectionWrapper codes) {
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
		LOGGER.debug("call BagServiceImpl.findByCode with parameter {}, {}, {}", locale, currency, userName);
		return bagDao.findByCode(locale, currency, userName);
	}

	@Override
	public Optional<BagDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BagDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
