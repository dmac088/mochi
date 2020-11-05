package io.nzbee.entity.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.HashSet;
import org.hibernate.Session;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;

@Component 
public class TagDaoPostgresImpl implements ITagDao {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Optional<TagEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TagEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TagEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TagDTO> findById(String locale, Long id) {
		LOGGER.debug("call TagDaoPostgresImpl.findById with parameters : {}, {}", locale, id);
		
		Session session = em.unwrap(Session.class);
		
		List<Long> ltids = Arrays.asList(id);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 false,
															 true))
				 .setParameter("locale", locale)
				 .setParameter("tagIDs", ltids)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		TagDTO result = (TagDTO) query.getSingleResult();
		
		return Optional.ofNullable(result);
	}
	
	@Override
	public Optional<TagDTO> findByCode(String locale, String code) {
		LOGGER.debug("call TagDaoPostgresImpl.findByCode with parameters : {}, {}, {}", locale, code);
		
		Session session = em.unwrap(Session.class);
		
		List<String> ltc = Arrays.asList(code);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 true,
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("tagCodes", ltc)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		TagDTO result = (TagDTO) query.getSingleResult();
		
		return Optional.ofNullable(result);
	}
	
	
	@Override
	public Optional<TagEntity> findByCode(String code) {
		LOGGER.debug("call TagDaoPostgresImpl.findByCode with parameters : {}", code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<TagEntity> cq = cb.createQuery(TagEntity.class);
		
		Root<TagEntity> root = cq.from(TagEntity.class);

		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions.add(
				cb.equal(root.get(TagEntity_.TAG_CODE), code)
		);
		
		TypedQuery<TagEntity> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		try {
			TagEntity tag = query.getSingleResult();
			return Optional.ofNullable(tag);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
		
	}

	
	@Override
	public Optional<TagDTO> findByDesc(String locale, String desc) {
		LOGGER.debug("call TagDaoPostgresImpl.findByDesc with parameters : {}, {}", locale, desc);
		
		Session session = em.unwrap(Session.class);
		
		List<String> ltd = Arrays.asList(desc);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 true,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("tagDescriptions", ltd)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		TagDTO result = (TagDTO) query.getSingleResult();
		
		return Optional.ofNullable(result);
	}


	@Override
	public Set<TagDTO> findAll(String locale, Set<String> tagCodes) {
		LOGGER.debug("pop call TagDaoPostgresImpl.findAll with parameters : {}, {}", locale, StringUtil.join(tagCodes));

		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 !tagCodes.isEmpty(),
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		
		if(!tagCodes.isEmpty()) {
			query.setParameter("tagCodes", tagCodes);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		Set<TagDTO> results = new HashSet<TagDTO>(query.getResultList());
		
		return results;
	}
	
	@Override
	public void save(TagEntity t) {
		em.persist(t);
	}

	@Override
	public void update(TagEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TagEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<TagDTO> findAll(String locale) {
		LOGGER.debug("call TagDaoPostgresImpl.findAll with parameters : {}, {}", locale);

		Session session = em.unwrap(Session.class);
		
		Query query = session.createNativeQuery(constructSQL(
															 false,
															 false,
															 false,
															 false,
															 false,
															 false))
				 .setParameter("locale", locale)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
				
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		Set<TagDTO> results = new HashSet<TagDTO>(query.getResultList());
		
		return results;
		
	}

	
	@Override
	public Set<TagDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Double maxPrice) {
		LOGGER.debug("call TagDaoPostgresImpl.findAll with parameters : locale = {}, currency = {}, category code = {}, category codes = {}, brand codes = {}, max price = {}", locale, currency, categoryCode, StringUtil.join(categoryCodes, ','), StringUtil.join(brandCodes, ','), maxPrice);
		
		Session session = em.unwrap(Session.class);
		
		categoryCodes.add(categoryCode);
		
		Query query = session.createNativeQuery(constructSQL(
															 !categoryCodes.isEmpty(),
															 !brandCodes.isEmpty(),
															 !(maxPrice == null),
															 false,
															 false,
															 false))
				 .setParameter("locale", locale)
				// .setParameter("categoryCode", categoryCode)
				 .setParameter("activeProductCode", Constants.activeSKUCode);
		
		if(!categoryCodes.isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes);
		}
		
		if(!brandCodes.isEmpty()) {
			query.setParameter("brandCodes", brandCodes);
		}
		
		if(!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
			query.setParameter("currency", currency);
			query.setParameter("markdownPriceCode", Constants.markdownPriceCode);
		}
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new TagDTOResultTransformer());
		
		@SuppressWarnings("unchecked")
		Set<TagDTO> results = new HashSet<TagDTO>(query.getResultList());
		
		return results;
		
	}
	

	private String constructSQL(
			boolean hasCategories,
			boolean hasBrands,
			boolean hasPrice,
			boolean hasTagCodes,
			boolean hasTagDescriptions,
			boolean hasTagIds
		) {
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
				"          AND t.cat_lvl = 0 " +
				//"          AND coalesce(t.cat_cd, t.cat_prnt_cd) = :categoryCode " + 
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
				"			select t.tag_id					as tag_id,  " + 
				"				   t.tag_cd 				as tag_cd, " + 
				"				   lcl.tag_lcl_id 			as tag_lcl_id, " + 
				"				   lcl.tag_desc 			as tag_desc, " + 
				"				   lcl.lcl_cd 				as lcl_cd, 		" + 
				"				   count(distinct p.upc_cd) as object_count  " + 
				"			from categories c  " + 
				"				inner join mochi.product_category pc " + 
				"					on c.cat_id = pc.cat_id " + 
				"				 " + 
				"				inner join mochi.product p " + 
				"					on pc.prd_id = p.prd_id " + 
				"				 " + 
				"				inner join mochi.product_status ps " + 
				"					on p.prd_sts_id = ps.prd_sts_id " + 
				"					and ps.prd_sts_cd = :activeProductCode " + 
				"									  " + 
				"				inner join mochi.brand b " +
				" 					on p.bnd_id = b.bnd_id " +
							
				"				inner join mochi.product_tag pt " + 
				"					on p.prd_id = pt.prd_id " + 
				 
				"				inner join mochi.tag t " + 
				"					on pt.tag_id = t.tag_id " + 
				 
				"				inner join mochi.tag_attr_lcl lcl " + 
				"					on t.tag_id = lcl.tag_id " + 
				"					and lcl.lcl_cd = :locale  " + 
				 
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
				
				"			where 0=0  " +
				((hasBrands) ? 				" 	AND b.bnd_cd 		in 	:brandCodes " 			: "") +
				((hasTagCodes) ? 			" 	AND t.tag_cd 		in 	:tagCodes " 			: "") +
				((hasTagDescriptions) ? 	" 	AND lcl.tag_desc 	in 	:tagDescriptions " 		: "") +
				((hasTagIds) ? 	" 				AND t.tag_id 	 	in 	:tagIDs " 				: "") +
				"			group by t.tag_id,  " + 
				"				   t.tag_cd, " + 
				"				   lcl.tag_lcl_id, " + 
				"				   lcl.tag_desc, " + 
				"				   lcl.lcl_cd"	;
		
	return sql;
	}

	@Override
	public TagDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

}
