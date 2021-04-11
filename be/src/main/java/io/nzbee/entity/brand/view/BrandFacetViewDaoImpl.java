package io.nzbee.entity.brand.view;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;

public class BrandFacetViewDaoImpl implements IBrandFacetViewDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<BrandFacetViewDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper tagCodes, Double maxPrice) {
		
		LOGGER.debug("call BrandFacetViewDaoImpl.findAll with parameters : locale = {}, currency = {}, categoryCode = {}, category codes = {}, tag codes = {}, maxPrice = {}", locale, currency, categoryCode, StringUtil.join(categoryCodes, ','), StringUtil.join(tagCodes, ','), maxPrice);
		
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
		.setResultTransformer(new BrandFacetViewDTOResultTransformer());
		
		List<BrandFacetViewDTO> results = query.getResultList();
		
		return results;
	}
	
	@Override
	public void save(BrandFacetViewDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(BrandFacetViewDTO t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BrandFacetViewDTO t) {
		
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


}
