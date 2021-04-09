package io.nzbee.entity.product.shipping.destination;

import java.util.Optional;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ShippingDestinationDaoImpl implements IShippingDestinationDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IShippingDestinationRepository shippingDestinationRepository ;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	

	@Override
	public Optional<ShippingDestinationDTO> findByCode(String locale, String code) {
		LOGGER.debug("call ShippingDestinationDaoImpl.findByCode parameters : {}, {}", locale, code);
		return shippingDestinationRepository.findByCode(locale, code);
	}
	
	@Override
	public Optional<ShippingDestinationEntity> findByCode(String code) {
		LOGGER.debug("call ShippingDestinationDaoImpl.findByCode parameters : {}", code);
		return shippingDestinationRepository.findByShippingDestinationCode(code);
	}


	@Override
	public Optional<ShippingDestinationEntity> findById(Long id) {
		return shippingDestinationRepository.findById(id);
	}

	@Override
	public void save(ShippingDestinationDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ShippingDestinationDTO t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingDestinationDTO t) {
		// TODO Auto-generated method stub
		
	}

	
	
}
