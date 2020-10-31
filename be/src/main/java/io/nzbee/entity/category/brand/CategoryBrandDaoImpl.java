package io.nzbee.entity.category.brand;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CategoryBrandDaoImpl implements ICategoryBrandDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<CategoryBrandEntity> findAllByBrandCode(String locale, String currency, String brandCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public List<CategoryBrand> findAllByBrandCode(String locale, String currency, String brandCode) {
//		LOGGER.debug("call CategoryBrandDaoImpl.findAllByBrandCode parameters : {}, {}, {}", locale, currency, brandCode);
//		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		
//		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
//		
//		Root<CategoryBrand> root = cq.from(CategoryBrand.class);
//		
//		Join<CategoryBrand, CategoryAttributeEntity> attribute = root.join(Category_.attributes);
//		Join<CategoryBrand, BrandEntity> products = root.join(CategoryBrand_.brands);
//		Join<CategoryBrand, CategoryType> type = root.join(Category_.categoryType);
//		
//		cq.multiselect(	root.get(CategoryBrand_.categoryId).alias("categoryId"),
//						root.get(CategoryBrand_.categoryCode).alias("categoryCode"),
//						attribute.get(CategoryAttribute_.categoryAttributeId).alias("categoryAttributeId"),
//						attribute.get(CategoryAttribute_.categoryDesc).alias("categoryDesc"),
//						type.get(CategoryType_.categoryTypeCode).alias("categoryTypeCode"),
//						type.get(CategoryType_.categoryTypeDesc).alias("categoryTypeDesc")
//		);
//		
//		List<Predicate> conditions = new ArrayList<Predicate>();
//	
//		if(!(brandCode == null)) {
//			
//			cq.where(cb.and(
//					cb.equal(products.get(Brand_.brandCode), brandCode),
//					cb.equal(attribute.get(CategoryAttribute_.lclCd), locale)
//					)
//			);
//			
//			conditions.add(cb.equal(products.get(Brand_.brandCode), brandCode));
//		}
//		
//		TypedQuery<Tuple> query = em.createQuery(cq);
//		List<Tuple> tuples = query.getResultList();
//		
//		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
//	}
	
	@Override
	public Optional<CategoryBrandDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrandDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrandDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CategoryBrandDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CategoryBrandDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryBrandEntity t) {
		em.persist(t);
	}

	@Override
	public void update(CategoryBrandEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryBrandEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryBrandDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBrandDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBrandDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBrandDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

//	@Override
//	public CategoryBrand objectToEntity(Object[] o, String locale, String currency) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public CategoryBrand objectToEntity(Tuple t, String locale, String currency) {
//		CategoryBrand cb = objectToEntity(t,locale);
//		cb.setCurrency(currency);
//		return cb; 
//	}
//
//	@Override
//	public CategoryBrand objectToEntity(Object[] o, String locale) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public CategoryBrand objectToEntity(Tuple t, String locale) {
//		CategoryAttributeEntity ca = new CategoryAttributeEntity();
//		ca.setCategoryDesc(t.get("categoryDesc").toString());
//		ca.setLclCd(locale);
//		
//		CategoryType ct = new CategoryType();
//		ct.setCategoryTypeCode(t.get("categoryTypeCode").toString());
//		ct.setCategoryTypeDesc(t.get("categoryTypeDesc").toString());
//		
//		CategoryBrand cp = new CategoryBrand();
//		cp.setCategoryId(Long.parseLong(t.get("categoryId").toString()));
//		cp.setCategoryCode(t.get("categoryCode").toString());
//		cp.setLocale(locale);
//		
//		cp.setCategoryAttribute(ca);
//		cp.setCategoryType(ct);
//		
//		return cp; 
//	}

}
