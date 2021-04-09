package io.nzbee.entity.bag;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="bagEntityService")
public class BagServiceImpl implements IBagService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IBagRepository bagRepository;
	
	@Autowired
	private IBagDao bagDao;
	
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
	public Optional<BagDTO> findByCode(String locale, String currency, String rootCategory, String userName) {
		LOGGER.debug("call BagServiceImpl.findByCode with parameter {}, {}, {}, {}", locale, currency, rootCategory, userName);
		return bagDao.findByCode(locale, currency, rootCategory, userName);
	}

	@Override
	public void save(BagEntity t) {
		bagRepository.save(t);
	}

	@Override
	public void update(BagEntity t) {
		bagRepository.save(t);
	}

	@Override
	public void delete(BagEntity t) {
		bagRepository.delete(t);
	}

}
