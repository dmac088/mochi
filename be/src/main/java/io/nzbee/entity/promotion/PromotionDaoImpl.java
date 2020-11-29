package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component(value = "promotionEntityDao")
public class PromotionDaoImpl implements IPromotionDao {
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<PromotionDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PromotionDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(PromotionEntity t) {
		em.persist(t);
	}

	@Override
	public void update(PromotionEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(PromotionEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PromotionDTO> findAll(String locale, Set<String> PromotionCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionDTO> findByProductCode(String locale, String productCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionDTO> findAllByCategory(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PromotionEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
