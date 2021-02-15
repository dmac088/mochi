package io.nzbee.entity.product.shipping.destination;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
		LOGGER.debug("call ShippingDestinationDaoImpl.findByCode parameters : {}, {}, {}", locale, code);
		
		return null;
	}
	
	@Override
	public Optional<ShippingDestinationEntity> findByCode(String code) {
		LOGGER.debug("call ShippingDestinationDaoImpl.findByCode parameters : {}", code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<ShippingDestinationEntity> cq = cb.createQuery(ShippingDestinationEntity.class);
		
		Root<ShippingDestinationEntity> root = cq.from(ShippingDestinationEntity.class);

		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(
				cb.equal(root.get(ShippingDestinationEntity_.POSTAGE_DESTINATION_CODE), code)
		);
		
		TypedQuery<ShippingDestinationEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		try {
			ShippingDestinationEntity destination = query.getSingleResult();
			return Optional.ofNullable(destination);
		} catch(NoResultException nre) {
			return Optional.empty();
		}
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
