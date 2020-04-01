package io.nzbee.entity.product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.attribute.ProductAttribute_;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.Currency_;
import io.nzbee.entity.product.department.Department;
import io.nzbee.entity.product.department.Department_;
import io.nzbee.entity.product.food.Food;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;
import io.nzbee.entity.product.price.ProductPriceType_;
import io.nzbee.entity.product.price.ProductPrice_;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.product.status.ProductStatus_;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.GeneralVars;
import io.nzbee.variables.ProductVars;

@Component(value = "productEntityDao")
public class ProductDaoPostgresImpl implements IProductDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<Product> findById(String locale, String currency, long id) {
		LOGGER.debug("Fetching a product for parameters : {}, {}, {}", locale, currency, id);
		
		final List<String> productCodes = new ArrayList<String>();
		
		Query query = em.createNativeQuery(this.constructSQL(false,
															 false,
															 true,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false), "ProductMapping")
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("categoryCode", CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE)
		.setParameter("categoryId", id)
		.setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		.setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
		.setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		
		if(!productCodes.isEmpty()) {
			query.setParameter("productCodes", productCodes);
		}

		Object[] p = (Object[])query.getSingleResult();
		
		Product product = (Product) p[0];
		product.setProductStatus((ProductStatus) p[1]);
		product.setDepartment((Department) p[5]);
		product.setProductAttribute((ProductAttribute) p[1]); 
		
		Brand brand = (Brand) p[3];
		product.setBrand(brand);
		brand.setBrandAttribute((BrandAttribute) p[4]);
		
		product.setRetailPrice(((BigDecimal) p[6]).doubleValue());
		product.setMarkdownPrice(((BigDecimal) p[7]).doubleValue());
		
