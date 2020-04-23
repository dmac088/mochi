package io.nzbee.entity.product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;

import org.apache.tomcat.util.buf.StringUtils;
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
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.attribute.ProductAttribute_;
import io.nzbee.entity.product.department.Department;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPrice_;
import io.nzbee.entity.product.status.ProductStatus;
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
		LOGGER.debug("call ProductDaoPostgresImpl.findById parameters : {}, {}, {}", locale, currency, id);
		
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
		.setParameter("productId", id)
		.setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		.setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
		.setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		
		try {
			Object[] p = (Object[])query.getSingleResult();
			
			Product product = this.objectToEntity(p, locale, currency);
			return Optional.ofNullable(product);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Product> findByCode(String locale, String currency, String code) {
		LOGGER.debug("call ProductDaoPostgresImpl.findByCode with parameters : {}, {}, {}", locale, currency, code);
		
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

		try {
			Object[] p = (Object[])query.getSingleResult();
			
			Product product = this.objectToEntity(p, locale, currency);
			return Optional.ofNullable(product);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Product> findByDesc(String locale, String currency, String desc) {
		LOGGER.debug("call ProductDaoPostgresImpl.findByDesc with parameters : {}, {}, {}", locale, currency, desc);
		
		Query query = em.createNativeQuery(this.constructSQL(false,
															 true,
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
		.setParameter("productDesc", desc)
		.setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		.setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
		.setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		

		try {
			Object[] p = (Object[])query.getSingleResult();
			
			Product product = this.objectToEntity(p, locale, currency);
			return Optional.ofNullable(product);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Product> findAll(String locale, String currency) {
		LOGGER.debug("call ProductDaoPostgresImpl.findAll with parameters : {}, {}", locale, currency);
		
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
		
		return results.stream().map(p -> this.objectToEntity(p, locale, currency)).collect(Collectors.toList());
	}
	

	@Override
	public List<Product> findAll(	String locale, 
									String currency, 
									Set<String> codes) {
		LOGGER.debug("call ProductDaoPostgresImpl.findAll with parameters : {}, {}, {}", locale, currency, StringUtils.join(codes, ','));
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									Pageable pageable, 
									String orderby) {
		LOGGER.debug("call ProductDaoPostgresImpl.findAll with parameters : {}, {}, {}, {}", locale, currency, pageable, orderby);
		
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
				results.stream().map(p -> this.objectToEntity(p, locale, currency)).collect(Collectors.toList());
		
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
		
		LOGGER.debug("call ProductDaoPostgresImpl.findAll with  parameters : {}, {}, {}, {}, {}, {}", locale, currency, pageable, categoryCode, brandCodes, tagCodes, orderby);
		
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
		results.stream().map(p -> this.objectToEntity(p, locale, currency)).collect(Collectors.toList());
		
		return new PageImpl<Product>(lp, pageable, total);
	}
	
	@Override
	public Product objectToEntity(Object[] o, String locale, String currency) {
		
		Product product = (Product) o[0];
		Department department = (Department) o[5];
		DepartmentAttribute departmentAttribute = (DepartmentAttribute) o[6];
		department.setAttribute(departmentAttribute);
		
		product.setProductStatus((ProductStatus) o[1]);
		product.setDepartment(department);
		product.setProductAttribute((ProductAttribute) o[2]);
		
		Brand brand = (Brand) o[3];
		product.setBrand(brand);
		brand.setBrandAttribute((BrandAttribute) o[4]);
		
		CategoryProduct category = (CategoryProduct) o[7];
		CategoryAttribute categoryAttribute = (CategoryAttribute) o[8];
		categoryAttribute.setCategory(category);
		category.setCategoryAttribute(categoryAttribute);
		
		product.setPrimaryCategory(category);

		CategoryType categoryType = (CategoryType) o[9];
		category.setCategoryType(categoryType);
		
		CategoryProduct parent = (CategoryProduct) o[10];
		category.setParent(parent);
		
		product.setRetailPrice(((BigDecimal) o[11]).doubleValue());
		product.setMarkdownPrice(((BigDecimal) o[12]).doubleValue());
		
		product.setLocale(locale);
		product.setCurrency(currency);
		product.setDisplayCategories(o[13].toString());
		product.setImagePath(o[14].toString());
		
		return product;
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
					: 	"	   cp.cat_id, " + 
						"	   cp.cat_cd, " +	
						"	   cp.cat_lvl, " +
						"	   cp.cat_prnt_id, " +	
						"	   ca.cat_lcl_id, " +
						"	   ca.cat_id, " +
						"	   ca.cat_desc, " +
						"	   ca.cat_img_pth, " +
						"	   ct.cat_typ_id 		AS cat_typ_id, 	" +
						"      ct.cat_typ_cd		AS cat_typ_cd, " +
						"      ct.cat_typ_desc 		AS cat_typ_desc, " + 
						"	   parent.cat_cd 		AS cat_prnt_cd, " + 
						"	   parent.cat_lvl 		AS cat_prnt_lvl, " + 
						"	   parent.cat_prnt_id	AS cat_prnt_prnt_id, " + 
						"	   prd.prd_id,   " + 
						"	   prd.upc_cd,   " + 
						"	   prd.prd_crtd_dt,   " +
						"	   prd.prm_cat_id, 	" +
						"	   attr.prd_lcl_id, " +
						"	   attr.prd_desc, " +	
						"	   attr.prd_img_pth, " +	
						"	   attr.lcl_cd, " +
						"	   dept.dept_id,   " + 
						"	   dept.dept_cd,   " + 
						"	   dept.dept_class,   " +
						"	   dattr.dept_lcl_id, " +	
						"	   dattr.dept_desc,   " +
						"	   bnd.bnd_id,   " + 
						"	   bnd.bnd_cd,   " + 
						"	   bal.bnd_lcl_id,		  " + 
						"	   bal.bnd_desc,   " + 
						"	   ps.prd_sts_id,   " + 
						"	   ps.prd_sts_cd,   " + 
						"	   ps.prd_sts_desc,  " + 
						"	   coalesce(rprc.prc_val,0) as retail_price,  " + 
						"	   coalesce(mprc.prc_val,0) as markdown_price,  " + 
						"	   food.exp_dt, " +
						"	   food.ctry_of_orig, " + 
						"	   STRING_AGG(coalesce(ca.cat_desc, ''), ',') as display_categories, " +
						"      :currency as ccy_cd, " +
						"	   :locale as lcl_cd ") + 
		
		"	FROM descendants cc    " + 
		
		"	INNER JOIN mochi.product_category pc    " + 
		"	ON cc.cat_id = pc.cat_id    " + 
		
		"	INNER JOIN mochi.product prd    " + 
		"	ON pc.prd_id = prd.prd_id   " + 
		
		"	INNER JOIN mochi.product_attr_lcl attr " +
		"	ON prd.prd_id = attr.prd_id " + 

		"	INNER JOIN mochi.category cp " + 
		"	ON prd.prm_cat_id = cp.cat_id " +

		"	INNER JOIN mochi.category_type ct  " + 
		"	ON cc.cat_typ_id = ct.cat_typ_id " + 	
		"	AND ct.cat_typ_cd = 'PRD01' " +

		"	INNER JOIN mochi.category_attr_lcl ca    " + 
		"	ON cp.cat_id = ca.cat_id    " + 
		"	AND ca.lcl_cd = :locale " +		
		
		"	LEFT JOIN mochi.category parent " +
		"	ON cp.cat_prnt_id = parent.cat_id  " +
		
		"	INNER JOIN mochi.department dept   " + 
		"	ON prd.dept_id = dept.dept_id   " + 
		
		"	INNER JOIN mochi.department_attr_lcl dattr   " + 
		"	ON dept.dept_id = dattr.dept_id   " + 
		"	AND dattr.lcl_cd = :locale " +
			
		"	INNER JOIN mochi.brand bnd   " + 
		"	ON prd.bnd_id = bnd.bnd_id   " + 
		 
		"	INNER JOIN mochi.brand_attr_lcl bal   " + 
		"	ON bnd.bnd_id = bal.bnd_id   " + 
		
		"	LEFT JOIN  ( " + 
		"	SELECT prd_id, " +  
		"		   prc_val  " +  
		"	FROM mochi.price rprc " +  
		"	INNER JOIN mochi.currency rcurr " +  
		"	ON         rprc.ccy_id = rcurr.ccy_id " +  
		"	AND        rcurr.ccy_cd = :currency " + 
		"	INNER JOIN mochi.price_type rpt " + 
		"	ON         rprc.prc_typ_id = rpt.prc_typ_id " +  
		"	AND        rpt.prc_typ_cd = :retailPriceCode " +  
		"	) rprc " + 
		"	ON prd.prd_id = rprc.prd_id " +  
		
		"	LEFT JOIN  ( " +
		"	SELECT prd_id, " +  
		"		   prc_val " + 
		"		FROM mochi.price mprc " + 
		"		INNER JOIN mochi.currency mcurr " + 
	    "		ON         mprc.ccy_id = mcurr.ccy_id  " + 
		"		AND        mcurr.ccy_cd = :currency  " +
		"		INNER JOIN mochi.price_type mpt " +
		"		ON         mprc.prc_typ_id = mpt.prc_typ_id " + 
		"		AND        mpt.prc_typ_cd = :markdownPriceCode " + 
		"		) mprc  " +
		"		ON prd.prd_id = mprc.prd_id  " + 
		
		"	LEFT JOIN mochi.product_food food " + 
		"	ON prd.prd_id = food.prd_id    " + 
		
		"	LEFT JOIN mochi.product_jewellery jew " + 
		"	ON prd.prd_id = jew.prd_id    " +
			 
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
			
		((countOnly)
			? 		""
			: 		"	GROUP BY   " +
					"	   cp.cat_id, " + 
					"	   cp.cat_cd, " +	
					"	   cp.cat_lvl, " +
					"	   cp.cat_prnt_id, " +		
					"	   ca.cat_lcl_id, " +
					"	   ca.cat_id, " +
					"	   ca.cat_desc, " +
					"	   ca.cat_img_pth, " +
					"	   ct.cat_typ_id, " + 
					"	   ct.cat_typ_cd, " +
					"	   ct.cat_typ_desc, " +
					"	   parent.cat_cd, " + 
					"	   parent.cat_lvl, " + 
					"	   parent.cat_prnt_id, " + 
					"	   prd.prd_id,  " + 
					"	   prd.upc_cd,  " + 
					"	   prd.prd_crtd_dt,  " +
					"	   attr.prd_lcl_id, " +
					"	   attr.prd_desc, " +	
					"	   attr.prd_img_pth, " +	
					"	   attr.lcl_cd, " +
					"	   dept.dept_id,   " + 
					"	   dept.dept_cd,   " + 
					"	   dept.dept_class,   " + 
					"	   dattr.dept_lcl_id, " +	
					"	   dattr.dept_desc,   " +
					"	   bnd.bnd_id,   " + 
					"	   bnd.bnd_cd,   " + 
					"	   bal.bnd_lcl_id,		  " + 
					"	   bal.bnd_desc,   " + 
					"	   ps.prd_sts_id,   " + 
					"	   ps.prd_sts_cd,   " + 
					"	   ps.prd_sts_desc,  " + 
					"	   rprc.prc_val,  " + 
					"	   mprc.prc_val,  " + 
					"	   food.exp_dt, " +
					"	   food.ctry_of_orig ") +
			
			
			((countOnly || !offset) 
					? 	""
					: 	//" ORDER BY 	:orderby " + 
						" LIMIT 	:limit " +
						" OFFSET 	:offset "
			);
	}

	@Override
	public void save(Product t) {
		em.persist(t);
		em.flush();
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

	@Override
	public Product objectToEntity(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}


}
