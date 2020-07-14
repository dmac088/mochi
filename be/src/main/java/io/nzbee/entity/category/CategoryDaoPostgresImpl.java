
package io.nzbee.entity.category;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.tomcat.util.buf.StringUtils;
import org.hibernate.Session;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.Globals;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.CategoryProduct_;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;

@Component(value="categoryEntityPostgresDao")
public class CategoryDaoPostgresImpl implements ICategoryDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private Globals globalVars;

	@Override
	public Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes,
			Set<String> tagCodes) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, StringUtils.join(brandCodes), StringUtils.join(tagCodes));
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(!categoryCodes.isEmpty(), 
															 !brandCodes.isEmpty(),
															 !tagCodes.isEmpty(),
															 false,
															 false,
															 true,
															 false,
															 false,
															 true,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", globalVars.getActiveSKUCode())
				 .setParameter("retailPriceCode", globalVars.getRetailPriceCode())
				 .setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode());
				 
		if(!categoryCodes.isEmpty()) {
			 query.setParameter("categoryCodes", categoryCodes);
		}
		
		if(!brandCodes.isEmpty()) {
			 query.setParameter("brandCodes", brandCodes);
		}
			
		if(!tagCodes.isEmpty()) {
			 query.setParameter("tagCodes", tagCodes);
		}
		
		query.setParameter("categoryCode", categoryCode);
		
		Double result = (new BigDecimal(query.getSingleResult().toString())).doubleValue();
		
		return result;
	}
	
	@Override
	public <T> List<Category> findAllByType(String locale, String currency, Class<T> cls) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findByCodeAndType parameters : {}, {}, {}", locale, currency, cls.getSimpleName());
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(false, 
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false, 
															 true), "CategoryMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", globalVars.getActiveSKUCode())
				 .setParameter("retailPriceCode", globalVars.getRetailPriceCode())
				 .setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode())
				 .setParameter("typeDiscriminator", Long.parseLong(cls.getAnnotation(DiscriminatorValue.class).value()));
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		return results.stream().map(c -> this.objectToEntity(c, locale, currency)).collect(Collectors.toList());
	}
	
	
	@Override
	public List<Category> findAll(String locale, String currency) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : {}, {}", locale, currency);
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(false, 
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false), "CategoryMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", globalVars.getActiveSKUCode())
				 .setParameter("retailPriceCode", globalVars.getRetailPriceCode())
				 .setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode());
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		return results.stream().map(c -> this.objectToEntity(c, locale, currency)).collect(Collectors.toList());
	}
	


	@Override
	public List<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes,
			Set<String> tagCodes, Double maxPrice) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, StringUtils.join(brandCodes), StringUtils.join(tagCodes));
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(!categoryCodes.isEmpty(), 
															 !brandCodes.isEmpty(),
															 !tagCodes.isEmpty(),
															 !(maxPrice == null),
															 true,
															 false,
															 false,
															 false,
															 true,
															 false), "CategoryMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", globalVars.getActiveSKUCode())
				 .setParameter("retailPriceCode", globalVars.getRetailPriceCode())
				 .setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode());
				 
		if(!categoryCodes.isEmpty()) {
		//	 query.setParameter("categoryCodes", categoryCodes);
		}
		
		if(!brandCodes.isEmpty()) {
			 query.setParameter("brandCodes", brandCodes);
		}
			
		if(!tagCodes.isEmpty()) {
			 query.setParameter("tagCodes", tagCodes);
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}
		
		query.setParameter("categoryCode", categoryCode);
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		return results.stream().map(c -> this.objectToEntity(c, locale, currency)).collect(Collectors.toList());
	}


	@Override
	public List<Category> findAll(String locale, String currency, Set<String> categoryCodes) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : {}, {}, {}", locale, currency, StringUtil.join(categoryCodes, ','));
		
		Session session = em.unwrap(Session.class);

		Query query = session.createNativeQuery(constructSQL(!categoryCodes.isEmpty(),
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false), "CategoryMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", globalVars.getActiveSKUCode())
				 .setParameter("retailPriceCode", globalVars.getRetailPriceCode())
				 .setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode());
		
		if(!categoryCodes.isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		return results.stream().map(c -> this.objectToEntity(c, locale, currency)).collect(Collectors.toList());
	}
	
	@Override
	public Optional<Category> findById(String locale, String currency, long id) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findById parameters : {}, {}, {}", locale, currency, id);
		
		Session session = em.unwrap(Session.class);
		
		final List<String> categoryCodes = new ArrayList<String>();
		
		Query query = session.createNativeQuery(constructSQL(!categoryCodes.isEmpty(),
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 true,
															 false,
															 false), "CategoryMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("categoryId", id)
				 .setParameter("activeProductCode", globalVars.getActiveSKUCode())
				 .setParameter("retailPriceCode", globalVars.getRetailPriceCode())
				 .setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode());
		
		if(!categoryCodes.isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes);
		}

		try {
			Object[] c = (Object[])query.getSingleResult();
			
			Category category = this.objectToEntity(c, locale, currency);
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Category> findByDesc(String locale, String currency, String desc) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findByDesc parameters : {}, {}, {}", locale, currency, desc);
		
		Session session = em.unwrap(Session.class);
		
		final List<String> categoryCodes = new ArrayList<String>();
		
		Query query = session.createNativeQuery(constructSQL(!categoryCodes.isEmpty(),
															 false,
															 false,
															 false,
															 false,
															 false,
															 true,
															 false,
															 false,
															 false), "CategoryMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("categoryDesc", desc)
				 .setParameter("activeProductCode", globalVars.getActiveSKUCode())
				 .setParameter("retailPriceCode", globalVars.getRetailPriceCode())
				 .setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode());
		
		if(!categoryCodes.isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes);
		}

		try {
			Object[] c = (Object[])query.getSingleResult();
			
			Category category = this.objectToEntity(c, locale, currency);
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}

	}

	@Override
	public Optional<Category> findByCode(String locale, String currency, String code) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findByCode parameters : {}, {}, {}", locale, currency, code);
		
		Session session = em.unwrap(Session.class);
		
		final List<String> categoryCodes = new ArrayList<String>();
		
		categoryCodes.add(code);
		
		Query query = session.createNativeQuery(constructSQL(!categoryCodes.isEmpty(),
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 true,
															 false), "CategoryMapping")
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("categoryCode", code)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", globalVars.getActiveSKUCode())
				 .setParameter("retailPriceCode", globalVars.getRetailPriceCode())
				 .setParameter("markdownPriceCode", globalVars.getMarkdownPriceCode());
		
		if(!categoryCodes.isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes);
		}

		try {
			Object[] c = (Object[])query.getSingleResult();
			
			Category category = this.objectToEntity(c, locale, currency);
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	

	@Override
	public List<Category> findByParent(String parentCategoryCode, String locale) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findByParent parameters : {}, {}", parentCategoryCode, locale);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Category> parent = root.join(Category_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
	
		if(!(parentCategoryCode == null)) {
			conditions.add(cb.equal(parent.get(Category_.categoryCode), parentCategoryCode));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	
	@Override
	public List<Category> findByLevel(String locale, Long level) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findByLevel parameters : {}, {}", locale, level);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!(level == null)) {
			conditions.add(cb.equal(root.get(Category_.categoryLevel), level));
		}
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	@Override
	public List<Category> findAllByProductCode(String locale, String currency, String productCode) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findAllByProductCode parameters : {}, {}, {}", locale, currency, productCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(CategoryProduct_.attributes);
		Join<CategoryProduct, Product> product = root.join(CategoryProduct_.products);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(product.get(Product_.productUPC), productCode));
		
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}

	@Override
	public Category objectToEntity(Tuple t, String locale, String currency) {
		Category c =  t.get("categoryType").toString().equals("PRD01") 
					? new CategoryProduct()
					: new CategoryBrand();
		
		c.setCategoryCode(t.get("categoryCode").toString());
		CategoryAttribute ca = new CategoryAttribute();
		ca.setCategoryDesc(t.get("categoryDesc").toString());
		c.setCategoryAttribute(ca);
		
		c.setLocale(locale);
		c.setCurrency(currency);
		
		return c;
	}
	
	@Override
	public Category objectToEntity(Object[] o, String locale, String currency) {
		Category category = (Category) o[0];
		category.setCategoryAttribute(((CategoryAttribute) o[1]));
		category.setCategoryType((CategoryType) o[2]);
		if(category instanceof CategoryProduct) {
			((CategoryProduct) category).setHasParent(o[3] != null);
			if(((CategoryProduct) category).hasParent()) {
				//we have a parent
				Category parentCategory = (Category) o[3];
				parentCategory.setCategoryAttribute(((CategoryAttribute) o[4]));
				parentCategory.setCategoryType((CategoryType) o[5]);
				category.setParent(parentCategory);
			}
		}
		category.setObjectCount(((BigDecimal)o[6]).intValue());
		category.setChildCount(((BigInteger)o[7]).longValue());
		category.setLocale(locale);
		category.setCurrency(currency);
		
		return category;
	}
	
	@Override
	public void save(Category t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Category t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Category t) {
		// TODO Auto-generated method stub
	}	
	
	private String constructSQL(
				boolean hasCategories,
				boolean hasBrands,
				boolean hasTags,
				boolean hasPrice,
				boolean childrenOnly,
				boolean maxPriceOnly,
				boolean hasCategoryDesc,
				boolean hasCategoryId,
				boolean hasCategoryCd,
				boolean hasType
			) {
		String sql = "WITH RECURSIVE  " +
				"descendants AS " +
				"( " +
				"  SELECT 	t.cat_id,  " +
				"			t.cat_cd, " +
				"			t.cat_lvl, " +
				"			t.cat_prnt_id,  " +
				"			t.cat_typ_id, " +
				"			cast('/' || cast(t.cat_id as text) || '/' as text) node " +
				"  FROM mochi.category AS t " +
				
				"  LEFT JOIN mochi.category_attr_lcl a " +
				"  ON t.cat_id = a.cat_id " +
				"  AND a.lcl_cd = :locale " +
				
				"  WHERE 0=0 " +
				((hasCategoryDesc)  ? " AND a.cat_desc = :categoryDesc " : "") + 
				((hasCategoryId)  	? " AND t.cat_id = :categoryId" : "") +
				((hasCategoryCd  	? " AND t.cat_cd = :categoryCode" : "") +
				((!hasCategoryCd 	&& !hasCategoryDesc 	&& !hasCategoryId) 		? " AND cat_prnt_id IS NULL " : "") +
				"  UNION ALL " +
				"  SELECT 	t.cat_id,  " +
				"			t.cat_cd,  " +
				"			t.cat_lvl, " +
				"			t.cat_prnt_id,  " +
				"			t.cat_typ_id, " +	
				"			cast(d.node || CAST(t.cat_id as text) || '/' as text) node " +
				"  FROM mochi.category AS t  " +
				"  JOIN descendants AS d  " +
				"  ON t.cat_prnt_id = d.cat_id " +
				"), summaries_pta " +
				"AS " +
				"( " +
				"select " +
				"    cc.cat_id 					AS cat_id, " +
				"    cc.cat_cd 					AS cat_cd, " +
				"    cc.cat_lvl 				AS cat_lvl, " +
				"    cc.cat_prnt_id 			AS prnt_id, " +
				"    cc.cat_typ_id 				AS cat_type_id, " +
				"    cc.node, " +
				"    COUNT(DISTINCT prd.upc_cd) 	AS object_count, " +
				"    MAX(markdown_price.prc_val) 	AS max_markdown_price,  " +
				"    MAX(retail_price.prc_val) 		AS max_retail_price " +
				"FROM descendants cc " +
				"LEFT JOIN mochi.product_category pc " +
				"ON cc.cat_id = pc.cat_id " +
				
				"LEFT JOIN 	( " +
				"		 SELECT prd.prd_id,  " +
				"			upc_cd " +
				"		 FROM mochi.product prd " +
				"		  " +
				"		 INNER JOIN mochi.product_status ps " +
				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
				"		  " +
				
				((hasBrands) ?
				"INNER JOIN mochi.brand b " +
				"		ON prd.bnd_id = b.bnd_id " +
				" 		AND b.bnd_cd in 	:brandCodes "
				: "") +
				
				((hasTags) ?
				"INNER JOIN mochi.product_tag pt" + 
				"		ON prd.prd_id = pt.prd_id " + 
				
				
				"INNER JOIN mochi.tag t " + 
				"		ON pt.tag_id = t.tag_id" +
				" 		AND t.tag_cd in 	:tagCodes " 
				: "") +
				
				((hasPrice) ?
				"INNER JOIN   ( " +
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
				"		AND prc_val <= :maxPrice " 
				: "") +
				
				((hasCategories) ? 	
				" INNER JOIN mochi.product_category pc " +
				"		ON prd.prd_id = pc.prd_id  " +
				
				" INNER JOIN mochi.category c " +
				"		ON  pc.cat_id = c.cat_id  " //+
				//" 		AND c.cat_cd in 	:categoryCodes " 
				: ""
					) +
				
				" WHERE prd_sts_cd = :activeProductCode " +
				") prd  " +
				"		 ON pc.prd_id = prd.prd_id " +
				"		  " +
				"LEFT JOIN 	( " +
				"		 SELECT prd.prd_id,  " +
				"			prc_typ_cd, " +
				"			prc_val  " +
				"		 FROM mochi.product prd " +
				
				"		 INNER JOIN mochi.price prc  " +
				"		 ON prd.prd_id = prc.prd_id " +
				"		  " +
				"		 INNER JOIN mochi.currency curr  " +
				"		 ON prc.ccy_id = curr.ccy_id  " +
				"		  " +
				"		 INNER JOIN mochi.price_type pt  " +
				"		 ON prc.prc_typ_id = pt.prc_typ_id " +

				"		 INNER JOIN mochi.product_status ps " +
				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
				"		  " +
				"		 WHERE curr.ccy_cd = 	:currency " +
				"		 AND prc_typ_cd = 		:retailPriceCode " +
				"		 AND prd_sts_cd = 		:activeProductCode " +
				"		 ) retail_price " +
				"		 ON pc.prd_id = retail_price.prd_id " +
				"		  " +
				"LEFT JOIN 	(SELECT prd.prd_id,  " +
				"			prc_typ_cd, " +
				"			prc_val  " +
				"		 FROM mochi.product prd " +

				"		 INNER JOIN mochi.price prc  " +
				"		 ON prd.prd_id = prc.prd_id " +
				"		  " +
				"		 INNER JOIN mochi.currency curr  " +
				"		 ON prc.ccy_id = curr.ccy_id  " +
				"		  " +
				"		 INNER JOIN mochi.price_type pt  " +
				"		 ON prc.prc_typ_id = pt.prc_typ_id " +

				"		 INNER JOIN mochi.product_status ps " +
				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
				"		  " +
				"		 WHERE curr.ccy_cd = 	:currency " +
				"		 AND prc_typ_cd = 		:markdownPriceCode " +
				"		 AND prd_sts_cd = 		:activeProductCode " +
				"		 )  markdown_price		  " +
				"		 ON pc.prd_id = markdown_price.prd_id " +
				" WHERE 0=0 " +
				((childrenOnly && hasCategoryId)  	? " AND cc.cat_id <> :categoryId " : "") +
				((childrenOnly && hasCategoryCd)  	? " AND cc.cat_cd <> :categoryCode " : "") +
				"GROUP BY  " +
				"	 cc.cat_id, " +
				"	 cc.cat_cd, " +
				"	 cc.cat_lvl, " +
				"	 cc.cat_prnt_id, " +
				"	 cc.cat_typ_id, " +
				"	 cc.node) " +
				" , summaries_ptb AS " +
				"( " +
				"SELECT 	 " +
				"	s1.cat_id, " +
				"	s1.cat_cd, " +
				"	s1.cat_lvl, " +
				"	s1.prnt_id, " +
				"	s1.cat_type_id, " +
				"	coalesce(s1.object_count, 0) + " +
				"	sum(coalesce(s2.object_count,0)) as object_count, " +
				"	greatest(0, s1.max_retail_price, max(s2.max_retail_price)) as max_retail_price, " +
				"	greatest(0, s1.max_markdown_price, max(s2.max_markdown_price)) as max_markdown_price, " +
				"   count(distinct s2.cat_id) as child_cat_count " +
				"FROM summaries_pta s1 " +
				"LEFT JOIN summaries_pta s2 " +
				"ON s1.node <> s2.Node and left(s2.node, length(s1.node)) = s1.node " +
				"GROUP BY " +
				"	s1.cat_id, " +
				"	s1.cat_cd, " +
				"	s1.cat_lvl, " +
				"	s1.prnt_id, " +
				"	s1.cat_type_id, " +
				"	s1.object_count, " +
				"	s1.max_retail_price, " +
				"	s1.max_markdown_price " +
				") " +
				"SELECT " +
				((maxPriceOnly) ? "MAX(s.max_markdown_price) as max_markdown_price " 
				:
				"		s.cat_id 				AS cat_id, " +
				"       s.cat_cd 				AS cat_cd, " +
				"       s.cat_lvl 				AS cat_lvl, " +	
				"		a.cat_lcl_id 			AS cat_lcl_id, "	+	
				"		s.cat_type_id 			AS cat_typ_id, 	" +
				"       ct.cat_typ_cd			AS cat_typ_cd, " +
				"       ct.cat_typ_desc 		AS cat_typ_desc, " +
				"       a.cat_desc 				AS cat_desc, " +
				"       a.lcl_cd 				AS lcl_cd, " +
				"		s.prnt_id   			AS cat_prnt_id, " +
				"		pc.cat_cd   			AS cat_prnt_cd, " +
				"		pc.cat_lvl   			AS cat_prnt_lvl, " +
				"		pc.cat_typ_id 			AS cat_prnt_typ_id, " +
				"		pct.cat_typ_cd			AS cat_prnt_typ_cd, " +
				"		pct.cat_typ_desc		AS cat_prnt_typ_desc, " +
				"		pc.cat_prnt_id 			AS cat_prnt_prnt_id, " + 
				" 		pa.cat_lcl_id 			AS cat_prnt_lcl_id, " +
				"       pa.cat_desc 			AS cat_prnt_desc, " +
				"       pa.lcl_cd 				AS cat_prnt_lcl_cd, " +
				"		a.cat_lcl_id			AS cat_lcl_id, " +
				"       a.cat_img_pth			AS cat_img_pth, " +
				"       s.object_count			AS object_count, " +
				"       s.max_retail_price		AS max_retail_price, " +
				"       s.max_markdown_price	AS max_markdown_price, " +
				"       ps.object_count			AS cat_prnt_object_count, " +
				"       ps.max_retail_price		AS cat_prnt_max_retail_price, " +
				"       ps.max_markdown_price 	AS cat_prnt_max_markdown_price, " +
				"		coalesce(cs.child_cat_count,0)		AS child_cat_count ") +

				"FROM summaries_ptb s " +

				"LEFT JOIN summaries_ptb ps " +
				"ON ps.cat_id = s.prnt_id " +

				"LEFT JOIN (" + 
				" SELECT 	prnt_id as cat_id, " +
				" 			count(distinct cat_id) as child_cat_count " +
				" FROM summaries_ptb cs " +
				" GROUP BY prnt_id" +
				") cs " +
				"ON s.cat_id = cs.cat_id " +
				
				"LEFT JOIN mochi.category_attr_lcl a " +
				"ON s.cat_id = a.cat_id " +
				"AND a.lcl_cd = :locale " +
				
				"LEFT JOIN mochi.category parent " +
				"ON s.prnt_id = parent.cat_id  " +
				
				"INNER JOIN mochi.category_type ct " +
				"ON ct.cat_typ_id = s.cat_type_id  " +
				
				((hasType) 
						? "AND ct.cat_typ_id = :typeDiscriminator "  
						: " ") +
				
				"LEFT JOIN mochi.category pc " +
				"ON pc.cat_id = s.prnt_id  " +
				
				"LEFT JOIN mochi.category_type pct " +
				"ON pc.cat_typ_id = pct.cat_typ_id  " +
				
				"LEFT JOIN mochi.category_attr_lcl pa " +
				"ON pc.cat_id = pa.cat_id " +
				"AND pa.lcl_cd = :locale " +
				
								
				"WHERE 0=0 " +
				"AND case " +
				"	 when :parentCategoryCode = '-1' " +
				"  then '0' " +
				"  else parent.cat_cd" +
				"	 end = " +
				"	 case" +
				"  when :parentCategoryCode = '-1' " +
				"  then '0' " +
				"  else :parentCategoryCode" +
				"  end" +
				//((!withChildren && hasCategories) ? 	" 	AND s.cat_cd in 	:categoryCodes " : "") +
				((!childrenOnly && hasCategoryDesc) ? 	" 	AND a.cat_desc = 	:categoryDesc " : "") +
				((!childrenOnly && hasCategoryId) ? 	" 	AND s.cat_id = 		:categoryId " : "") +
				((!childrenOnly && hasCategoryCd) ? 	" 	AND s.cat_cd = 		:categoryCode " : ""));
			
		return sql;
	}



}
