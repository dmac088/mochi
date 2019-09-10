package io.nzbee.domain.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.Brand_; 
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.brand.attribute.BrandAttribute_;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.product.readonly.CategoryProduct_;
import io.nzbee.entity.category.product.readonly.CategoryProduct;
import io.nzbee.entity.PageableUtil;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.attribute.ProductAttribute_;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.Currency_;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.entity.product.hierarchy.Hierarchy_;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.price.ProductPriceType_;
import io.nzbee.entity.product.price.ProductPrice_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

@Component
public class ProductDaoImpl implements IProductDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public List<io.nzbee.domain.product.Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<io.nzbee.domain.product.Product> findAll(String locale, String currency) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.domain.product.Product> cq = cb.createQuery(io.nzbee.domain.product.Product.class);
		
		//join product to product attribute
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		
		// Define DTO projection
		cq.select(cb.construct(
				io.nzbee.domain.product.Product.class,
		        root.get(Product_.productUPC),
		        productAttribute.get(ProductAttribute_.productDesc)));
		
		
		TypedQuery<io.nzbee.domain.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
	}
	

	
	@Override
	public Optional<io.nzbee.domain.product.Product> findById(long id, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.domain.product.Product> cq = cb.createQuery(io.nzbee.domain.product.Product.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
				
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(root.get(Product_.productId), id));
		
		TypedQuery<io.nzbee.domain.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public List<io.nzbee.domain.product.Product> getAll(String locale, String currency) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.domain.product.Product> cq = cb.createQuery(io.nzbee.domain.product.Product.class);
		
		//join product to product attribute
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		
		// Define DTO projection
		cq.select(cb.construct(
				io.nzbee.domain.product.Product.class,
		        root.get(Product_.productUPC),
		        productAttribute.get(ProductAttribute_.productDesc)));
		
		
		TypedQuery<io.nzbee.domain.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
	}

	@Override
	public void save(io.nzbee.domain.product.Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(io.nzbee.domain.product.Product t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(io.nzbee.domain.product.Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<io.nzbee.domain.product.Product> findByUPC(String upc, String locale, String currency) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root = cq.from(Product.class);

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(root.get(Product_.productUPC), upc));
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
		
	@Override
	public Page<io.nzbee.domain.product.Product> findAllActiveSKU(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds, List<Long> tagIds) {
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root 									= cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute 	= root.join(Product_.attributes);
		Join<Product, CategoryProduct> category 			= root.join(Product_.categories);
		Join<Product, Brand> brand 							= root.join(Product_.brand);
		Join<Product, ProductStatus> status 				= root.join(Product_.productStatus);
		Join<Product, ProductPrice> price 					= root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type 			= price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr 					= price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute 			= brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(CategoryProduct_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(CategoryProduct_.categoryId).in(categoryIds));
		}
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
		if(!tagIds.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagId).in(tagIds));
		}
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.priceValue), priceStart));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.priceValue), priceEnd));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));
		
		Long resultCount = this.getResultCountById(categoryIds, locale, priceStart, priceEnd, priceType, currency, priceDateStart, priceDateEnd, pageable, brandIds, tagIds);
	
		Order order = pageable.getSort().stream().map(o -> {
			return this.getOrder(o.getProperty().replaceAll(".*\\.", ""), o.getDirection(), cb, productAttribute, price);
		}).collect(Collectors.toList()).get(0);
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
				.orderBy(order)
		);

		PageableUtil pageableUtil = new PageableUtil();
		query.setFirstResult(pageableUtil.getStartPosition(pageable));
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<Product>(query.getResultList(), pageable, resultCount);
    }
	
	
	@Override
	public Page<io.nzbee.domain.product.Product> findAllActiveSKUByPrimaryHierarchyById(List<Long> categoryIds, String locale, Double priceStart,
			Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd,
			Pageable pageable, List<Long> brandIds, List<Long> tagIds) {
		
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root 									= cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute 	= root.join(Product_.attributes);
		Join<Product, CategoryProduct> category 					= root.join(Product_.categories);
		Join<Product, Brand> brand 							= root.join(Product_.brand);
		Join<Product, ProductStatus> status 				= root.join(Product_.productStatus);
		Join<Product, ProductPrice> price 					= root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type 			= price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr 					= price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute 			= brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(CategoryProduct_.attributes);
		Join<CategoryProduct, Hierarchy> categoryHierarchy 		= category.join(CategoryProduct_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(CategoryProduct_.categoryId).in(categoryIds));
		}
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
		if(!tagIds.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagId).in(tagIds));
		}
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		if(priceStart != -1 && priceEnd != -1) {
			conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.priceValue), priceStart));
			conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.priceValue), priceEnd));
		}
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), CategoryVars.PRIMARY_HIERARCHY_CODE));		
		Long resultCount = this.getResultCountById(categoryIds, locale, priceStart, priceEnd, priceType, currency, priceDateStart, priceDateEnd, pageable, brandIds, tagIds);
	
		Order order = pageable.getSort().stream().map(o -> {
			return this.getOrder(o.getProperty().replaceAll(".*\\.", ""), o.getDirection(), cb, productAttribute, price);
		}).collect(Collectors.toList()).get(0);
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
				.orderBy(order)
		);

		PageableUtil pageableUtil = new PageableUtil();
		query.setFirstResult(pageableUtil.getStartPosition(pageable));
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<Product>(query.getResultList(), pageable, resultCount);
	}
	
	@Override
	public Page<io.nzbee.domain.product.Product> findAllActiveSKUByCode(List<String> categoryCodes, String locale, Double priceStart,
			Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd,
			Pageable pageable, List<String> brandCodes, List<String> tagCodes) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root 									= cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute 	= root.join(Product_.attributes);
		Join<Product, CategoryProduct> category 					= root.join(Product_.categories);
		Join<Product, Brand> brand 							= root.join(Product_.brand);
		Join<Product, ProductStatus> status 				= root.join(Product_.productStatus);
		Join<Product, ProductPrice> price 					= root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type 			= price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr 					= price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute 			= brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(CategoryProduct_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy 		= category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryCodes.isEmpty()) {
			conditions.add(category.get(CategoryProduct_.categoryCode).in(categoryCodes));
		}
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!tagCodes.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagCode).in(tagCodes));
		}
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		if(priceStart != -1 && priceEnd != -1) {
			conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.priceValue), priceStart));
			conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.priceValue), priceEnd));
		}
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), CategoryVars.PRIMARY_HIERARCHY_CODE));		
		Long resultCount = this.getResultCountByCode(categoryCodes, locale, priceStart, priceEnd, priceType, currency, priceDateStart, priceDateEnd, pageable, brandCodes, tagCodes);
	
		Order order = pageable.getSort().stream().map(o -> {
			return this.getOrder(o.getProperty().replaceAll(".*\\.", ""), o.getDirection(), cb, productAttribute, price);
		}).collect(Collectors.toList()).get(0);
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
				.orderBy(order)
		);

		PageableUtil pageableUtil = new PageableUtil();
		query.setFirstResult(pageableUtil.getStartPosition(pageable));
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<Product>(query.getResultList(), pageable, resultCount);
	}
	
	@Override
	public Page<io.nzbee.domain.product.Product> findAllActiveSKUByCode(List<String> categoryCodes, String locale, 
			String priceType, String currency, Date priceDateStart, Date priceDateEnd,
			Pageable pageable, List<String> brandCodes, List<String> tagCodes) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root 									= cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute 	= root.join(Product_.attributes);
		Join<Product, CategoryProduct> category 					= root.join(Product_.categories);
		Join<Product, Brand> brand 							= root.join(Product_.brand);
		Join<Product, ProductStatus> status 				= root.join(Product_.productStatus);
		Join<Product, ProductPrice> price 					= root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type 			= price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr 					= price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute 			= brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(CategoryProduct_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy 		= category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryCodes.isEmpty()) {
			conditions.add(category.get(CategoryProduct_.categoryCode).in(categoryCodes));
		}
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!tagCodes.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagCode).in(tagCodes));
		}
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), CategoryVars.PRIMARY_HIERARCHY_CODE));		
		Long resultCount = this.getResultCountByCode(categoryCodes, locale, priceType, currency, priceDateStart, priceDateEnd, pageable, brandCodes, tagCodes);
	
		Order order = pageable.getSort().stream().map(o -> {
			return this.getOrder(o.getProperty().replaceAll(".*\\.", ""), o.getDirection(), cb, productAttribute, price);
		}).collect(Collectors.toList()).get(0);
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
				.orderBy(order)
		);

		PageableUtil pageableUtil = new PageableUtil();
		query.setFirstResult(pageableUtil.getStartPosition(pageable));
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<Product>(query.getResultList(), pageable, resultCount);
	}
	
	@Override
	public List<io.nzbee.domain.product.Product> getAll(String locale, String currency, List<String> productCodes) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root = cq.from(Product.class);
		
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, CategoryProduct> category = root.join(Product_.categories);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(CategoryProduct_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		if(!productCodes.isEmpty()) {
			conditions.add(root.get(Product_.productUPC).in(productCodes));
		}
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);

		return query.getResultList();
    }
	
	@Override
	public Double getMaxPriceById(String categoryDesc, String locale, String priceType, String currency, List<Long> categoryIds, List<Long> brandIds, List<Long> tagIds) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, CategoryProduct> category = root.join(Product_.categories);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(CategoryProduct_.categoryId).in(categoryIds));
		}
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
		if(!tagIds.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagId).in(tagIds));
		}
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		
		TypedQuery<Double> query = em.createQuery(cq
				.select(cb.max(price.<Double>get(ProductPrice_.priceValue)))
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false));
		 
		return query.getSingleResult();
    }
	
	@Override
	public Double getMaxPriceByCode(String categoryDesc, String locale, String priceType, String currency, List<String> categoryCodes, List<String> brandCodes, List<String> tagCodes) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, CategoryProduct> category = root.join(Product_.categories);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryCodes.isEmpty()) {
			conditions.add(category.get(Category_.categoryCode).in(categoryCodes));
		}
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!tagCodes.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagCode).in(tagCodes));
		}
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		
		TypedQuery<Double> query = em.createQuery(cq
				.select(cb.max(price.<Double>get(ProductPrice_.priceValue)))
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false));
		 
		return query.getSingleResult();
    }
	
	private Long getResultCountById(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds, List<Long> tagIds) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, CategoryProduct> category = root.join(Product_.categories);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(Category_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(CategoryProduct_.categoryId).in(categoryIds));
		}
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
		if(!tagIds.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagId).in(tagIds));
		}
		
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.priceValue), priceStart));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.priceValue), priceEnd));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));
		
		TypedQuery<Long> query = em.createQuery(cq
				.select(cb.count(root.<Long>get(Product_.productId)))
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false));
		 
		return query.getSingleResult();
	}
	
	private Long getResultCountByCode(List<String> categoryCodes, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<String> brandCodes, List<String> tagCodes) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, CategoryProduct> category = root.join(Product_.categories);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(CategoryProduct_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryCodes.isEmpty()) {
			conditions.add(category.get(CategoryProduct_.categoryCode).in(categoryCodes));
		}
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!tagCodes.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagCode).in(tagCodes));
		}
		
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.priceValue), priceStart));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.priceValue), priceEnd));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));
		
		TypedQuery<Long> query = em.createQuery(cq
				.select(cb.count(root.<Long>get(Product_.productId)))
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false));
		 
		return query.getSingleResult();
	}
	
	private Long getResultCountByCode(List<String> categoryCodes, String locale, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<String> brandCodes, List<String> tagCodes) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, CategoryProduct> category = root.join(Product_.categories);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = category.join(CategoryProduct_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryCodes.isEmpty()) {
			conditions.add(category.get(CategoryProduct_.categoryCode).in(categoryCodes));
		}
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!tagCodes.isEmpty()) {
			Join<Product, ProductTag> tag = root.join(Product_.tags);
			conditions.add(tag.get(ProductTag_.productTagCode).in(tagCodes));
		}
		
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.lessThanOrEqualTo(price.get(ProductPrice_.startDate), priceDateStart));
		conditions.add(cb.greaterThanOrEqualTo(price.get(ProductPrice_.endDate), priceDateEnd));
		
		TypedQuery<Long> query = em.createQuery(cq
				.select(cb.count(root.<Long>get(Product_.productId)))
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false));
		 
		return query.getSingleResult();
	}
	
	private Order getOrder(String orderName, Sort.Direction orderDirection, CriteriaBuilder cb, Join<Product, ProductAttribute> attributeJoin, Join<Product, ProductPrice> priceJoin) {

		if(orderName.toLowerCase().equals(ProductAttribute_.productDesc.getName().toLowerCase()) && orderDirection.equals(Sort.Direction.ASC)) {
			return cb.asc(cb.lower(attributeJoin.get(ProductAttribute_.productDesc.getName())));
		} else if (orderName.toLowerCase().equals(ProductAttribute_.productDesc.getName().toLowerCase()) && orderDirection.equals(Sort.Direction.DESC)) {
		    return cb.desc(cb.lower(attributeJoin.get(ProductAttribute_.productDesc.getName())));
		} else if (orderName.toLowerCase().equals(ProductPrice_.priceValue.getName().toLowerCase()) && orderDirection.equals(Sort.Direction.ASC)) {
			return cb.asc(priceJoin.get(ProductPrice_.priceValue.getName()));
		} else if (orderName.toLowerCase().equals(ProductPrice_.priceValue.getName().toLowerCase()) && orderDirection.equals(Sort.Direction.DESC)) {
			return cb.desc(priceJoin.get(ProductPrice_.priceValue.getName())); 
		}
		
		return cb.asc(cb.lower(attributeJoin.get(ProductAttribute_.productDesc.getName())));
	}

	@Override
	public Optional<io.nzbee.domain.product.Product> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


}
