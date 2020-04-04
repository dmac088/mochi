package io.nzbee.entity.product.department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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
import io.nzbee.variables.ProductVars;

@Component
public class DepartmentDaoImpl  implements IDepartmentDao { 

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<Department> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Department> findById(String locale, String currency, long id) {
		
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
		
		Tuple tuple = query.getSingleResult();
		
		Brand brandEntity = new Brand();
		BrandAttribute brandAttribute = new BrandAttribute();
		
		brandAttribute.setId(Long.parseLong(tuple.get("brandAttributeId").toString()));
		brandAttribute.setBrand(brandEntity);
		brandAttribute.setBrandDesc(tuple.get("brandDesc").toString());
		brandAttribute.setLclCd(locale);
		
		brandEntity.setBrandAttribute(brandAttribute);
		brandEntity.setId(Long.parseLong(tuple.get("brandId").toString()));
		brandEntity.setBrandCode(tuple.get("brandCode").toString());
		brandEntity.setLocale(locale);
		brandEntity.setCurrency(currency);

		return Optional.ofNullable(brandEntity);
	}
	
	@Override
	public Optional<Department> findByCode(String locale, String currency, String code) {
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
		
		Tuple tuple = query.getSingleResult();
		
		Brand brandEntity = new Brand();
		BrandAttribute brandAttribute = new BrandAttribute();
		
		brandAttribute.setId(Long.parseLong(tuple.get("brandAttributeId").toString()));
		brandAttribute.setBrand(brandEntity);
		brandAttribute.setBrandDesc(tuple.get("brandDesc").toString());
		brandAttribute.setLclCd(locale);
		
		brandEntity.setBrandAttribute(brandAttribute);
		brandEntity.setId(Long.parseLong(tuple.get("brandId").toString()));
		brandEntity.setBrandCode(tuple.get("brandCode").toString());
		brandEntity.setLocale(locale);
		brandEntity.setCurrency(currency);
		
		return Optional.ofNullable(brandEntity);
	}

	
	@Override
	public Optional<Department> findByDesc(String locale, String currency, String desc) {
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
		
		Tuple tuple = query.getSingleResult();
		
		Brand brandEntity = new Brand();
		BrandAttribute brandAttribute = new BrandAttribute();
		
		brandAttribute.setId(Long.parseLong(tuple.get("brandAttributeId").toString()));
		brandAttribute.setBrand(brandEntity);
		brandAttribute.setBrandDesc(tuple.get("brandDesc").toString());
		brandAttribute.setLclCd(locale);
		
		brandEntity.setBrandAttribute(brandAttribute);
		brandEntity.setId(Long.parseLong(tuple.get("brandId").toString()));
		brandEntity.setBrandCode(tuple.get("brandCode").toString());
		brandEntity.setLocale(locale);
		brandEntity.setCurrency(currency);
		
		return Optional.ofNullable(brandEntity);
	}
	

	
	@Override
	public void save(Department t) {
		em.persist(t);
		em.flush();
	}
	
	@Override
	public void update(Department t, String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Department t) {
		// TODO Auto-generated method stub
		
	}

}