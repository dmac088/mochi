package io.nzbee.entity.product.shipping.destination;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.entity.StringCollectionWrapper;

public class ShippingDestinationDaoImpl implements IShippingDestinationDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IShippingDestinationRepository shippingDestinationRepository ;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<ShippingDestinationDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public Optional<ShippingDestinationDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingDestinationDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingDestinationDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingDestinationDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingDestinationDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingDestinationDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingDestinationDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingDestinationEntity> findById(long id) {
		return shippingDestinationRepository.findById(id);
	}

	@Override
	public List<ShippingDestinationEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingDestinationEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ShippingDestinationEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ShippingDestinationEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingDestinationEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShippingDestinationDTO> findAll(String locale, Set<String> PromotionCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingDestinationDTO> findByProductCode(String locale, String productCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingDestinationDTO> findAll(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> tagCodes, Double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingDestinationDTO> findAllByCategory(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
