package io.nzbee.entity.product;

import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.attribute.ProductAttribute_;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPrice_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.GeneralVars;
import io.nzbee.variables.ProductVars;

@Component(value = "productEntityDao")
public class ProductDaoImpl implements IProductDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

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
				 .setParameter("orderby", "1")
				 .setParameter("limit", Integer.toString(GeneralVars.DEFAULT_PAGE_SIZE))
				 .setParameter("offset", Integer.toString(GeneralVars.DEFAULT_PAGE * GeneralVars.DEFAULT_PAGE_SIZE));
		
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
				 .setParameter("orderby", "1")
				 .setParameter("limit", Integer.toString(size))
				 .setParameter("offset", Integer.toString(page * size));
		

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
		
		return new PageImpl<Product>(lp, PageRequest.of(page, size), total);
	}
	
	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									int page, 
									int size,
									String categoryDesc,
									List<String> categoryCodes, 
									List<String> brandCodes, 
									List<String> tagCodes, 
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
				 .setParameter("orderby", "1")
				 .setParameter("limit", Integer.toString(size))
				 .setParameter("offset", Integer.toString(page * size));
				

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
				
		return new PageImpl<Product>(lp, PageRequest.of(page, size), total);
	}

	@Override
	public Page<Product> findAll( 
								 String locale,
								 String currency,
								 Double priceStart,
								 Double priceEnd, 
								 int page, 
								 int size,
								 String categoryDesc,
								 List<String> categoryCodes,
								 List<String> brandCodes, 
								 List<String> tagCodes,
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
					.setParameter("orderby", "1")
					.setParameter("limit", Integer.toString(size))
					.setParameter("offset", Integer.toString(page * size));
				

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
				
		return new PageImpl<Product>(lp, PageRequest.of(page, size), total);
	}
	
	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									List<String> productCodes) {
		// TODO Auto-generated method stub
		
		return null;
		
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

	@SuppressWarnings("unused")
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
