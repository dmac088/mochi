
package io.nzbee.entity.category;

import java.util.ArrayList;
import java.util.HashSet;
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
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity_;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.product.CategoryProductEntity_;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.ProductEntity_;

@Component(value="categoryEntityPostgresDao")
public class CategoryDaoPostgresImpl implements ICategoryDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	public static final String CACHE_NAME = "categoryCache";

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes,
			Set<String> tagCodes) {
		LOGGER.debug("call CategoryDaoPostgresImpl.getMaxPrice parameters : locale = {}, currency = {}, category code = {}, category codes = {}, brand codes = {}, tag codes = {}", locale, currency, categoryCode, StringUtils.join(categoryCodes), StringUtils.join(brandCodes), StringUtils.join(tagCodes));
		
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
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("retailPriceCode", Constants.retailPriceCode)
				 .setParameter("markdownPriceCode", Constants.markdownPriceCode);
				 
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
		
		Optional<Object> result = Optional.ofNullable(query.getSingleResult());
		
		return result.isPresent()
			   ? new Double(result.get().toString())
			   : new Double(0);
	}
	
	
	@Override
	public Set<CategoryEntity> findAll() {
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll ");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
		
		Root<CategoryEntity> root = cq.from(CategoryEntity.class);
		
		List<Predicate> conditions = new ArrayList<Predicate>();

		TypedQuery<CategoryEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return query.getResultStream().collect(Collectors.toSet());
	}
	
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME, key="#categoryCode")
			}
	)
	public Optional<CategoryEntity> findByCode(String categoryCode) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findByCode with parameter {} ", categoryCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
		
		Root<CategoryEntity> root = cq.from(CategoryEntity.class);
		
		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(
				cb.equal(root.get(CategoryEntity_.CATEGORY_CODE), categoryCode)
		);
		
		TypedQuery<CategoryEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		try {
			CategoryEntity p = query.getSingleResult();
			return Optional.ofNullable(p);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
		
	}

	
	@Override
	public <T> Set<CategoryDTO> findAllByType(String locale, Class<T> cls) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findByCodeAndType parameters : {}, {}", locale, cls.getSimpleName());
		
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
															 true))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("typeDiscriminator", Long.parseLong(cls.getAnnotation(DiscriminatorValue.class).value()));
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = query.getResultList();
		
		return results.stream().map(c -> this.objectToDTO(c, locale)).collect(Collectors.toSet());
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public Set<CategoryDTO> findAll(String locale) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : {}", locale);
		
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
															 false))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		Set<CategoryDTO> results = new HashSet<CategoryDTO>(query.getResultList());
		
		return results;
	}
	


	@SuppressWarnings("deprecation")
	@Override
	public Set<CategoryDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes,
			Set<String> tagCodes, Double maxPrice) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : locale = {}, currency = {}, category code = {}, category codes = {}, brand codes = {}, tag codes = {}, max price = {}", locale, currency, categoryCode, StringUtils.join(brandCodes), StringUtils.join(tagCodes));
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(false, 
															 !brandCodes.isEmpty(),
															 !tagCodes.isEmpty(),
															 !(maxPrice == null),
															 true,
															 false,
															 false,
															 false,
															 true,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		query.setParameter("categoryCode", categoryCode);
		
		if(!brandCodes.isEmpty()) {
			 query.setParameter("brandCodes", brandCodes);
		}
			
		if(!tagCodes.isEmpty()) {
			 query.setParameter("tagCodes", tagCodes);
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
			query.setParameter("currency", currency);
			query.setParameter("markdownPriceCode", Constants.markdownPriceCode);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		Set<CategoryDTO> results = new HashSet<CategoryDTO>(query.getResultList());
		
		return results;
	}


	@SuppressWarnings("deprecation")
	@Override
	public Set<CategoryDTO> findAll(String locale, Set<String> categoryCodes) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : {}, {}, {}", locale, StringUtil.join(categoryCodes, ','));
		
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
															 false))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		if(!categoryCodes.isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		Set<CategoryDTO> results = new HashSet<CategoryDTO>(query.getResultList());
		
		return results;
	}
	
	
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME, key="{#locale, #categoryId}")
			}
	)
	public Optional<CategoryDTO> findById(String locale, Long categoryId) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findById parameters : {}, {}, {}", locale, categoryId);
		
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
															 false))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("categoryId", categoryId)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		

		try {
			Object[] c = (Object[])query.getSingleResult();
			
			CategoryDTO category = this.objectToDTO(c, locale);
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME, key="{#locale, #categoryDesc}")
			}
	)
	public Optional<CategoryDTO> findByDesc(String locale, String categoryDesc) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findByDesc parameters : {}, {}, {}", locale, categoryDesc);
		
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
															 false))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("categoryDesc", categoryDesc)
				 .setParameter("activeProductCode", Constants.activeSKUCode);

		try {
			Object[] c = (Object[])query.getSingleResult();
			
			CategoryDTO category = this.objectToDTO(c, locale);
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}

	}

	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME, key="{#locale, #categoryCode}")
			}
		)
	public Optional<CategoryDTO> findByCode(String locale, String categoryCode) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findByCode parameters : {}, {}, {}", locale, categoryCode);
		
		Session session = em.unwrap(Session.class);
		
		final List<String> categoryCodes = new ArrayList<String>();
		
		Query query = session.createNativeQuery(constructSQL(!categoryCodes.isEmpty(),
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 true,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("categoryCode", categoryCode)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode);

		try {
			Object[] c = (Object[])query.getSingleResult();
			
			CategoryDTO category = this.objectToDTO(c, locale);
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	

	@Override
	public Set<CategoryEntity> findByParent(String parentCategoryCode, String locale) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findByParent parameters : {}, {}", parentCategoryCode, locale);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
		
		Root<CategoryEntity> root = cq.from(CategoryEntity.class);
		
		Join<CategoryEntity, CategoryAttributeEntity> categoryAttribute = root.join(CategoryEntity_.attributes);
		Join<CategoryEntity, CategoryEntity> parent = root.join(CategoryEntity_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
	
		if(!(parentCategoryCode == null)) {
			conditions.add(cb.equal(parent.get(CategoryEntity_.categoryCode), parentCategoryCode));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttributeEntity_.lclCd), locale));
		
		TypedQuery<CategoryEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultStream().collect(Collectors.toSet());
	}
	
	
	@Override
	public Set<CategoryEntity> findByLevel(String locale, Long level) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findByLevel parameters : {}, {}", locale, level);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
		
		Root<CategoryEntity> root = cq.from(CategoryEntity.class);
		
		Join<CategoryEntity, CategoryAttributeEntity> categoryAttribute = root.join(CategoryEntity_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		if(!(level == null)) {
			conditions.add(cb.equal(root.get(CategoryEntity_.categoryLevel), level));
		}
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttributeEntity_.lclCd), locale));
		
		TypedQuery<CategoryEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultStream().collect(Collectors.toSet());
	}
	
	@Override
	public Set<CategoryEntity> findAllByProductCode(String locale, String productCode) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findAllByProductCode parameters : {}, {}, {}", locale, productCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
		
		Root<CategoryProductEntity> root = cq.from(CategoryProductEntity.class);
		Join<CategoryProductEntity, CategoryAttributeEntity> categoryAttribute = root.join(CategoryProductEntity_.attributes);
		Join<CategoryProductEntity, ProductEntity> product = root.join(CategoryProductEntity_.products);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(product.get(ProductEntity_.productUPC), productCode));
		
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttributeEntity_.lclCd), locale));
		
		TypedQuery<CategoryEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultStream().collect(Collectors.toSet());
	}

