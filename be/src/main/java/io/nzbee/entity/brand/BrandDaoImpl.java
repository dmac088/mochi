package io.nzbee.entity.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.nzbee.Globals;
import io.nzbee.entity.brand.Brand_;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.brand.attribute.BrandAttribute_;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.CategoryBrand_;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;
import io.nzbee.entity.tag.Tag_;
import io.nzbee.entity.tag.Tag;

@Component
public class BrandDaoImpl  implements IBrandDao { 
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private Globals globalVars;
	
	@Override
	public Optional<Brand> findById(String locale, String currency, long id) {
		LOGGER.debug("call BrandDaoImpl.findById parameters : {}, {}, {}", locale, currency, id);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(Brand_.brandId), id),
				cb.equal(attribute.get(BrandAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Brand brand = this.objectToEntity(tuple, locale, currency);
			return Optional.ofNullable(brand);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Brand> findByCode(String locale, String currency, String code) {
		LOGGER.debug("call BrandDaoImpl.findByCode parameters : {}, {}, {}", locale, currency, code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(Brand_.brandCode), code),
				cb.equal(attribute.get(BrandAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Brand brand = this.objectToEntity(tuple, locale, currency);
			return Optional.ofNullable(brand);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	@Override
	public Optional<Brand> findByDesc(String locale, String currency, String desc) {
		LOGGER.debug("call BrandDaoImpl.findByDesc parameters : {}, {}, {}", locale, currency, desc);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		cq.where(cb.and(
				cb.equal(attribute.get(BrandAttribute_.brandDesc), desc),
				cb.equal(attribute.get(BrandAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Brand brand = this.objectToEntity(tuple, locale, currency);
			return Optional.ofNullable(brand);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency, Set<String> brandCodes) {
		LOGGER.debug("call BrandDaoImpl.findAll parameters : {}, {}, {}", locale, currency, StringUtil.join(brandCodes, ','));
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), globalVars.getActiveSKUCode()));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));
		if(!brandCodes.isEmpty()) {
			conditions.add(root.in(Brand_.brandCode).in(brandCodes));
		}

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
	}
	
	@Override
	public List<Brand> findAllByCategory(String locale, String currency, String categoryCode) {
		LOGGER.debug("call BrandDaoImpl.findAllByCategory parameters : {}, {}, {}", locale, currency, categoryCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Brand, CategoryBrand> category = root.join(Brand_.categories);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), globalVars.getActiveSKUCode()));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(category.get(CategoryBrand_.categoryCode), categoryCode));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency) {
		LOGGER.debug("call BrandDaoImpl.findAll parameters : {}, {}, {}", locale, currency);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), globalVars.getActiveSKUCode()));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> this.objectToEntity(t, locale, currency)).collect(Collectors.toList());
		
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency, Set<String> categoryCodes, Set<String> tagCodes) {
		LOGGER.debug("call BrandDaoImpl.findAll parameters : {}, {}, {}, {}", locale, currency, StringUtil.join(categoryCodes, ','), StringUtil.join(tagCodes, ','));
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
		
		Root<Brand> root = cq.from(Brand.class);
		
		Join<Brand, Product> product = root.join(Brand_.products);
		Join<Product, ProductStatus> status = product.join(Product_.productStatus);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(categoryCodes.size() > 0) {
			Join<Product, CategoryProduct> category = product.join(Product_.categories);
			conditions.add(category.get(Category_.categoryCode).in(categoryCodes));
		}
		if(tagCodes.size() > 0) {
			Join<Product, Tag> productTag = product.join(Product_.tags);
			conditions.add(productTag.get(Tag_.tagCode).in(tagCodes));
		}
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), globalVars.getActiveSKUCode()));
		
		TypedQuery<Brand> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);

		return query.getResultList();
	}
	
	@Override
	public Brand objectToEntity(Tuple t, String locale, String currency) {
		Brand brandEntity = new Brand();
		BrandAttribute brandAttribute = new BrandAttribute();
		
		brandAttribute.setId(Long.parseLong(t.get("brandAttributeId").toString()));
		brandAttribute.setBrand(brandEntity);
		brandAttribute.setBrandDesc(t.get("brandDesc").toString());
		brandAttribute.setLclCd(locale);
		
		brandEntity.setBrandAttribute(brandAttribute);
		brandEntity.setId(Long.parseLong(t.get("brandId").toString()));
		brandEntity.setBrandCode(t.get("brandCode").toString());
		brandEntity.setLocale(locale);
		brandEntity.setCurrency(currency);
		
		return brandEntity;
	}
	
	@Override
	public void save(Brand t) {
		em.persist(t);
		em.flush();
	}
	
	@Override
	public void update(Brand t, String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Brand t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Brand objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

}