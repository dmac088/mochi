package io.nzbee.entity.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.Brand_; 
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.brand.attribute.BrandAttribute_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.CategoryProduct_;
import io.nzbee.entity.PageableUtil;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.attribute.ProductAttribute_;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.Currency_;
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

@Component(value = "productEntityDao")
public class ProductDaoImpl implements IProductDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	
	@Override
	public List<Product> findAll(String locale, String currency) {
		
		List<String> categories = new ArrayList<String>();
		categories.add(CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE);
		
		
		Query query = em.createNamedQuery("getProducts")
				 .setParameter("categoryCodes", categories)
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
				 
				 //these should contain default values for these parameters
				 .setParameter("orderby", "test")
				 .setParameter("limit", "10")
				 .setParameter("offset", "0");
		
				//we need to put out own pagination filters here
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		return results.stream().map(p -> {
			Product product = (Product) p[0];
			product.setProductAttribute((ProductAttribute) p[1]); 
			product.setBrand((Brand) p[2]);
			product.getBrand().setBrandAttribute((BrandAttribute) p[3]);
			
			return product;
		}).collect(Collectors.toList());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									int page, 
									int size, 
									String orderby) {
		// TODO Auto-generated method stub
		
		//first get the result count
		Query query = em.createNamedQuery("Product.getProducts.count")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		
		Object result = query.getSingleResult();
		long total = ((long) result);
		
		query = em.createNamedQuery("Product.getProducts")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
				 
				 //these should contain default values for these parameters
				 .setParameter("orderby", orderby)
				 .setParameter("limit", Integer.toString(size))
				 .setParameter("offset", Integer.toString(0));
		

		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		List<Product> lp = 
		results.stream().map(p -> {
			Product product = (Product) p[0];
			product.setProductAttribute((ProductAttribute) p[1]); 
			product.setBrand((Brand) p[2]);
			product.getBrand().setBrandAttribute((BrandAttribute) p[3]);
			
			return product;
		}).collect(Collectors.toList());
		
		return new PageImpl(lp, PageRequest.of(page, size), total);
	}
	

	@Override
	public Page<Product> findAll(List<String> categoryCodes, 
								 String locale, 
								 Double priceStart,
								 Double priceEnd, 
								 String priceType, 
								 String currency, 
								 Date priceDateStart, 
								 Date priceDateEnd,
								 int page, 
								 int size,  
								 List<String> brandCodes, 
								 List<String> tagCodes) {
		
		// TODO Auto-generated method stub
		
		
		
		return null;

	}
	
	@Override
	public Page<Product> findAll(List<String> categoryCodes, String locale, 
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
		
		return new PageImpl<Product>(query.getResultList(), pageable, query.getResultList().size());
	}
	
	
	@Override
	public Optional<Product> findById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root = cq.from(Product.class);

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(root.get(Product_.productId), id));
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	
	@Override
	public Optional<Product> findByCode(String code) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(root.get(Product_.productUPC), code));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	

	@Override
	public Optional<Product> findByDesc(String locale, String desc) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		Root<Product> root = cq.from(Product.class);
		
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE));
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.productDesc), desc));
		
		TypedQuery<Product> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);

		return Optional.ofNullable(query.getSingleResult());
	}
	
	@Override
	public Page<Product> findAll(String locale, String currency, List<String> productCodes) {
		
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
	public List<Product> getAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return this.findAll(locale, currency);
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




}
