
package io.nzbee.entity.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.ArrayUtils;
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
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity_;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.product.CategoryProductEntity_;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.ProductEntity_;

@Component(value="categoryEntityPostgresDao")
public class CategoryDaoPostgresImpl implements ICategoryDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private ICategoryRepository categoryRepository;


	@Override
	public Optional<CategoryEntity> findById(Long id) {
		return categoryRepository.findById(id);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Caching(
			put = {
					@CachePut(value = CategoryServiceImpl.CACHE_NAME, key="#locale + \", \" + #categoryId.toString()")
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
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("categoryId", categoryId)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		

		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		try {
			CategoryDTO category = (CategoryDTO) query.getSingleResult();
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Caching(
			put = {
					@CachePut(value = CategoryServiceImpl.CACHE_NAME, key="#locale + \", \" + #categoryCode")
			}
		)
	public Optional<CategoryDTO> findByCode(String locale, String categoryCode) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findByCode parameters : {}, {}", locale, categoryCode);
		
		Session session = em.unwrap(Session.class);
		
		final List<String> categoryCodes = new ArrayList<String>();
		categoryCodes.add(categoryCode);
		
		Query query = session.createNativeQuery(constructSQL(!categoryCodes.isEmpty(),
															 false,
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
				 .setParameter("categoryCode", categoryCode)
				 .setParameter("categoryCodes", categoryCodes)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode);

		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		try {
			CategoryDTO category = (CategoryDTO) query.getSingleResult();
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<CategoryEntity> findByCode(String categoryCode) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findByCode with parameter {} ", categoryCode);
		
		return categoryRepository.findByCategoryCode(categoryCode);
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	@Caching(
			put = {
					@CachePut(value = CategoryServiceImpl.CACHE_NAME, key="#locale + \", \" + #categoryDesc")
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
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("categoryDesc", categoryDesc)
				 .setParameter("activeProductCode", Constants.activeSKUCode);

		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		try {
			CategoryDTO category = (CategoryDTO) query.getSingleResult();
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}

	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Caching(
			put = {
					@CachePut(value = CategoryServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + #maxPrice")
			}
	)
	public List<CategoryDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes,
			StringCollectionWrapper tagCodes, Double maxPrice) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : locale = {}, currency = {}, category code = {}, category codes = {}, brand codes = {}, tag codes = {}, max price = {}", locale, currency, categoryCode, StringUtils.join(brandCodes.getCodes()), StringUtils.join(tagCodes.getCodes()));
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(false, 
															 !brandCodes.getCodes().isEmpty(),
															 !tagCodes.getCodes().isEmpty(),
															 !(maxPrice == null),
															 true,
															 false,
															 false,
															 false,
															 true,
															 false,
															 true))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		query.setParameter("categoryCode", categoryCode);
		
		if(!brandCodes.getCodes().isEmpty()) {
			 query.setParameter("brandCodes", brandCodes.getCodes());
		}
			
		if(!tagCodes.getCodes().isEmpty()) {
			 query.setParameter("tagCodes", tagCodes.getCodes());
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
			query.setParameter("currency", currency);
			query.setParameter("markdownPriceCode", Constants.markdownPriceCode);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		return query.getResultList();
	}
	
	
	@Override
	@Caching(
			put = {
					@CachePut(value = CategoryServiceImpl.CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #categoryCode + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey()")
			}
	)
	public Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes,
			StringCollectionWrapper tagCodes) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.getMaxPrice parameters : locale = {}, currency = {}, category code = {}, category codes = {}, brand codes = {}, tag codes = {}", locale, currency, categoryCode, StringUtils.join(categoryCodes.getCodes()), StringUtils.join(brandCodes.getCodes()), StringUtils.join(tagCodes.getCodes()));
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(!categoryCodes.getCodes().isEmpty(), 
															 !brandCodes.getCodes().isEmpty(),
															 !tagCodes.getCodes().isEmpty(),
															 false,
															 false,
															 true,
															 false,
															 false,
															 true,
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("currency", currency)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("retailPriceCode", Constants.retailPriceCode)
				 .setParameter("markdownPriceCode", Constants.markdownPriceCode);
				 
		if(!categoryCodes.getCodes().isEmpty()) {
			 query.setParameter("categoryCodes", categoryCodes.getCodes());
		}
		
		if(!brandCodes.getCodes().isEmpty()) {
			 query.setParameter("brandCodes", brandCodes.getCodes());
		}
			
		if(!tagCodes.getCodes().isEmpty()) {
			 query.setParameter("tagCodes", tagCodes.getCodes());
		}
		
		query.setParameter("categoryCode", categoryCode);
		
		Optional<Object> result = Optional.ofNullable(query.getSingleResult());
		
		return result.isPresent()
			   ? new Double(result.get().toString())
			   : new Double(0);
	}
	
	
	@Override
	public List<CategoryEntity> findAll() {
		
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
		
		return query.getResultList();
	}
	
	@Override
	public List<CategoryEntity> findAll(Set<String> codes) {
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll with parameter {} ", ArrayUtils.toString(codes));
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
		
		Root<CategoryEntity> root = cq.from(CategoryEntity.class);
		
		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(
				root.get(CategoryEntity_.CATEGORY_CODE).in(codes)
		);
		
		TypedQuery<CategoryEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
	
		return query.getResultList();
	}
	
	

	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public <T> List<CategoryDTO> findAllByType(String locale, String rootCategory, Class<T> cls) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findAllByType parameters : {}, {}, {}", locale, cls.getSimpleName(), cls.getAnnotation(DiscriminatorValue.class).value());
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(false, 
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 true, 
															 true,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("categoryCode", rootCategory)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("typeDiscriminator", Long.parseLong(cls.getAnnotation(DiscriminatorValue.class).value()));
		
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		return query.getResultList();
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<CategoryDTO> findAll(String locale) {
		
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
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("parentCategoryCode", "-1")
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		return query.getResultList();
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Caching(
			put = {
					@CachePut(value = CategoryServiceImpl.CACHE_NAME, key="#locale + \", \" + #codes.getCacheKey()")
			}
	)
	public List<CategoryDTO> findAll(String locale, StringCollectionWrapper codes) {
		
		LOGGER.debug("call CategoryDaoPostgresImpl.findAll parameters : {}, {}, {}", locale, StringUtil.join(codes.getCodes(), ','));
		
		Session session = em.unwrap(Session.class);

		Query query = session.createNativeQuery(constructSQL(!codes.getCodes().isEmpty(),
															 false,
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
		
		if(!codes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", codes.getCodes());
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new CategoryDTOResultTransformer());
		
		return query.getResultList();
	}
	
	
	@Override
	public List<CategoryEntity> findByParent(String parentCategoryCode, String locale) {
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
		
		return query.getResultList();
	}
	
	
	@Override
	public List<CategoryEntity> findByLevel(String locale, Long level) {
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
		
		return query.getResultList();
	}
	
	@Override
	public List<CategoryEntity> findAllByProductCode(String locale, String productCode) {
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
		
		return query.getResultList();
	}

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
				boolean hasType,
				boolean restrictLevel
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
				((hasCategoryId)  	? " AND t.cat_id 	= :categoryId " 	: "") +
				((hasCategoryCd  	? " AND t.cat_cd 	= :categoryCode " 	: "") +
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
				"  ON t.cat_prnt_id = d.cat_id )" +
				((restrictLevel) 
				? 	" , rootcategory AS 							" + 
					"(												" + 
					"          SELECT    cat_lvl					" +
					"          FROM      mochi.category t			" +
					"          LEFT JOIN mochi.category_attr_lcl a	" + 
					"          ON        t.cat_id = a.cat_id 		" +
					"          AND       a.lcl_cd = :locale			"  +
					"          WHERE     0=0						"  +
					((hasCategoryDesc)  ? " AND a.cat_desc 	= :categoryDesc " 	: "") + 
					((hasCategoryId)  	? " AND t.cat_id 	= :categoryId " 	: "") +
					((hasCategoryCd)  	? " AND t.cat_cd 	= :categoryCode " 	: "") + ")"  
				 : "") +
				 ", categories AS ( " + 
				
		        "SELECT    	 COALESCE(s2.cat_typ_id,s1.cat_typ_id)   	AS cat_typ_id, 			" +
		        "	         COALESCE(s2.cat_id,s1.cat_id)           	AS cat_id, 				" +
		        "			 s2.cat_cd        					  		AS cat_cd,				" + 
		        "			 COALESCE(s1.cat_id,s2.cat_id)				AS display_cat_id,		" +
		        "			 COALESCE(s1.cat_cd,s2.cat_cd)				AS display_cat_cd,		" +
		        "			 COALESCE(s1.cat_lvl,s2.cat_lvl)			AS display_cat_lvl		" +
		        
		        "FROM      descendants s1 														" +
		        "LEFT JOIN descendants s2 														" +
		        "ON        s1.node <> s2.node 													" + 
		        "AND       LEFT(s2.node, length(s1.node)) = s1.node 							" + 
		        "WHERE     0=0 																	" +
		        ((hasCategories) 
						? " AND s1.cat_cd IN (:categoryCodes) 									" 
						: "") 																	  +
						
		        "GROUP BY  	 COALESCE(s2.cat_typ_id,s1.cat_typ_id), 							" +
		        "	         COALESCE(s2.cat_id,s1.cat_id), 									" +
		        "			 s2.cat_cd,															" +									
		        "	         COALESCE(s1.cat_id,s2.cat_id), 									" +
		        "	         COALESCE(s1.cat_cd,s2.cat_cd), 									" +
		        "	         COALESCE(s1.cat_lvl,s2.cat_lvl) 									" +
				") 					" + 
				", summaries AS ( " +
				"select " +
                "cc.display_cat_id                  	AS cat_id, 								" +
                "cc.display_cat_cd                  	AS cat_cd, 								" +
                "cc.display_cat_lvl         		   	AS cat_lvl, 							" +
                "cc.cat_typ_id              		   	AS cat_typ_id, 							" +
				"COUNT(DISTINCT cc.cat_cd)		   		AS child_cat_count,						" +
                "COUNT(DISTINCT prd.upc_cd) 		   	AS object_count 						" +
				((maxPriceOnly) ? ",    MAX(markdown_price.prc_val) 	AS max_markdown_price,  " +
								 "      MAX(retail_price.prc_val) 		AS max_retail_price 	"  
							   : "") + 
				"FROM categories cc 															" +
							   
				"LEFT JOIN mochi.product_category pc 											" +
				"ON cc.cat_id = pc.cat_id 														" +
				
				"LEFT JOIN 	( 																	" +
				"		 SELECT prd.prd_id,  													" +
				"				prd.bnd_id, 													" +
				"				upc_cd 															" +
				"		 FROM mochi.product prd 												" +
				"		  																		" +
				"		 INNER JOIN mochi.product_status ps 									" +
				"		 ON prd.prd_sts_id = ps.prd_sts_id 										" +
				"		  " +
				" WHERE prd_sts_cd = :activeProductCode 										" +
				") prd  																		" +
				" ON pc.prd_id = prd.prd_id 													" +
				
				((hasBrands) ?
				" INNER JOIN mochi.brand b " +
				" ON prd.bnd_id = b.bnd_id "
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

				"		 INNER JOIN mochi.price_type pt  " +
				"		 ON prc.prc_typ_id = pt.prc_typ_id " +

				"		 INNER JOIN mochi.product_status ps " +
				"		 ON prd.prd_sts_id = ps.prd_sts_id " +
 
				"		 WHERE curr.ccy_cd = 	:currency " +
				"		 AND prc_typ_cd = 		:retailPriceCode " +
				"		 AND prd_sts_cd = 		:activeProductCode " +
				"		 ) retail_price " +
				"		 ON pc.prd_id = retail_price.prd_id " +

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
				((childrenOnly && hasCategoryId)  	? " AND cc.display_cat_id	<> :categoryId " 	: "") +
				((childrenOnly && hasCategoryCd)  	? " AND cc.display_cat_cd 	<> :categoryCode " 	: "") +
				((hasBrands)   						? " AND b.bnd_cd 			in :brandCodes " 	: "") +
				((hasTags) 							? " AND t.tag_cd 			in :tagCodes " 		: "") +
				"GROUP BY  																	" +
				"cc.display_cat_id,															" +
                "cc.display_cat_cd,															" +
                "cc.display_cat_lvl,														" +
                "cc.cat_typ_id																" +
				" ) 																		" +
				"SELECT 																	" +
				((maxPriceOnly) 
				? "		MAX(s.max_markdown_price) as max_markdown_price " 
				: "		s.cat_id 						AS cat_id, " +
				"       s.cat_cd 						AS cat_cd, " +
				"       s.cat_lvl 						AS cat_lvl, " +	
				"		a.cat_lcl_id 					AS cat_lcl_id, "	+	
				"		s.cat_typ_id 					AS cat_typ_id, 	" +
				"       ct.cat_typ_cd					AS cat_typ_cd, " +
				"       ct.cat_typ_desc 				AS cat_typ_desc, " +
				"       a.cat_desc 						AS cat_desc, " +
				"       a.lcl_cd 						AS lcl_cd, " +
				"		pc.cat_id   					AS cat_prnt_id, " +
				"		pc.cat_cd   					AS cat_prnt_cd, " +
				"		pc.cat_lvl   					AS cat_prnt_lvl, " +
				"		pc.cat_typ_id 					AS cat_prnt_typ_id, " +
				"		pct.cat_typ_cd					AS cat_prnt_typ_cd, " +
				"		pct.cat_typ_desc				AS cat_prnt_typ_desc, " +
				"		ppc.cat_id 						AS cat_prnt_prnt_id, " +
				"		pc.cat_prnt_cd 					AS cat_prnt_prnt_cd, " + 
				" 		pa.cat_lcl_id 					AS cat_prnt_lcl_id, " +
				"       pa.cat_desc 					AS cat_prnt_desc, " +
				"       pa.lcl_cd 						AS cat_prnt_lcl_cd, " +
				"       a.cat_img_pth					AS cat_img_pth, " +
				"       s.object_count					AS object_count, " +
				"		coalesce(s.child_cat_count,0)	AS child_cat_count ") +

				"FROM summaries s " +
				
				((restrictLevel) 
				? "JOIN rootcategory rc " + 
				  "ON s.cat_lvl <= rc.cat_lvl + 1 "
				: "") +
				
				"LEFT JOIN mochi.category_attr_lcl a " +
				"ON s.cat_id = a.cat_id " +
				"AND a.lcl_cd = :locale " +
			
				"INNER JOIN mochi.category_type ct " +
				"ON ct.cat_typ_id = s.cat_typ_id  " +
				
				((hasType) 
				? "AND ct.cat_typ_id = :typeDiscriminator "  
				: " ") +
				
				"INNER JOIN mochi.category c " +
				"ON 		   s.cat_id = c.cat_id " +
				
				"LEFT JOIN  mochi.category pc " +
				"ON         pc.cat_id = c.cat_prnt_id " +
				
				"LEFT JOIN  mochi.category ppc " +
				"ON         ppc.cat_id = pc.cat_prnt_id " +
				
				"LEFT JOIN  mochi.category_type pct " +
				"ON         pc.cat_typ_id = pct.cat_typ_id " +
				
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
				"  end " +
				((!maxPriceOnly && !childrenOnly && hasCategoryDesc) ? 	" 	AND a.cat_desc = 	:categoryDesc " : "") +
				((!maxPriceOnly && !childrenOnly && hasCategoryId) ? 	" 	AND s.cat_id = 		:categoryId " : "") +
			//	((!maxPriceOnly && !childrenOnly && hasCategoryCd) ? 	" 	AND s.cat_cd = 		:categoryCode " : "") + 
				((!maxPriceOnly)  ? "ORDER BY lower(a.cat_desc) ASC " : ""));
			
		return sql;
	}

}
