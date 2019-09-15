package io.nzbee.dto.product;

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
import javax.persistence.criteria.JoinType;
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
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.price.ProductPriceType_;
import io.nzbee.entity.product.price.ProductPrice_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;
import io.nzbee.variables.ProductVars;

@Component(value="productDomainDao")
public class ProductDaoImpl implements IProductDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public List<io.nzbee.dto.product.Product> findAll(String locale, String currency) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.product.Product> cq = cb.createQuery(io.nzbee.dto.product.Product.class);
		
		//join product to product attribute
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		
		Join<Product, ProductPrice> retailPrice 					= root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> retailType 			= retailPrice.join(ProductPrice_.type);
		retailType.on(cb.equal(retailType.get(ProductPriceType_.code), ProductVars.PRICE_MARKDOWN_CODE));
		
		Join<Product, ProductPrice> markdownPrice 					= root.join(Product_.prices);
		Join<ProductPrice, ProductPriceType> markdownType 			= markdownPrice.join(ProductPrice_.type);
		markdownType.on(cb.equal(markdownType.get(ProductPriceType_.code), ProductVars.PRICE_RETAIL_CODE));
		
		Join<ProductPrice, Currency> markdownCurr 					= markdownPrice.join(ProductPrice_.currency);
		//markdownPrice.on(cb.between(new Date(), markdownPrice.get(ProductPrice_.startDate), markdownPrice.get(ProductPrice_.endDate)));
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		
		// Define DTO projection
		cq.select(cb.construct(
				io.nzbee.dto.product.Product.class,
				root.get(Product_.productUPC),
				root.get(Product_.productCreateDt),
				productAttribute.get(ProductAttribute_.productDesc),
				retailPrice.get(ProductPrice_.priceValue),
				markdownPrice.get(ProductPrice_.priceValue),
				productAttribute.get(ProductAttribute_.ProductImage),
				productAttribute.get(ProductAttribute_.lclCd),
				markdownCurr.get(Currency_.code)
				)
		);
		
		// Define DTO projection
		cq.select(cb.construct( io.nzbee.dto.product.Product.class,
						        root.get(Product_.productUPC),
						        productAttribute.get(ProductAttribute_.productDesc)
				  			   ));
		
		
		TypedQuery<io.nzbee.dto.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
	}
	

	
	@Override
	public Optional<io.nzbee.dto.product.Product> findById(long id, String locale, String currency) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.product.Product> cq = cb.createQuery(io.nzbee.dto.product.Product.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
				
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		conditions.add(cb.equal(root.get(Product_.productId), id));
		
		TypedQuery<io.nzbee.dto.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public List<io.nzbee.dto.product.Product> getAll(String locale, String currency) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.product.Product> cq = cb.createQuery(io.nzbee.dto.product.Product.class);
		
		//join product to product attribute
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale));
		
		// Define DTO projection
		cq.select(cb.construct(
				io.nzbee.dto.product.Product.class,
		        root.get(Product_.productUPC),
		        productAttribute.get(ProductAttribute_.productDesc)));
		
		
		TypedQuery<io.nzbee.dto.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultList();
	}

	@Override
	public void save(io.nzbee.dto.product.Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(io.nzbee.dto.product.Product t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(io.nzbee.dto.product.Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<io.nzbee.dto.product.Product> findByUPC(String upc, String locale, String currency) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.product.Product> cq = cb.createQuery(io.nzbee.dto.product.Product.class);
		
		Root<Product> root = cq.from(Product.class);

		List<Predicate> conditions = new ArrayList<Predicate>();	
		conditions.add(cb.equal(root.get(Product_.productUPC), upc));
		
		TypedQuery<io.nzbee.dto.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
		
	
	
	@Override
	public Page<io.nzbee.dto.product.Product> findAll(List<String> categoryCodes, String locale, Double priceStart,
			Double priceEnd, String priceType, String currency, Date priceDateStart, Date priceDateEnd,
			Pageable pageable, List<String> brandCodes, List<String> tagCodes) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.product.Product> cq = cb.createQuery(io.nzbee.dto.product.Product.class);
		
		Root<Product> root 											= cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute 			= root.join(Product_.attributes);
		Join<Product, CategoryProduct> category 					= root.join(Product_.categories);
		Join<Product, Brand> brand 									= root.join(Product_.brand);
		Join<Product, ProductStatus> status 						= root.join(Product_.productStatus);
		
		Join<Product, ProductPrice> retailPrice 					= root.join(Product_.prices, JoinType.LEFT);
		Join<ProductPrice, ProductPriceType> retailType 			= retailPrice.join(ProductPrice_.type);
		retailType.on(cb.equal(retailType.get(ProductPriceType_.code), ProductVars.PRICE_RETAIL_CODE));
		
		Join<ProductPrice, Currency> retailCurr 					= retailPrice.join(ProductPrice_.currency);
		retailPrice.on(cb.between(retailPrice.get(ProductPrice_.priceValue), priceStart, priceEnd));
		retailPrice.on(cb.between(retailPrice.get(ProductPrice_.startDate), priceDateStart, priceDateEnd));
		retailCurr.on(cb.equal(retailCurr.get(Currency_.code), currency));
		
		Join<Product, ProductPrice> markdownPrice 					= root.join(Product_.prices, JoinType.LEFT);
		Join<ProductPrice, ProductPriceType> markdownType 			= markdownPrice.join(ProductPrice_.type);
		markdownType.on(cb.equal(markdownType.get(ProductPriceType_.code), ProductVars.PRICE_MARKDOWN_CODE));
		
		Join<ProductPrice, Currency> markdownCurr 					= markdownPrice.join(ProductPrice_.currency);
		markdownPrice.on(cb.between(markdownPrice.get(ProductPrice_.priceValue), priceStart, priceEnd));
		markdownPrice.on(cb.between(markdownPrice.get(ProductPrice_.startDate), priceDateStart, priceDateEnd));
		markdownCurr.on(cb.equal(markdownCurr.get(Currency_.code), currency));
		
		///markdownPrice.on(cb.between(markdownPrice.get(ProductPrice_.endDate), priceDateStart, priceDateEnd));
		
		Join<Brand, BrandAttribute> brandAttribute 					= brand.join(Brand_.brandAttributes);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute 	= category.join(CategoryProduct_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy 				= category.join(Category_.hierarchy);
		
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
		conditions.add(cb.equal(retailType.get(ProductPriceType_.desc), priceType));

		if(priceType.equals(ProductVars.RETAIL_SKU_DESCRIPTION)) {
			conditions.add(cb.equal(retailType.get(ProductPriceType_.desc), priceType));
			conditions.add(cb.between(retailPrice.get(ProductPrice_.priceValue), priceStart, priceEnd));
		}
		if(priceType.equals(ProductVars.MARKDOWN_SKU_DESCRIPTION)) {
			conditions.add(cb.equal(markdownType.get(ProductPriceType_.desc), priceType));
			conditions.add(cb.between(markdownPrice.get(ProductPrice_.priceValue), priceStart, priceEnd));
		}
		
		Order order = pageable.getSort().stream().map(o -> {
			return this.getOrder(o.getProperty().replaceAll(".*\\.", ""), o.getDirection(), cb, productAttribute, retailPrice);
		}).collect(Collectors.toList()).get(0);
		
		/* Define DTO projection */
		//We need to define a constructor in our Product DTO (domain object)
		
		cq.select(cb.construct(
					io.nzbee.dto.product.Product.class,
					root.get(Product_.productUPC),
					root.get(Product_.productCreateDt),
					productAttribute.get(ProductAttribute_.productDesc),
					retailPrice.get(ProductPrice_.priceValue),
					markdownPrice.get(ProductPrice_.priceValue),
					productAttribute.get(ProductAttribute_.ProductImage),
					productAttribute.get(ProductAttribute_.lclCd),
					markdownCurr.get(Currency_.code)
					)
		);
		
		TypedQuery<io.nzbee.dto.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
				.orderBy(order)
		);

		PageableUtil pageableUtil = new PageableUtil();
		query.setFirstResult(pageableUtil.getStartPosition(pageable));
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<io.nzbee.dto.product.Product>(query.getResultList(), pageable, query.getResultList().size());
	}
	
	@Override
	public Page<io.nzbee.dto.product.Product> findAll(List<String> categoryCodes, String locale, 
			String priceType, String currency, Date priceDateStart, Date priceDateEnd,
			Pageable pageable, List<String> brandCodes, List<String> tagCodes) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<io.nzbee.dto.product.Product> cq = cb.createQuery(io.nzbee.dto.product.Product.class);
		
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
		
		TypedQuery<io.nzbee.dto.product.Product> query = em.createQuery(cq
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
				.orderBy(order)
		);

		PageableUtil pageableUtil = new PageableUtil();
		query.setFirstResult(pageableUtil.getStartPosition(pageable));
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<io.nzbee.dto.product.Product>(query.getResultList(), pageable, query.getResultList().size());
	}
	
	@Override
	public List<io.nzbee.dto.product.Product> getAll(String locale, String currency, List<String> productCodes) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
	
		CriteriaQuery<io.nzbee.dto.product.Product> cq = cb.createQuery(io.nzbee.dto.product.Product.class);
		
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
		
		TypedQuery<io.nzbee.dto.product.Product> query = em.createQuery(cq
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
