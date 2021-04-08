package io.nzbee.entity.product.physical.full;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.nzbee.Constants;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDaoImpl;

public class PhysicalProductFullDaoImpl implements IPhysicalProductFullDao {

	private final Logger LOGGER = LoggerFactory.getLogger(PhysicalProductLightDaoImpl.class);
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	
	@SuppressWarnings({ "deprecation"})
	@Override
	public Optional<PhysicalProductFullDTO> findByCode(String locale, String currency, String code) {
		  
		LOGGER.debug("call PhysicalProductLightDaoImpl.findByCode with parameters : {}, {}, {}", locale, currency, code);
		
		Query query = em.createNativeQuery(this.constructSQL(false, false, false, false, false, false, true, ""))
				
		.setParameter("productCode", code)
		.setParameter("locale", locale)
		.setParameter("currency", currency);
		
		query.unwrap(org.hibernate.query.Query.class)
		.setResultTransformer(new AliasToBeanResultTransformer(PhysicalProductFullDTO.class));
		
		try {
			PhysicalProductFullDTO result = (PhysicalProductFullDTO) query.getSingleResult();
			return Optional.ofNullable(result);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
		
	}
	
	private String constructSQL(boolean hasProductCodes,
			boolean hasCategoryCodes,
			boolean hasBrandCodes,
			boolean hasTagCodes,
			boolean hasPrice,
			boolean countOnly,
			boolean offset,
			String 	sort) {

		String sql = "WITH recursive descendants AS" + 
		"(" + 
		"       SELECT t.cat_id," + 
		"              t.cat_cd," + 
		"              t.cat_lvl," + 
		"              t.cat_prnt_id," + 
		"              t.cat_prnt_cd," + 
		"              t.cat_typ_id," + 
		"              cast('/'" + 
		"                     || cast(t.cat_id AS text)" + 
		"                     || '/' AS text) node" + 
		"       FROM   mochi.category         AS t" + 
		"       WHERE  0=0" + 
		"       AND    t.cat_cd = :rootCategoryCode " + 
		"       UNION ALL" + 
		"       SELECT t.cat_id," + 
		"              t.cat_cd," + 
		"              t.cat_lvl," + 
		"              t.cat_prnt_id," + 
		"              t.cat_prnt_cd," + 
		"              t.cat_typ_id," + 
		"              cast(d.node" + 
		"                     || cast(t.cat_id AS text)" + 
		"                     || '/' AS text) node" + 
		"       FROM   mochi.category         AS t" + 
		"       JOIN   descendants            AS d" + 
		"       ON     t.cat_prnt_id = d.cat_id )" + 
		"" + 
		"SELECT " +
		((countOnly) 
		? "count(*) as product_count " 
		:
		"		physicalpr0_1_.upc_cd                 AS productUPC," + 
		"       attributes1_.prd_desc                 AS productDesc," + 
		"       attributes3_.bnd_desc                 AS brandDesc," + 
		"       Max(CASE" + 
		"             WHEN productpri7_.prc_typ_cd = 'RET01' THEN prices6_.prc_val" + 
		"             ELSE 0" + 
		"           END)                              AS retailPrice," + 
		"       Max(CASE" + 
		"             WHEN productpri7_.prc_typ_cd = 'MKD01' THEN prices6_.prc_val" + 
		"             ELSE 0" + 
		"           END)                              AS markdownPrice," + 
		"       COALESCE(stockonhan5_.soh_qty, 0) > 0 AS inStock," + 
		"       attributes1_.prd_img_pth              AS productImage ") + 
		"FROM   mochi.product_basic physicalpr0_" + 
		"	   INNER JOIN mochi.product_category pc " + 
		"	   		   ON physicalpr0_.prd_id = pc.prd_id" + 
		"	   INNER JOIN descendants d" + 
		"	   			ON pc.cat_id = d.cat_id" + 
		"       INNER JOIN mochi.product physicalpr0_1_" + 
		"               ON physicalpr0_.prd_id = physicalpr0_1_.prd_id" + 
		"       INNER JOIN mochi.product_attr_lcl attributes1_" + 
		"               ON physicalpr0_.prd_id = attributes1_.prd_id" + 
		"       INNER JOIN mochi.brand brandentit2_" + 
		"               ON physicalpr0_1_.bnd_id = brandentit2_.bnd_id" + 
		"       INNER JOIN mochi.brand_attr_lcl attributes3_" + 
		"               ON brandentit2_.bnd_id = attributes3_.bnd_id" + 
		"       INNER JOIN mochi.product_status productsta4_" + 
		"               ON physicalpr0_1_.prd_sts_id = productsta4_.prd_sts_id" + 
		"       LEFT OUTER JOIN mochi.stock_on_hand stockonhan5_" + 
		"                    ON physicalpr0_.prd_id = stockonhan5_.soh_prd_id" + 
		"       INNER JOIN mochi.price prices6_" + 
		"               ON physicalpr0_.prd_id = prices6_.prd_id" + 
		"       INNER JOIN mochi.price_type productpri7_" + 
		"               ON prices6_.prc_typ_id = productpri7_.prc_typ_id" + 
		"       INNER JOIN mochi.currency currency8_" + 
		"               ON prices6_.ccy_id = currency8_.ccy_id" + 
		"			   " + 
		
		((hasTagCodes)
		? 	"	   INNER JOIN mochi.product_tag pt " + 
		"	   		   ON physicalpr0_.prd_id = pt.prd_id" + 
		"			   " + 
		"	   INNER JOIN mochi.tag t" + 
		"	   		   ON pt.tag_id = t.tag_id" + 
		"	   		   AND (t.tag_cd in :tagCodes)"  
		: "") +
		"			   " + 
		"WHERE  0=0 " +
		"		AND attributes1_.lcl_cd = :locale" + 
		"       AND attributes3_.lcl_cd = :locale" + 
		"       AND currency8_.ccy_cd = :currency" + 
		"	    AND productsta4_.prd_sts_cd = '" + Constants.activeSKUCode + "'" +
		"	    " + 
		
		((hasBrandCodes) 
		? " AND (brandentit2_.bnd_cd in :brandCodes)"
		: "") +
		"	   " + 
		
		((hasProductCodes) 
		? "	AND (physicalpr0_1_.upc_cd IN :productCodes)"
		: "") + 
		"	   " + 
		
		((hasCategoryCodes)
		? "	AND (d.cat_cd in :categoryCodes)" 
		: "") +
		"	   " + 
		
		((hasPrice)
		? " AND prices6_.prc_val > :maxPrice " + 
		"	AND productpri7_.prc_typ_cd = '" + Constants.markdownPriceCode + "'"
		: "") +
		"	" + 
		((!countOnly)
		? "GROUP  BY physicalpr0_1_.upc_cd," + 
		"          attributes1_.prd_desc," + 
		"          attributes3_.bnd_desc," + 
		"          attributes1_.prd_img_pth," + 
		"          stockonhan5_.soh_qty " + 
		"		  " + 
		"ORDER BY 	attributes1_.prd_desc " + 
		((offset) 
		? "LIMIT :limit OFFSET :offset " 
		: "")
		: "");
		
		return sql;
	}

}
