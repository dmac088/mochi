package io.nzbee.entity.brand;

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
public class BrandDaoImpl  implements IBrandDao { 

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<Brand> findById(String locale, String currency, long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.brandAttributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(root.get(Brand_.brandId), id));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brandDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		Tuple tuple = query.getSingleResult();
		
		Brand brandEntity = new Brand();
		BrandAttribute brandAttribute = new BrandAttribute();
		
		brandAttribute.setId(Long.parseLong(tuple.get("brandAttributeId").toString()));
		brandAttribute.setBrandId(Long.parseLong(tuple.get("brandId").toString()));
		brandAttribute.setBrandDesc(tuple.get("brandDesc").toString());
		brandAttribute.setLclCd(locale);
		
		brandEntity.setBrandAttribute(brandAttribute);
		brandEntity.setId(Long.parseLong(tuple.get("brandId").toString()));
		brandEntity.setCode(tuple.get("brandCode").toString());
		
		return Optional.ofNullable(brandEntity);
	}
	
	@Override
	public Optional<Brand> findByCode(String locale, String currency, String code) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.brandAttributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(root.get(Brand_.brandCode), code));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brnadDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		Tuple tuple = query.getSingleResult();
		
		Brand brandEntity = new Brand();
		BrandAttribute brandAttribute = new BrandAttribute();
		
		brandAttribute.setId(Long.parseLong(tuple.get("brandAttributeId").toString()));
		brandAttribute.setBrandId(Long.parseLong(tuple.get("brandId").toString()));
		brandAttribute.setBrandDesc(tuple.get("brnadDesc").toString());
		brandAttribute.setLclCd(locale);
		
		brandEntity.setBrandAttribute(brandAttribute);
		brandEntity.setId(Long.parseLong(tuple.get("brandId").toString()));
		brandEntity.setCode(tuple.get("brandCode").toString());
		
		return Optional.ofNullable(brandEntity);
	}

	
	@Override
	public Optional<Brand> findByDesc(String locale, String currency, String desc) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.brandAttributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.brandDesc), desc));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brnadDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		Tuple tuple = query.getSingleResult();
		
		Brand brandEntity = new Brand();
		BrandAttribute brandAttribute = new BrandAttribute();
		
		brandAttribute.setId(Long.parseLong(tuple.get("brandAttributeId").toString()));
		brandAttribute.setBrandId(Long.parseLong(tuple.get("brandId").toString()));
		brandAttribute.setBrandDesc(tuple.get("brnadDesc").toString());
		brandAttribute.setLclCd(locale);
		
		brandEntity.setBrandAttribute(brandAttribute);
		brandEntity.setId(Long.parseLong(tuple.get("brandId").toString()));
		brandEntity.setCode(tuple.get("brandCode").toString());
		
		return Optional.ofNullable(brandEntity);
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency, List<String> brandCodes) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.brandAttributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(root.in(Brand_.brandCode).in(brandCodes));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brnadDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> {
			Brand brandEntity = new Brand();
			BrandAttribute brandAttribute = new BrandAttribute();
			
			brandAttribute.setId(Long.parseLong(t.get("brandAttributeId").toString()));
			brandAttribute.setBrandId(Long.parseLong(t.get("brandId").toString()));
			brandAttribute.setBrandDesc(t.get("brnadDesc").toString());
			brandAttribute.setLclCd(locale);
			
			brandEntity.setBrandAttribute(brandAttribute);
			brandEntity.setId(Long.parseLong(t.get("brandId").toString()));
			brandEntity.setCode(t.get("brandCode").toString());
			
			return brandEntity;
		}).collect(Collectors.toList());
	}
	
	@Override
	public List<Brand> findAllByCategory(String locale, String currency, String categoryCode) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Brand, CategoryBrand> category = root.join(Brand_.categories);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.brandAttributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(category.get(CategoryBrand_.categoryCode), categoryCode));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brnadDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> {
			Brand brandEntity = new Brand();
			BrandAttribute brandAttribute = new BrandAttribute();
			
			brandAttribute.setId(Long.parseLong(t.get("brandAttributeId").toString()));
			brandAttribute.setBrandId(Long.parseLong(t.get("brandId").toString()));
			brandAttribute.setBrandDesc(t.get("brnadDesc").toString());
			brandAttribute.setLclCd(locale);
			
			brandEntity.setBrandAttribute(brandAttribute);
			brandEntity.setId(Long.parseLong(t.get("brandId").toString()));
			brandEntity.setCode(t.get("brandCode").toString());
			
			return brandEntity;
		}).collect(Collectors.toList());
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Brand> root = cq.from(Brand.class);
		Join<Brand, Product> brand = root.join(Brand_.products);
		Join<Product, ProductStatus> status = brand.join(Product_.productStatus);
		Join<Brand, BrandAttribute> attribute = root.join(Brand_.brandAttributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(attribute.get(BrandAttribute_.lclCd), locale));

		cq.multiselect(	root.get(Brand_.brandId).alias("brandId"),
						root.get(Brand_.brandCode).alias("brandCode"),
						attribute.get(BrandAttribute_.Id).alias("brandAttributeId"),
						attribute.get(BrandAttribute_.brandDesc).alias("brnadDesc")
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> {
			Brand brandEntity = new Brand();
			BrandAttribute brandAttribute = new BrandAttribute();
			
			brandAttribute.setId(Long.parseLong(t.get("brandAttributeId").toString()));
			brandAttribute.setBrandId(Long.parseLong(t.get("brandId").toString()));
			brandAttribute.setBrandDesc(t.get("brnadDesc").toString());
			brandAttribute.setLclCd(locale);
			
			brandEntity.setBrandAttribute(brandAttribute);
			brandEntity.setId(Long.parseLong(t.get("brandId").toString()));
			brandEntity.setCode(t.get("brandCode").toString());
			
			return brandEntity;
		}).collect(Collectors.toList());
		
	}
	
	@Override
	public List<Brand> findAll(String locale, String currency, List<String> categoryCodes, List<String> tagCodes) {
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
			conditions.add(productTag.get(Tag_.productTagCode).in(tagCodes));
		}
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		
		TypedQuery<Brand> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);

		return query.getResultList();
	}
	
	@Override
	public void save(Brand t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(Brand t, String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Brand t) {
		// TODO Auto-generated method stub
		
	}

}