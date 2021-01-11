package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.nzbee.entity.StringCollectionWrapper;


@Component(value = "promotionEntityDao")
public class PromotionDaoPostgresImpl implements IPromotionDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<PromotionDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<PromotionDTO> findByCode(String locale, String code) {
		LOGGER.debug("call PromotionDaoPostgresImpl.findByCode parameters : {}, {}", locale, code);
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL())
				 .setParameter("locale", locale)
				 .setParameter("promoCode", code);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new PromotionDTOResultTransformer());
		
		try {
			PromotionDTO result = (PromotionDTO) query.getSingleResult();
			return Optional.ofNullable(result);
		} catch(NoResultException nre) {
			return Optional.empty();
		}
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

	private String constructSQL() {
		return 
		"SELECT " +
		"	   promo.prm_id, " +
		"	   promo.prm_cd, " +
		"      prmlcl.prm_desc, " +
		"      promo.prm_st_dt, " +
		"      promo.prm_en_dt, " +
		"	   promo.prm_mec_id, " +
		"	   promomec.prm_mec_cd, " +
		"	   promomec.prm_mec_desc, " +
		"      :locale as lcl_cd " +
		
		"FROM mochi.promotion promo " +
		
		"	INNER JOIN mochi.promotion_attr_lcl prmlcl " +
		"	ON promo.prm_id = prmlcl.prm_id " +
		"	AND prmlcl.lcl_cd = :locale " +
		
		"	INNER JOIN mochi.promotion_mechanic promomec " +
		"	ON promo.prm_mec_id =  promomec.prm_mec_id " +
		"	" +
		"WHERE promo.prm_cd = :promoCode "; 
	}

	@Override
	public List<PromotionDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}
}
