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
		
		
		Query query = em.createNativeQuery(this.constructSQL(false, 
															 false,
															 false,
															 false))
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
		Query query = em.createNativeQuery(this.constructSQL(false, 
															 false,
															 false,
															 true))
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
				 .setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
				 .setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE);
		
		Object result = query.getSingleResult();
		long total = ((long) result);
		
		query = em.createNamedQuery(this.constructSQL(false, 
													  false,
													  false,
													  false))
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
		return this.findAll(locale, 
							currency, 
							new Double(-1), 
							new Double(-1), 
							page, 
							size, 
							categoryDesc, 
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
								 int page, 
								 int size,
								 String categoryDesc,
								 List<String> categoryCodes,
								 List<String> brandCodes, 
								 List<String> tagCodes,
								 String orderby) {
		
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(this.constructSQL(categoryCodes.size()>=1, 
  				 											 tagCodes.size()>=1,
  				 											 (!(priceStart.equals(new Double(-1)) && (priceEnd.equals(new Double(-1))))),
  				 											 true), "ProductMapping")
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("productTypeCode", ProductVars.PRODUCT_TYPE_RETAIL)
		.setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		.setParameter("brandCodes", brandCodes);
		
		Object result = query.getSingleResult();
		long total = ((long) result);
		
		query = em.createNativeQuery(this.constructSQL(	categoryCodes.size()>=1, 
					   									tagCodes.size()>=1,
					   									(!(priceStart.equals(new Double(-1)) && (priceEnd.equals(new Double(-1))))),
					   									false), "ProductMapping")
		.setParameter("locale", locale)
		.setParameter("currency", currency)
		.setParameter("productTypeCode", ProductVars.PRODUCT_TYPE_RETAIL)
		.setParameter("activeProductCode", ProductVars.ACTIVE_SKU_CODE)
		.setParameter("retailPriceCode", ProductVars.PRICE_RETAIL_CODE)
		.setParameter("markdownPriceCode", ProductVars.PRICE_MARKDOWN_CODE)
		
		//filtering is hardcoded to markdown price
		.setParameter("priceTypeCode", ProductVars.PRICE_MARKDOWN_CODE)
		
		//these should contain default values for these parameters
		.setParameter("orderby", "1")
		.setParameter("limit", Integer.toString(size))
		.setParameter("offset", Integer.toString(page * size))
		.setParameter("priceStart", priceStart)
		.setParameter("priceEnd", priceEnd)
		.setParameter("brandCodes", brandCodes);
		
		
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
	
	private String constructSQL(
								boolean hasCategories, 
								boolean hasTags,
								boolean hasPrices,
								boolean countOnly) {
		//now we can implement conditional joins
		//based on the parameters passed
		
		return "WITH RECURSIVE    " + 
		"primary_descendants AS    " + 
		"(    " + 
		" SELECT 	t.cat_id,     " + 
		"			t.hir_id,    " + 
		"			t.cat_cd,    " + 
		"			t.cat_lvl,    " + 
		"			t.cat_prnt_id,   " + 
		"			t.cat_typ_id   " + 
		" FROM mochi.category AS t   " + 
	
		"	INNER JOIN mochi.category_attr_lcl AS attr  " + 
		"	ON t.cat_id = attr.cat_id 	 " + 

		" WHERE  " + 
		" attr.cat_desc = :categoryDesc " + 
		" AND attr.lcl_cd = :locale " + 

		" UNION ALL    " + 
		" SELECT 	t.cat_id,     " + 
		"			t.hir_id,    " + 
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
		"			t.hir_id,    " + 
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
		"			t.hir_id,    " + 
		"			t.cat_cd,     " + 
		"			t.cat_lvl,    " + 
		"			t.cat_prnt_id,   " + 
		"			t.cat_typ_id   " + 
		"  FROM mochi.category AS t    " + 
		"  JOIN secondary_descendants AS d   " + 
		"  ON t.cat_prnt_id = d.cat_id    " + 
		"), descendants AS (   " + 
		"select cat_id, " + 
		"	   hir_id, " + 
		"	   cat_cd, " + 
		"	   cat_lvl, " + 
		"	   cat_prnt_id, " + 
		"	   cat_typ_id " + 
		"from primary_descendants " + 
		"INTERSECT " + 
		"select cat_id, " + 
		"	   hir_id, " + 
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
						"	   prdt.prd_typ_cd,   " + 
						"	   prdt.prd_typ_desc,   " + 
						"	   bnd.bnd_id,   " + 
						"	   bnd.bnd_cd,   " + 
						"	   bal.bnd_lcl_id,		  " + 
						"	   bal.bnd_desc,   " + 
						"	   ps.prd_sts_id,   " + 
						"	   ps.prd_sts_cd,   " + 
						"	   ps.prd_sts_desc,  " + 
						"	   max(case  " + 
						"	   when prc_typ_cd = :retailPriceCode " + 
						"	   then prc.prc_val  " + 
						"	   else 0  " + 
						"	   end) as retail_price,  " + 
						"	   max(case  " + 
						"	   when prc_typ_cd = :markdownPriceCode  " + 
						"	   then prc.prc_val  " + 
						"	   else 0  " + 
						"	   end) as markdown_price  ") + 
		
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
		 
		"	INNER JOIN mochi.product_type prdt   " + 
		"	ON prd.prd_typ_id = prdt.prd_typ_id   " + 
			
		"	INNER JOIN mochi.brand bnd   " + 
		"	ON prd.bnd_id = bnd.bnd_id   " + 
		 
		"	INNER JOIN mochi.brand_attr_lcl bal   " + 
		"	ON bnd.bnd_id = bal.bnd_id   " + 
			
		"	INNER JOIN mochi.price prc     " + 
		"	ON prd.prd_id = prc.prd_id    " + 
			
		"	INNER JOIN mochi.currency curr     " + 
		"	ON prc.ccy_id = curr.ccy_id   " + 
		
		"	INNER JOIN mochi.price_type pt   " + 
		"	ON prc.prc_typ_id = pt.prc_typ_id   " + 
			 
		"	INNER JOIN mochi.product_status ps    " + 
		"	ON prd.prd_sts_id = ps.prd_sts_id   " + 
		
		((hasTags) ? 
						"	INNER JOIN mochi.product_tag ptags	 " +
						"	ON prd.prd_id = ptags.prd_id " +
		
						"	INNER JOIN mochi.tag tag	 " +
						"	ON ptags.tag_id = tag.tag_id "
				   : 	"") +
		
		"WHERE now() >= prc.prc_st_dt AND now() <= prc.prc_en_dt  " + 
		"AND prdt.prd_typ_cd = :productTypeCode " +
		"AND curr.ccy_cd = 		:currency " + 
		"AND prd_sts_cd = 		:activeProductCode  " + 
		"AND bal.lcl_cd = 		:locale " + 
		"AND bnd.bnd_cd in 		(:brandCodes) " + 
		((hasPrices) 
				?   "	   AND  case  " + 
					"	   		when prc_typ_cd = :priceTypeCode  " + 
					"	   		then prc.prc_val  " + 
					"	   		else 0  " + 
					"	   		end between :priceStart AND :priceEnd " 
				: 	"") +
		((countOnly) 
					? 	""
					: 	"GROUP BY  " + 
						"	   cc.cat_id, " + 
						"	   cc.cat_cd, " +	
						"	   cc.cat_lvl, " +
						"	   cc.cat_prnt_id, " +	
						"	   prd.prd_id,   " + 
						"	   prd.upc_cd,   " + 
						"	   prd.prd_crtd_dt,   " + 
						"	   prdt.prd_typ_cd,   " + 
						"	   prdt.prd_typ_desc,   " + 
						"	   bnd.bnd_id,   " + 
						"	   bnd.bnd_cd,   " + 
						"	   bal.bnd_lcl_id,  " + 
						"	   bal.bnd_desc,   " + 
						"	   ps.prd_sts_id,   " + 
						"	   ps.prd_sts_cd,   " + 
						"	   ps.prd_sts_desc   " + 
						" ORDER BY 	:orderby " + 
						" LIMIT 	:limit " +
						" OFFSET 	:offset ");
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
