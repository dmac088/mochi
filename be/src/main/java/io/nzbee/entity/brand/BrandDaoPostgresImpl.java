package io.nzbee.entity.brand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
import io.nzbee.entity.brand.BrandEntity_;

@Component
public class BrandDaoPostgresImpl  implements IBrandDao { 
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	private static final String CACHE_NAME = "brandCache";

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@SuppressWarnings("deprecation")
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME, key="#locale + \", \" + #id.toString()")
			}
	)
	public Optional<BrandDTO> findById(String locale, Long id) {
		LOGGER.debug("call BrandDaoImpl.findById parameters : {}, {}", locale, id);
		
		Session session = em.unwrap(Session.class);
		
		List<Long> lbid = Arrays.asList(id);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 true))
				 .setParameter("categoryCode", Constants.primaryRootCategoryCode)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("brandIds", lbid);
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new BrandDTOResultTransformer());
		
		try {
			BrandDTO result = (BrandDTO) query.getSingleResult();
			return Optional.ofNullable(result);
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME, key="#code")
			}
	)
	public Optional<BrandEntity> findByCode(String code) {
		LOGGER.debug("call BrandDaoImpl.findByCode parameters : {}", code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<BrandEntity> cq = cb.createQuery(BrandEntity.class);
		
		Root<BrandEntity> root = cq.from(BrandEntity.class);

		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(
				cb.equal(root.get(BrandEntity_.BRAND_CODE), code)
		);
		
		TypedQuery<BrandEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		try {
			BrandEntity brand = query.getSingleResult();
			return Optional.ofNullable(brand);
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME, key="{#locale, #code}")
			}
	)
	public Optional<BrandDTO> findByCode(String locale, String code) {
		LOGGER.debug("call BrandDaoImpl.findByCode parameters : {}, {}, {}", locale, code);
		
		Session session = em.unwrap(Session.class);
		
		List<String> lbc = Arrays.asList(code);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 true,
															 false,
															 false))
				 .setParameter("categoryCode", Constants.primaryRootCategoryCode)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("brandCodes", lbc);
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new BrandDTOResultTransformer());
		
		try {
			BrandDTO result = (BrandDTO) query.getSingleResult();
			return Optional.ofNullable(result);
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME, key="{#locale, #desc}")
			}
	)
	public Optional<BrandDTO> findByDesc(String locale, String desc) {
		LOGGER.debug("call BrandDaoImpl.findByDesc parameters : {}, {}", locale, desc);
		
		Session session = em.unwrap(Session.class);
		
		List<String> lbd = Arrays.asList(desc);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 false,
															 true,
															 false))
				 .setParameter("categoryCode", Constants.primaryRootCategoryCode)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("brandDescriptions", lbd);
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new BrandDTOResultTransformer());
		
		try {
			BrandDTO result = (BrandDTO) query.getSingleResult();
			return Optional.ofNullable(result);
		} catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME + "Other", key="{#locale, #codes}")
			}
	)
	public List<BrandDTO> findAll(String locale, Set<String> codes) {
		LOGGER.debug("call BrandDaoImpl.findAll parameters : {}, {}, {}", locale, StringUtil.join(codes, ','));
		
		Session session = em.unwrap(Session.class);
		
		List<String> lbc = codes.stream().collect(Collectors.toList());
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 !codes.isEmpty(),
															 false,
															 false))
				 .setParameter("categoryCode", Constants.primaryRootCategoryCode)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		if(!codes.isEmpty()) {
			query.setParameter("brandCodes", lbc);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new BrandDTOResultTransformer());
		
		List<BrandDTO> results = query.getResultList();
		
		return results;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<BrandDTO> findAllByCategory(String locale, String categoryCode) {
		LOGGER.debug("call BrandDaoImpl.findAllByCategory parameters : {}, {}", locale, categoryCode);
		
		Session session = em.unwrap(Session.class);
		
		List<String> lc = new ArrayList<String>(Arrays.asList(categoryCode));
		
		Query query = session.createNativeQuery(constructSQL(
														     true,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false))
				 .setParameter("categoryCode", Constants.primaryRootCategoryCode)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("categoryCodes", lc);
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new BrandDTOResultTransformer());
		
		List<BrandDTO> results = query.getResultList();
		
		return results;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME + "ByProductCode", key="{#locale, #productCode}")
			}
	)
	public Optional<BrandDTO> findByProductCode(String locale, String productCode) {
		LOGGER.debug("call BrandDaoImpl.findByProductCode parameters : {}, {}", locale, productCode);
		
		Session session = em.unwrap(Session.class);
		
		List<String> lpc = new ArrayList<String>(Arrays.asList(productCode));
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 true,
															 false,
															 false,
															 false))
				 .setParameter("categoryCode", Constants.primaryRootCategoryCode)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode)
				 .setParameter("productCodes", lpc);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new BrandDTOResultTransformer());
		
		BrandDTO result = (BrandDTO) query.getSingleResult();
		
		return Optional.ofNullable(result);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<BrandDTO> findAll(String locale) {
		LOGGER.debug("call BrandDaoImpl.findAll parameters : {}", locale);
				
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 false,
															 false,
															 false))
				 .setParameter("categoryCode", Constants.primaryRootCategoryCode)
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new BrandDTOResultTransformer());
		
		List<BrandDTO> results = query.getResultList();
		
		return results;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	@Caching(
			put = {
					@CachePut(value = CACHE_NAME + "Other", key="{#locale, #currency, #categoryCode, #categoryCodes.getCacheKey(), #tagCodes.getCacheKey(), #maxPrice}")
			}
	)
	public List<BrandDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper tagCodes, Double maxPrice) {
		LOGGER.debug("call BrandDaoImpl.findAll with parameters : locale = {}, currency = {}, categoryCode = {}, category codes = {}, tag codes = {}, maxPrice = {}", locale, currency, categoryCode, StringUtil.join(categoryCodes, ','), StringUtil.join(tagCodes, ','), maxPrice);
		
		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(
															 !categoryCodes.getCodes().isEmpty(),
															 !tagCodes.getCodes().isEmpty(),
															 !(maxPrice == null),
															 false,
															 false,
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("categoryCode", categoryCode)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		if(!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
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
		.setResultTransformer(new BrandDTOResultTransformer());
		
		List<BrandDTO> results = query.getResultList();
		
		return results;
		
	}
	
	@Override
	public void save(BrandEntity t) {
		em.persist(t);
	}
	
	@Override
	public void update(BrandEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(BrandEntity t) {
		// TODO Auto-generated method stub
		
	}

	
	private String constructSQL(
			boolean hasCategories,
			boolean hasTags,
			boolean hasPrice,
			boolean hasProductCodes,
			boolean hasBrandCodes,
			boolean hasBrandDescriptions,
			boolean hasBrandIds) {
	String sql = "WITH recursive descendants AS " + 
			"( " + 
			"          SELECT    t.cat_id, " + 
			"                    t.cat_cd, " + 
			"                    t.cat_lvl, " + 
			"                    t.cat_prnt_id, " +
			"                    t.cat_prnt_cd, " + 
			"                    t.cat_typ_id, " + 
			"                    cast('/' " + 
			"                              || cast(t.cat_id AS text) " + 
			"                              || '/' AS text) node " + 
			"          FROM      mochi.category            AS t " + 
			"          WHERE     0=0 " +
			" 		   AND t.cat_cd 	= :categoryCode " + 
			"          UNION ALL " + 
			"          SELECT t.cat_id, " + 
			"                 t.cat_cd, " + 
			"                 t.cat_lvl, " + 
			"                 t.cat_prnt_id, " +
			"                 t.cat_prnt_cd, " +
			"                 t.cat_typ_id, " + 
			"                 cast(d.node " + 
			"                        || cast(t.cat_id AS text) " + 
			"                        || '/' AS text) node " + 
			"          FROM   mochi.category         AS t " + 
			"          JOIN   descendants            AS d " + 
			"          ON     t.cat_prnt_id = d.cat_id )" + 
			", categories AS " + 
			"( " + 
			"          SELECT    coalesce(s2.cat_id,s1.cat_id) as cat_id, " +
			"		   			 coalesce(s2.cat_cd,s1.cat_cd) as cat_cd " +
			"          FROM      descendants s1 " + 
			"          LEFT JOIN descendants s2 " + 
			"          ON        s1.node <> s2.node " + 
			"          AND       LEFT(s2.node, length(s1.node)) = s1.node " +
			"		   WHERE 0=0 " + 
			((hasCategories) ? 	"AND  s1.cat_cd IN (:categoryCodes) " : "") +
			"          GROUP BY  coalesce(s2.cat_id,s1.cat_id), " +
			"		   			 coalesce(s2.cat_cd,s1.cat_cd)" +
			")" + 
			"select b.bnd_id, 		" + 
			"	    b.bnd_cd,		" + 
			"	    lcl.bnd_lcl_id,	" + 
			"	    lcl.bnd_desc,	" + 
			"	    lcl.lcl_cd, 	" +		
			"	    count(distinct p.upc_cd) as object_count " + 
			"from categories c " + 
			"	inner join mochi.product_category pc" + 
			"		on c.cat_id = pc.cat_id" + 
			"						" + 
			"	inner join mochi.product p" + 
			"		on pc.prd_id = p.prd_id" + 
			"						" + 
			"	inner join mochi.product_status ps" + 
			"		on p.prd_sts_id = ps.prd_sts_id" + 
			"		and ps.prd_sts_cd = :activeProductCode " + 
			"						 " + 
			"	inner join mochi.brand b" + 
			"		on p.bnd_id = b.bnd_id" + 
			"						 " + 
			"	inner join mochi.brand_attr_lcl lcl" + 
			"		on b.bnd_id = lcl.bnd_id" + 
			"		and lcl.lcl_cd = :locale " + 
			"						 " + 
			((hasPrice) ?
			"inner join  ( " +
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
			"		ON p.prd_id = mprc.prd_id  " +
			"		AND prc_val <= :maxPrice " 
			: "") +
			
			((hasTags) ?
				"	left join mochi.product_tag pt" + 
				"		on p.prd_id = pt.prd_id" + 
				"" + 
				"	left join mochi.tag t " + 
				"		on pt.tag_id = t.tag_id" 
				: "") + 
			"						 " + 
			"where 0=0 " + 
			((hasTags) ? 	" 					AND t.tag_cd in 	:tagCodes " 			: "") +
			((hasProductCodes) ? " 				AND p.prd_cd in 	:productCodes " 		: "") +
			((hasBrandCodes) ? " 				AND b.bnd_cd in 	:brandCodes " 			: "") +
			((hasBrandDescriptions) ? " 		AND lcl.bnd_desc in :brandDescriptions " 	: "") +
			((hasBrandIds) ? " 					AND b.bnd_id in 	:brandIds " 			: "") +
			"group by 	b.bnd_id, 		" + 
			"	   		b.bnd_cd,		" + 
			"	   		lcl.bnd_desc,	" + 
			"	   		lcl.bnd_lcl_id, " +
			"	   		lcl.lcl_cd		" + 
			"order by lcl.bnd_desc ASC ";
		
	return sql;
	}

	@Override
	public BrandDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BrandEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BrandDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	


}