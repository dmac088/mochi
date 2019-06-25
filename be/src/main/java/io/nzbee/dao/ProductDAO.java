package io.nzbee.dao;

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
import io.nzbee.entity.Brand;
import io.nzbee.entity.BrandAttribute;
import io.nzbee.entity.BrandAttribute_;
import io.nzbee.entity.Brand_;
import io.nzbee.entity.Category;
import io.nzbee.entity.CategoryAttribute;
import io.nzbee.entity.CategoryAttribute_;
import io.nzbee.entity.Category_;
import io.nzbee.entity.Currency;
import io.nzbee.entity.Currency_;
import io.nzbee.entity.Hierarchy;
import io.nzbee.entity.Hierarchy_;
import io.nzbee.entity.PageableUtil;
import io.nzbee.entity.Product;
import io.nzbee.entity.ProductAttribute;
import io.nzbee.entity.ProductAttribute_;
import io.nzbee.entity.ProductPrice;
import io.nzbee.entity.ProductPriceType;
import io.nzbee.entity.ProductPriceType_;
import io.nzbee.entity.ProductPrice_;
import io.nzbee.entity.ProductStatus;
import io.nzbee.entity.ProductStatus_;
import io.nzbee.entity.Product_;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

@Component
public class ProductDAO implements Dao<Product> {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<Product> get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Long getResultCount(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, Category> category = root.join(Product_.categories);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<Category, CategoryAttribute> categoryAttribute = category.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(Category_.categoryId).in(categoryIds));
		}
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
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
	
	
	public Page<Product> getAll(List<Long> categoryIds, String locale, Double priceStart, Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd, Pageable pageable, List<Long> brandIds) {
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, Category> category = root.join(Product_.categories);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<Category, CategoryAttribute> categoryAttribute = category.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(Category_.categoryId).in(categoryIds));
		}
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
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
		
		Long resultCount = this.getResultCount(categoryIds, locale, priceStart, priceEnd, priceType, currency, priceDateStart, priceDateEnd, pageable, brandIds);
	
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
	
	
	private Order getOrder(String orderName, Sort.Direction orderDirection, CriteriaBuilder cb, Join attributeJoin, Join priceJoin) {

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
	
	public Double getMaxPrice(String categoryDesc, String locale, String priceType, String currency, List<Long> categoryIds, List<Long> brandIds) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		
		Root<Product> root = cq.from(Product.class);
		
		Join<Product, Category> category = root.join(Product_.categories);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, Brand> brand = root.join(Product_.brand);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, ProductPrice> price = root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> type = price.join(ProductPrice_.type);
		Join<ProductPrice, Currency> curr = price.join(ProductPrice_.currency);
		Join<Brand, BrandAttribute> brandAttribute = brand.join(Brand_.brandAttributes);
		Join<Category, CategoryAttribute> categoryAttribute = category.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = category.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!categoryIds.isEmpty()) {
			conditions.add(category.get(Category_.categoryId).in(categoryIds));
		}
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
		conditions.add(cb.equal(brandAttribute.get(BrandAttribute_.lclCd), locale));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		conditions.add(cb.equal(type.get(ProductPriceType_.desc), priceType));
		conditions.add(cb.equal(curr.get(Currency_.code), currency));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), CategoryVars.PRIMARY_HIERARCHY_CODE));
		
		TypedQuery<Double> query = em.createQuery(cq
				.select(cb.max(price.<Double>get(ProductPrice_.priceValue)))
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false));
		 
		return query.getSingleResult();
    }

}