//	@Override
//	public Category objectToEntity(Tuple t, String locale, String currency) {
//		Category c = objectToEntity(t, locale);
//		c.setCurrency(currency);
//		return c;
//	}
//	
//	@Override
//	public Category objectToEntity(Object[] o, String locale, String currency) {
//		Category c = objectToEntity(o, locale);
//		c.setCurrency(currency);
//		return c;
//	}
//	
//	@Override
//	public Category objectToEntity(Object[] o, String locale) {
//		Category category = (Category) o[0];
//		category.setCategoryAttribute(((CategoryAttributeEntity) o[1]));
//		category.setCategoryType((CategoryType) o[2]);
//		if(category instanceof CategoryProduct) {
//			((CategoryProduct) category).setHasParent(o[3] != null);
//			if(((CategoryProduct) category).hasParent()) {
//				//we have a parent
//				Category parentCategory = (Category) o[3];
//				parentCategory.setCategoryAttribute(((CategoryAttributeEntity) o[4]));
//				parentCategory.setCategoryType((CategoryType) o[5]);
//				category.setParent(parentCategory);
//			}
//		}
//		category.setObjectCount(((BigDecimal)o[6]).intValue());
//		category.setChildCount(((BigInteger)o[7]).longValue());
//		category.setLocale(locale);
//		
//		return category;
//	}
//
//	@Override
//	public Category objectToEntity(Tuple t, String locale) {
//		Category c =  t.get("categoryType").toString().equals("PRD01") 
//				? new CategoryProduct()
//				: new CategoryBrand();
//	
//		c.setCategoryCode(t.get("categoryCode").toString());
//		CategoryAttributeEntity ca = new CategoryAttributeEntity();
//		ca.setCategoryDesc(t.get("categoryDesc").toString());
//		c.setCategoryAttribute(ca);
//		
//		c.setLocale(locale);
//		
//		return c;
//	}
	
	@Override
	public void save(CategoryEntity t) {
		em.persist(t);
	}

	@Override
	public void update(CategoryEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryEntity t) {
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
		String sql = "WITH RECURSIVE descendants AS " +
				"( " +
				"  SELECT 	t.cat_id,  " +
				"			t.cat_cd, " +
				"			t.cat_lvl, " +
				"			t.cat_prnt_cd,  " +
				"			t.cat_prnt_id,  " +
				"			t.cat_typ_id, " +
				"			cast('/' || cast(t.cat_id as text) || '/' as text) node " +
				"  FROM mochi.category AS t " +
				
				"  LEFT JOIN mochi.category_attr_lcl a " +
				"  ON t.cat_id = a.cat_id " +
				"  AND a.lcl_cd = :locale " +
				"  WHERE 0=0 " +
				((hasCategoryDesc)  ? " AND a.cat_desc 	= :categoryDesc " 	: "") + 
				((hasCategoryId)  	? " AND t.cat_id 	= :categoryId" 		: "") +
				((hasCategoryCd  	? " AND t.cat_cd 	= :categoryCode" 	: "") +
				((!hasCategoryCd 	&& !hasCategoryDesc 	&& !hasCategoryId) 		? " AND cat_prnt_id IS NULL " : "") +
				"  UNION ALL " +
				"  SELECT 	t.cat_id,  " +
				"			t.cat_cd,  " +
				"			t.cat_lvl, " +
				"			t.cat_prnt_cd,  " +
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
				"    cc.cat_prnt_cd 			AS cat_prnt_cd, " +
				"    cc.cat_prnt_id 			AS cat_prnt_id, " +
				"    cc.cat_typ_id 				AS cat_type_id, " +
				"    cc.node, " +
				"    COUNT(DISTINCT prd.upc_cd) 	AS object_count " +
				((maxPriceOnly) ? ",    MAX(markdown_price.prc_val) 	AS max_markdown_price,  " +
								 "      MAX(retail_price.prc_val) 		AS max_retail_price " 
							   : "") + 
				"FROM descendants cc " +
				"LEFT JOIN mochi.product_category pc " +
				"ON cc.cat_id = pc.cat_id " +
				
				"LEFT JOIN 	( " +
				"		 SELECT prd.prd_id,  " +
				"			prd.bnd_id, " +
				"			upc_cd " +
				"		 FROM mochi.product prd " +
				"		  " +
				"		 INNER JOIN mochi.product_status ps " +
				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
				"		  " +
								
				" WHERE prd_sts_cd = :activeProductCode " +
				") prd  " +
				"		 ON pc.prd_id = prd.prd_id " +
				
				((hasBrands) ?
				"INNER JOIN mochi.brand b " +
				"		ON prd.bnd_id = b.bnd_id "
				: "") +
				
				((hasTags) ?
				"INNER JOIN mochi.product_tag pt" + 
				"		ON prd.prd_id = pt.prd_id " + 
				
				
				"INNER JOIN mochi.tag t " + 
				"		ON pt.tag_id = t.tag_id " 
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
				
				((maxPriceOnly)
				? "LEFT JOIN 	( " +
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
				"		 ON pc.prd_id = markdown_price.prd_id " 
				: "") +
				" WHERE 0=0 " +
				((childrenOnly && hasCategoryId)  	? " AND cc.cat_id	<> :categoryId " : "") +
				((childrenOnly && hasCategoryCd)  	? " AND cc.cat_cd 	<> :categoryCode " : "") +
				((hasBrands)   						? " AND b.bnd_cd 	in :brandCodes " : "") +
				((hasCategories) 					? " AND cc.cat_cd 	in :categoryCodes " : "") +
				((hasTags) 							? " AND t.tag_cd 	in :tagCodes " : "") +
				"GROUP BY  " +
				"	 cc.cat_id, " +
				"	 cc.cat_cd, " +
				"	 cc.cat_lvl, " +
				"	 cc.cat_prnt_cd, " +
				"	 cc.cat_prnt_id, " +
				"	 cc.cat_typ_id, " +
				"	 cc.node) " +
				" , summaries_ptb AS " +
				"( " +
				"SELECT 	 " +
				"	s1.cat_id, " +
				"	s1.cat_cd, " +
				"	s1.cat_lvl, " +
				"	s1.cat_prnt_cd, " +
				"	s1.cat_prnt_id, " +
				"	s1.cat_type_id, " +
				"	coalesce(s1.object_count, 0) + " +
				"	sum(coalesce(s2.object_count,0)) as object_count, " +
				((maxPriceOnly) 
						? 	"	greatest(0, s1.max_retail_price, max(s2.max_retail_price)) as max_retail_price, " +
							"	greatest(0, s1.max_markdown_price, max(s2.max_markdown_price)) as max_markdown_price, " 
						:   "") +
				"   count(distinct s2.cat_id) as child_cat_count " +
				"FROM summaries_pta s1 " +
				"LEFT JOIN summaries_pta s2 " +
				"ON s1.node <> s2.Node and left(s2.node, length(s1.node)) = s1.node " +
				"GROUP BY " +
				"	s1.cat_id, " +
				"	s1.cat_cd, " +
				"	s1.cat_lvl, " +
				"	s1.cat_prnt_cd, " +
				"	s1.cat_prnt_id, " +
				"	s1.cat_type_id, " +
				"	s1.object_count " +
				((maxPriceOnly) 
					? "	,s1.max_retail_price, " +
					 "	s1.max_markdown_price " 
					: "") +
				") " +
				"SELECT " +
				((maxPriceOnly) 
				? "MAX(s.max_markdown_price) as max_markdown_price " 
				: "		s.cat_id 				AS cat_id, " +
				"       s.cat_cd 				AS cat_cd, " +
				"       s.cat_lvl 				AS cat_lvl, " +	
				"		a.cat_lcl_id 			AS cat_lcl_id, "	+	
				"		s.cat_type_id 			AS cat_typ_id, 	" +
				"       ct.cat_typ_cd			AS cat_typ_cd, " +
				"       ct.cat_typ_desc 		AS cat_typ_desc, " +
				"       a.cat_desc 				AS cat_desc, " +
				"       a.lcl_cd 				AS lcl_cd, " +
				"		pc.cat_id   			AS cat_prnt_id, " +
				"		pc.cat_cd   			AS cat_prnt_cd, " +
				"		pc.cat_lvl   			AS cat_prnt_lvl, " +
				"		pc.cat_typ_id 			AS cat_prnt_typ_id, " +
				"		pct.cat_typ_cd			AS cat_prnt_typ_cd, " +
				"		pct.cat_typ_desc		AS cat_prnt_typ_desc, " +
				"		ppc.cat_id 				AS cat_prnt_prnt_id, " +
				"		pc.cat_prnt_cd 			AS cat_prnt_prnt_cd, " + 
				" 		pa.cat_lcl_id 			AS cat_prnt_lcl_id, " +
				"       pa.cat_desc 			AS cat_prnt_desc, " +
				"       pa.lcl_cd 				AS cat_prnt_lcl_cd, " +
				"       a.cat_img_pth			AS cat_img_pth, " +
				"       s.object_count			AS object_count, " +
				"		coalesce(cs.child_cat_count,0)		AS child_cat_count ") +

				"FROM summaries_ptb s " +

				"LEFT JOIN summaries_ptb ps " +
				"ON ps.cat_id = s.cat_prnt_id " +

				"LEFT JOIN (" + 
				" SELECT 	cat_prnt_cd as cat_cd, " +
				" 			count(distinct cat_id) as child_cat_count " +
				" FROM summaries_ptb cs " +
				" GROUP BY cat_prnt_cd" +
				") cs " +
				"ON s.cat_cd = cs.cat_cd " +
				
				"LEFT JOIN mochi.category_attr_lcl a " +
				"ON s.cat_id = a.cat_id " +
				"AND a.lcl_cd = :locale " +
			
				"INNER JOIN mochi.category_type ct " +
				"ON ct.cat_typ_id = s.cat_type_id  " +
				
				((hasType) 
				? "AND ct.cat_typ_id = :typeDiscriminator "  
				: " ") +
				
				"LEFT JOIN mochi.category pc " +
				"ON pc.cat_id = s.cat_prnt_id  " +
				
				"LEFT JOIN mochi.category ppc " +
				"ON ppc.cat_id = pc.cat_prnt_id  " +
				
				"LEFT JOIN mochi.category_type pct " +
				"ON pc.cat_typ_id = pct.cat_typ_id  " +
				
				"LEFT JOIN mochi.category_attr_lcl pa " +
				"ON pc.cat_id = pa.cat_id " +
				"AND pa.lcl_cd = :locale " +
				
				"WHERE 0=0 " +
				"AND case " +
				"	 when :parentCategoryCode = '-1' " +
				"  then '0' " +
				"  else pc.cat_cd" +
				"	 end = " +
				"	 case" +
				"  when :parentCategoryCode = '-1' " +
				"  then '0' " +
				"  else :parentCategoryCode" +
				"  end" +
				((!maxPriceOnly && !childrenOnly && hasCategoryDesc) ? 	" 	AND a.cat_desc = 	:categoryDesc " : "") +
				((!maxPriceOnly && !childrenOnly && hasCategoryId) ? 	" 	AND s.cat_id = 		:categoryId " : "") +
				((!maxPriceOnly && !childrenOnly && hasCategoryCd) ? 	" 	AND s.cat_cd = 		:categoryCode " : ""));
			
		return sql;
	}

	@Override
	public CategoryDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CategoryEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}



	

}
