package io.nzbee.entity.category.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.Brand_;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;

@Service
public class CategoryBrandDaoImpl implements ICategoryBrandDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<CategoryBrand> findAllByBrandCode(String locale, String currency, String brandCode) {
		LOGGER.debug("call CategoryBrandDaoImpl.findAllByBrandCode parameters : {}, {}, {}", locale, currency, brandCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<CategoryBrand> root = cq.from(CategoryBrand.class);
		
		Join<CategoryBrand, CategoryAttribute> attribute = root.join(Category_.attributes);
		Join<CategoryBrand, Brand> products = root.join(CategoryBrand_.brands);
		Join<CategoryBrand, CategoryType> type = root.join(Category_.categoryType);
		
		cq.multiselect(	root.get(CategoryBrand_.categoryId).alias("categoryId"),
						root.get(CategoryBrand_.categoryCode).alias("categoryCode"),
						attribute.get(CategoryAttribute_.categoryAttributeId).alias("categoryAttributeId"),
						attribute.get(CategoryAttribute_.categoryDesc).alias("categoryDesc"),
						type.get(CategoryType_.categoryTypeCode).alias("categoryTypeCode"),
						type.get(CategoryType_.categoryTypeDesc).alias("categoryTypeDesc")
		);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
	
		if(!(brandCode == null)) {
			
			cq.where(cb.and(
					cb.equal(products.get(Brand_.brandCode), brandCode),
					cb.equal(attribute.get(CategoryAttribute_.lclCd), locale)
					)
			);
			
			conditions.add(cb.equal(products.get(Brand_.brandCode), brandCode));
		}
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
	}
	
	@Override
	public Optional<CategoryBrand> findById(String locale, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrand> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryBrand> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrand> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryBrand> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryBrand t) {
		em.persist(t);
		em.flush();
	}

	@Override
	public void update(CategoryBrand t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryBrand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryBrand objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBrand objectToEntity(Tuple t, String locale, String currency) {
		CategoryBrand cb = objectToEntity(t,locale);
		cb.setCurrency(currency);
		return cb; 
	}

	@Override
	public CategoryBrand objectToEntity(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryBrand objectToEntity(Tuple t, String locale) {
		CategoryAttribute ca = new CategoryAttribute();
		ca.setCategoryDesc(t.get("categoryDesc").toString());
		ca.setLclCd(locale);
		
		CategoryType ct = new CategoryType();
		ct.setCategoryTypeCode(t.get("categoryTypeCode").toString());
		ct.setCategoryTypeDesc(t.get("categoryTypeDesc").toString());
		
		CategoryBrand cp = new CategoryBrand();
		cp.setCategoryId(Long.parseLong(t.get("categoryId").toString()));
		cp.setCategoryCode(t.get("categoryCode").toString());
		cp.setLocale(locale);
		
		cp.setCategoryAttribute(ca);
		cp.setCategoryType(ct);
		
		return cp; 
	}

}