		return Optional.ofNullable(product);
	}
	
	@Override
	public Optional<Product> findByCode(String locale, String currency, String code) {
		LOGGER.debug("Fetching a product for parameters : {}, {}, {}", locale, currency, code);
		
		final List<String> productCodes = new ArrayList<String>();
		productCodes.add(code);
		
		Query query = em.createNativeQuery(this.constructSQL(true,
															 false,
															 false,
															 false, 
															 false,
															 false,
															 false,
															 false,
															 false), "ProductMapping")
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("categoryCode", CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE)
		.setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		.setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
		.setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		
		if(!productCodes.isEmpty()) {
			query.setParameter("productCodes", productCodes);
		}

		Object[] p = (Object[])query.getSingleResult();
		
		Product product = (Product) p[0];
		product.setProductStatus((ProductStatus) p[1]);
		product.setDepartment((Department) p[5]);
		product.setProductAttribute((ProductAttribute) p[1]); 
		
		Brand brand = (Brand) p[3];
		product.setBrand(brand);
		brand.setBrandAttribute((BrandAttribute) p[4]);
		
		product.setRetailPrice(((BigDecimal) p[6]).doubleValue());
		product.setMarkdownPrice(((BigDecimal) p[7]).doubleValue());
		
		return Optional.ofNullable(product);
	}
	
	@Override
	public Optional<Product> findByDesc(String locale, String currency, String desc) {
		LOGGER.debug("Fetching a product for parameters : {}, {}, {}", locale, currency, desc);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, ProductAttribute> productAttribute = root.join(Product_.attributes);
		Join<Product, ProductStatus> status = root.join(Product_.productStatus);
		Join<Product, Department> department = root.join(Product_.department);
		Join<Product, ProductPrice> retailPrice = root.join(Product_.prices, JoinType.LEFT);
		Join<ProductPrice, ProductPriceType> retailPriceType = retailPrice.join(ProductPrice_.type);
		Join<ProductPrice, Currency> retailCurrency = retailPrice.join(ProductPrice_.currency);
		
		Join<Product, ProductPrice> markdownPrice = root.join(Product_.prices, JoinType.LEFT);
		Join<ProductPrice, ProductPriceType> markdownPriceType = markdownPrice.join(ProductPrice_.type);
		Join<ProductPrice, Currency> markdownCurrency = markdownPrice.join(ProductPrice_.currency);
		
		retailPriceType.on(cb.equal(retailPriceType.get(ProductPriceType_.code), ProductVars.PRICE_RETAIL_CODE));
		markdownPriceType.on(cb.equal(markdownPriceType.get(ProductPriceType_.code), ProductVars.PRICE_MARKDOWN_CODE));
		
		cq.multiselect(	root.get(Product_.productId).alias("productId"),
				root.get(Product_.productUPC).alias("productCode"),
				productAttribute.get(ProductAttribute_.Id).alias("productAttributeId"),
				productAttribute.get(ProductAttribute_.productDesc).alias("productDesc"),
				retailPrice.get(ProductPrice_.priceValue).alias("retailPrice"),
				markdownPrice.get(ProductPrice_.priceValue).alias("markdownPrice"));
		
		cq.where(cb.and(
				cb.equal(productAttribute.get(ProductAttribute_.lclCd), locale),
				cb.equal(productAttribute.get(ProductAttribute_.productDesc), desc),
				cb.equal(status.get(ProductStatus_.productStatusCode), ProductVars.ACTIVE_SKU_CODE),
				cb.equal(department.get(Department_.departmentCode), "FOO01"),
				cb.equal(retailCurrency.get(Currency_.code), currency),
				cb.equal(markdownCurrency.get(Currency_.code), currency)
		));
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		Tuple tuple = query.getSingleResult();
		
		Product pe = new Food();
		ProductAttribute pa = new ProductAttribute();
		
		pa.setId(Long.parseLong(tuple.get("productAttributeId").toString()));
		//pa.setProductId(Long.parseLong(tuple.get("productId").toString()));
		pa.setProductDesc(tuple.get("productDesc").toString());
		pa.setLclCd(locale);
		
		pe.setProductAttribute(pa);
		pe.setProductId(Long.parseLong(tuple.get("productId").toString()));
		pe.setUPC(tuple.get("productCode").toString());
		pe.setRetailPrice(Double.parseDouble(tuple.get("retailPrice").toString()));
		pe.setMarkdownPrice(Double.parseDouble(tuple.get("markdownPrice").toString()));
		
		return Optional.ofNullable(pe);
	}
	
	@Override
	public List<Product> findAll(String locale, String currency) {
		LOGGER.debug("Fetching products for parameters : {}, {}}", locale, currency);
		
		List<String> categories = new ArrayList<String>();
		categories.add(CategoryVars.PRIMARY_HIERARCHY_ROOT_CODE);
		
		
		Query query = em.createNativeQuery(this.constructSQL(false,
															 false,
															 false,
															 false, 
															 false,
															 false,
															 false,
															 false,
															 true), "ProductMapping")
				 .setParameter("categoryCodes", categories)
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
				 
				 //these should contain default values for these parameters
				 //.setParameter("orderby", "1")
				 .setParameter("limit", Integer.toString(GeneralVars.DEFAULT_PAGE_SIZE))
				 .setParameter("offset", Integer.toString(GeneralVars.DEFAULT_PAGE * GeneralVars.DEFAULT_PAGE_SIZE));
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		return results.stream().map(p -> {
			
			Product product = (Product) p[0];
			product.setProductStatus((ProductStatus) p[1]);
			product.setDepartment((Department) p[5]);
			product.setProductAttribute((ProductAttribute) p[1]); 
			
			Brand brand = (Brand) p[3];
			product.setBrand(brand);
			brand.setBrandAttribute((BrandAttribute) p[4]);
			
			product.setRetailPrice(((BigDecimal) p[6]).doubleValue());
			product.setMarkdownPrice(((BigDecimal) p[7]).doubleValue());
			
			return product;
		}).collect(Collectors.toList());
	}
	

	@Override
	public List<Product> findAll(	String locale, 
									String currency, 
									List<String> codes) {
		LOGGER.debug("Fetching a products for parameters : {}, {}, {}", locale, currency, codes);
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									Pageable pageable, 
									String orderby) {
		LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}", locale, currency, pageable, orderby);
		
		//first get the result count
		Query query = em.createNativeQuery(this.constructSQL(false,
															 false,
															 false,
															 false, 
															 false, 
															 false,
															 false,
															 true,
															 false), "ProductMapping.count")
				 .setParameter("locale", 			locale)
				 .setParameter("currency", 			currency)
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", 	ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		
		Object result = query.getSingleResult();
		long total = ((BigInteger) result).longValue();
		
		query = em.createNativeQuery(this.constructSQL(
													  false,
													  false,
													  false,
				   									  false,
													  false, 
													  false,
													  false,
													  false,
													  true), "ProductMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", 	ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
				 
				 //these should contain default values for these parameters
				 //.setParameter("orderby", "1")
				 .setParameter("limit", pageable.getPageSize())
				 .setParameter("offset", pageable.getOffset());
		

		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		List<Product> lp = 
		results.stream().map(p -> {
			Product product = (Product) p[0];
			product.setProductStatus((ProductStatus) p[1]);
			product.setDepartment((Department) p[5]);
			product.setProductAttribute((ProductAttribute) p[1]); 
			
			Brand brand = (Brand) p[3];
			product.setBrand(brand);
			brand.setBrandAttribute((BrandAttribute) p[4]);
			
			product.setRetailPrice(((BigDecimal) p[6]).doubleValue());
			product.setMarkdownPrice(((BigDecimal) p[7]).doubleValue());
			
			return product;
		}).collect(Collectors.toList());
		
		return new PageImpl<Product>(lp, pageable, total);
	}
	
	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									Pageable pageable,
									String categoryCode,
									List<String> categoryCodes, 
									List<String> brandCodes, 
									List<String> tagCodes, 
									String orderby) {
		
		LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}, {}, {}", locale, currency, pageable, categoryCode, brandCodes, tagCodes, orderby);
		
		//first get the result count
		return this.findAll(locale, 
							currency, 
							new Double(-1), 
							new Double(-1), 
							pageable, 
							categoryCode, 
							categoryCodes, 
							brandCodes, 
							tagCodes, 
							orderby);
	}

	@Override
	public Page<Product> findAll(String locale,
								 String currency,
								 Double priceStart,
								 Double priceEnd, 
								 Pageable pageable,
								 String categoryCode,
								 List<String> categoryCodes,
								 List<String> brandCodes, 
								 List<String> tagCodes,
								 String orderby) {
		
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(this.constructSQL(false,
	 														 false,
															 false,
															 categoryCodes.size()>=1, 
															 brandCodes.size()>=1,
  				 											 tagCodes.size()>=1,
  				 											 (!(priceStart.equals(new Double(-1)) && (priceEnd.equals(new Double(-1))))),
  				 											 true,
  				 											 false), "ProductMapping.count")
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		.setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
		.setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
		.setParameter("categoryCode", categoryCode);
		
		if(!brandCodes.isEmpty()) {
			query.setParameter("brandCodes", brandCodes);
		}
		
		Object result = query.getSingleResult();
		long total = ((BigInteger) result).longValue();
		
		boolean hasPrices = (!(priceStart.equals(new Double(-1)) && (priceEnd.equals(new Double(-1)))));
		
		query = em.createNativeQuery(this.constructSQL(	false,
														false,
														false,
														categoryCodes.size()>=1, 
														brandCodes.size()>=1,
					   									tagCodes.size()>=1,
					   									hasPrices,
					   									false,
					   									true), "ProductMapping")
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		.setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
		.setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
		.setParameter("categoryCode", categoryCode);
		
		//filtering is hardcoded to markdown price
		if(hasPrices) {
			query.setParameter("priceTypeCode", ProductVars.PRICE_MARKDOWN_CODE)
				 .setParameter("priceStart", priceStart)
				 .setParameter("priceEnd", priceEnd);
		}
		
		//these should contain default values for these parameters
		query
		//.setParameter("orderby", "1")
		.setParameter("limit", pageable.getPageSize())
		.setParameter("offset", pageable.getOffset());
		
		if(!brandCodes.isEmpty()) {
			query.setParameter("brandCodes", brandCodes);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		List<Product> lp = 
		results.stream().map(p -> {
			Product product = (Product) p[0];
			product.setProductStatus((ProductStatus) p[1]);
			product.setDepartment((Department) p[5]);
			product.setProductAttribute((ProductAttribute) p[2]);
			
			Brand brand = (Brand) p[3];
			product.setBrand(brand);
			brand.setBrandAttribute((BrandAttribute) p[4]);
			
			product.setRetailPrice(((BigDecimal) p[6]).doubleValue());
			product.setMarkdownPrice(((BigDecimal) p[7]).doubleValue());
			
			return product;
		}).collect(Collectors.toList());
		
		return new PageImpl<Product>(lp, pageable, total);
	}
	
	private String constructSQL(boolean hasProductCodes,
								boolean hasProductDesc,
								boolean hasProductId,
								boolean hasCategories,
								boolean hasBrands,
								boolean hasTags,
								boolean hasPrices,
								boolean countOnly,
								boolean offset) {
		
		//now we can implement conditional joins
		//based on the parameters passed
		return "WITH RECURSIVE    " + 
		"primary_descendants AS    " + 
		"(    " + 
		" SELECT 	t.cat_id,     " + 
		"			t.cat_cd,    " + 
		"			t.cat_lvl,    " + 
		"			t.cat_prnt_id,   " + 
		"			t.cat_typ_id   " + 
		" FROM mochi.category AS t   " + 
		" WHERE  " + 
		" t.cat_cd = :categoryCode " + 

		" UNION ALL    " + 
		" SELECT 	t.cat_id,     " + 
		"			t.cat_cd,     " + 
		"			t.cat_lvl,    " + 
		"			t.cat_prnt_id,   " + 
		"			t.cat_typ_id   " + 
		"  FROM mochi.category AS t    " + 
		"  JOIN primary_descendants AS d   " + 
		"  ON t.cat_prnt_id = d.cat_id    " + 
		"),  secondary_descendants AS    " + 
		"(    " + 
		" SELECT 	t.cat_id,     " + 
		"			t.cat_cd,    " + 
		"			t.cat_lvl,    " + 
		"			t.cat_prnt_id,   " + 
		"			t.cat_typ_id   " + 
		" FROM mochi.category AS t   " + 
		((hasCategories) 
						? " WHERE cat_cd in (:categoryCodes) " 
						: "") +

		" UNION ALL    " + 
		" SELECT 	t.cat_id,     " + 
		"			t.cat_cd,     " + 
		"			t.cat_lvl,    " + 
		"			t.cat_prnt_id,   " + 
		"			t.cat_typ_id   " + 
		"  FROM mochi.category AS t    " + 
		"  JOIN secondary_descendants AS d   " + 
		"  ON t.cat_prnt_id = d.cat_id    " + 
		"), descendants AS (   " + 
		"select cat_id, " + 
		"	   cat_cd, " + 
		"	   cat_lvl, " + 
		"	   cat_prnt_id, " + 
		"	   cat_typ_id " + 
		"from primary_descendants " + 
		"INTERSECT " + 
		"select cat_id, " + 
		"	   cat_cd, " + 
		"	   cat_lvl, " + 
		"	   cat_prnt_id, " + 
		"	   cat_typ_id  " + 
		"from secondary_descendants " + 
		") " + 
		"select 	    " + 
		((countOnly) 
					? 	"	   count(distinct prd.prd_id) as product_count  "
					: 	"	   cc.cat_id, " + 
						"	   cc.cat_cd, " +	
						"	   cc.cat_lvl, " +
						"	   cc.cat_prnt_id, " +	
						"	   prd.prd_id,   " + 
						"	   prd.upc_cd,   " + 
						"	   prd.prd_crtd_dt,   " +
						"	   attr.prd_lcl_id, " +
						"	   attr.prd_desc, " +	
						"	   attr.prd_img_pth, " +	
						"	   attr.lcl_cd, " +
						"	   dept.dept_id,   " + 
						"	   dept.dept_cd,   " + 
						"	   dept.dept_class,   " + 
						"	   bnd.bnd_id,   " + 
						"	   bnd.bnd_cd,   " + 
						"	   bal.bnd_lcl_id,		  " + 
						"	   bal.bnd_desc,   " + 
						"	   ps.prd_sts_id,   " + 
						"	   ps.prd_sts_cd,   " + 
						"	   ps.prd_sts_desc,  " + 
						"	   rprc.prc_val as retail_price,  " + 
						"	   mprc.prc_val as markdown_price,  " + 
						"	   food.exp_dt, " +
						"	   food.ctry_of_orig ") + 
		
		"FROM descendants cc    " + 
		"	INNER JOIN mochi.product_category pc    " + 
		"	ON cc.cat_id = pc.cat_id    " + 
		
		"	INNER JOIN mochi.category p1	  " + 
		"	ON cc.cat_prnt_id = p1.cat_id   " + 
		 
		"	LEFT JOIN mochi.category p2	  " + 
		"	ON p1.cat_prnt_id = p2.cat_id  " + 
		 
		"	LEFT JOIN mochi.category p3	  " + 
		"	ON p2.cat_prnt_id = p3.cat_id  " + 
		 
		"	LEFT JOIN mochi.category p4	  " + 
		"	ON p3.cat_prnt_id = p4.cat_id  " + 
	
		"	INNER JOIN mochi.product prd    " + 
		"	ON pc.prd_id = prd.prd_id   " + 
		
		"	INNER JOIN mochi.product_attr_lcl attr " +
		"	ON prd.prd_id = attr.prd_id " + 
		 
		"	INNER JOIN mochi.department dept   " + 
		"	ON prd.dept_id = dept.dept_id   " + 
			
		"	INNER JOIN mochi.brand bnd   " + 
		"	ON prd.bnd_id = bnd.bnd_id   " + 
		 
		"	INNER JOIN mochi.brand_attr_lcl bal   " + 
		"	ON bnd.bnd_id = bal.bnd_id   " + 
			
		"	LEFT JOIN mochi.price rprc     " + 
		"	ON prd.prd_id = rprc.prd_id    " + 
			
		"	INNER JOIN mochi.currency rcurr     " + 
		"	ON rprc.ccy_id 		= rcurr.ccy_id   " + 
		"	AND rcurr.ccy_cd 	= :currency " + 
		
		"	INNER JOIN mochi.price_type rpt   " + 
		"	ON rprc.prc_typ_id 	= rpt.prc_typ_id   " + 
		"	AND rpt.prc_typ_cd = :retailPriceCode " +
		
		"	LEFT JOIN mochi.price mprc     " + 
		"	ON prd.prd_id = mprc.prd_id    " +
		
		"	LEFT JOIN mochi.product_food food " + 
		"	ON prd.prd_id = food.prd_id    " + 
		
		"	LEFT JOIN mochi.product_jewellery jew " + 
		"	ON prd.prd_id = jew.prd_id    " +
			
		"	INNER JOIN mochi.currency mcurr     " + 
		"	ON mprc.ccy_id 		= mcurr.ccy_id   " + 
		"	AND mcurr.ccy_cd 	= :currency " + 
		
		"	INNER JOIN mochi.price_type mpt   " + 
		"	ON mprc.prc_typ_id 	= mpt.prc_typ_id   " + 
		"	AND mpt.prc_typ_cd = :markdownPriceCode " +
			 
		"	INNER JOIN mochi.product_status ps    " + 
		"	ON prd.prd_sts_id = ps.prd_sts_id   " + 
		
		((hasTags) ? 
						"	INNER JOIN mochi.product_tag ptags	 " +
						"	ON prd.prd_id = ptags.prd_id " +
		
						"	INNER JOIN mochi.tag tag	 " +
						"	ON ptags.tag_id = tag.tag_id "
				   : 	"") +
		
		"WHERE 0=0 " +
		"AND prd_sts_cd = 			:activeProductCode  " + 
		"AND bal.lcl_cd = 			:locale " +
		"AND attr.lcl_cd = 			:locale " +	
		((hasBrands) 
					? "AND bnd.bnd_cd in 		:brandCodes " 
					: "") +
		((hasPrices) 
				?   "	   AND  case  " + 
					"	   		when rpt.prc_typ_cd = :priceTypeCode  " + 
					"	   		then rprc.prc_val  " + 
					"			when mpt.prc_typ_cd = :priceTypeCode  "	+
					"			then mprc.prc_val " + 		
					"	   		else 0  " + 
					"	   		end between :priceStart AND :priceEnd " 
				: 	"") +
		 
			((hasProductCodes) 	? 	" 	AND prd.upc_cd 		in :productCodes" 	: "") +
			((hasProductDesc) 	? 	" 	AND attr.prd_desc 	= :productDesc " 	: "") +
			((hasProductId) 	? 	" 	AND prd.prd_id 		= :productId " 		: "") +
			((countOnly || !offset) 
					? 	""
					: 	//" ORDER BY 	:orderby " + 
						" LIMIT 	:limit " +
						" OFFSET 	:offset "
			);
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
