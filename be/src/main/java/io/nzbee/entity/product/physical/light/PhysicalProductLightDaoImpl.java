package io.nzbee.entity.product.physical.light;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;

@Component(value = "physicalProductLightDao")
public class PhysicalProductLightDaoImpl implements IPhysicalProductLightDao {

	private final Logger LOGGER = LoggerFactory.getLogger(PhysicalProductLightDaoImpl.class);

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategoryCode,
			Pageable pageable, String sort) {
		LOGGER.debug("call PhysicalProductLightDaoImpl.findAll with parameters : {}, {}, {}, {}, {}", locale, currency,
				rootCategoryCode, pageable, sort);

		Query query = em.createNativeQuery(this.constructSQL(false, false, false, false, false, true, false, ""))

				.setParameter("rootCategoryCode", rootCategoryCode)
				.setParameter("locale", locale)
				.setParameter("currency", currency);

		Object result = query.getSingleResult();
		long total = ((Number) result).longValue();

		query = em.createNativeQuery(this.constructSQL(false, false, false, false, false, false, true, ""))

				.setParameter("rootCategoryCode", rootCategoryCode)
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("limit", pageable.getPageSize())
				.setParameter("offset", pageable.getOffset())
				.setParameter("orderby", getOrderby(sort));

		query.unwrap(org.hibernate.query.Query.class)
				.setResultTransformer(new AliasToBeanResultTransformer(PhysicalProductLightDTO.class));

		List<PhysicalProductLightDTO> results = query.getResultList();

		return new PageImpl<PhysicalProductLightDTO>(results, pageable, total);
	}

	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Page<PhysicalProductLightDTO> findAll(String locale, String currency, String rootCategoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		LOGGER.debug("call PhysicalProductLightDaoImpl.findAll with parameters: {}, {}, {}, {}, {}, {}, {}, {}, {}, {}",
				locale, currency, rootCategoryCode, categoryCodes.getCodes(), brandCodes.getCodes(), tagCodes.getCodes(),
				maxPrice, page, size, sort);

		/*
		boolean hasProductCodes,
		boolean hasCategoryCodes,
		boolean hasBrandCodes,
		boolean hasTagCodes,
		boolean hasPrice,
		boolean countOnly,
		boolean offset,
		String 	sort
		*/
		
		Query query = em
				.createNativeQuery(this.constructSQL(false,
													 !categoryCodes.getCodes().isEmpty(),
													 !brandCodes.getCodes().isEmpty(),
													 !tagCodes.getCodes().isEmpty(),
													 !(maxPrice == null),
													 true,
													 false,
													 ""))
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("rootCategoryCode", rootCategoryCode);

		if (!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}

		if (!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}

		if (!tagCodes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", tagCodes.getCodes());
		}

		if (!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}

		Object result = query.getSingleResult();
		long total = ((Number) result).longValue();

		query = em
				.createNativeQuery(	 this.constructSQL(false,
									 !categoryCodes.getCodes().isEmpty(),
									 !brandCodes.getCodes().isEmpty(),
									 !tagCodes.getCodes().isEmpty(),
									 !(maxPrice == null),
									 false,
									 true,
									 ""))
				
				.setParameter("locale", locale)
				.setParameter("currency", currency)
				.setParameter("rootCategoryCode", rootCategoryCode);

		Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		// these should contain default values for these parameters
		query
				.setParameter("orderby", getOrderby(sort))
				.setParameter("limit", pageable.getPageSize())
				.setParameter("offset", pageable.getOffset());

		if (!categoryCodes.getCodes().isEmpty()) {
			query.setParameter("categoryCodes", categoryCodes.getCodes());
		}

		if (!brandCodes.getCodes().isEmpty()) {
			query.setParameter("brandCodes", brandCodes.getCodes());
		}

		if (!tagCodes.getCodes().isEmpty()) {
			query.setParameter("tagCodes", tagCodes.getCodes());
		}

		if (!(maxPrice == null)) {
			query.setParameter("maxPrice", maxPrice);
		}

		query.unwrap(org.hibernate.query.Query.class).setResultTransformer(new AliasToBeanResultTransformer(PhysicalProductLightDTO.class));

		List<PhysicalProductLightDTO> results = query.getResultList();

		return new PageImpl<PhysicalProductLightDTO>(results, PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)), total);
	}

	// this query was previously used to hydrate all objects required for the whole
	// domain model while this is useful for applying business rules it's far too
	// verbose for
	// the simple requirement of sending some lightweight objects to our front end
	// for display purposes.....
	// at this time we only need to return the following to create a lightweight
	// physical product
	// object
	/*
	 * String productUPC, String productDesc, String brandDesc, Double retailPrice,
	 * Double markdownPrice, Boolean inStock, String productImage
	 */

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
		? "count(distinct physicalpr0_1_.upc_cd) as product_count " 
		:
		"		physicalpr0_1_.upc_cd                 AS productUPC," + 
		"       attributes1_.prd_desc                 AS productDesc," + 
		"       attributes3_.bnd_desc                 AS brandDesc," + 
		"       Max(CASE" + 
		"             WHEN productpri7_.prc_typ_cd = '" + Constants.retailPriceCode + "'" +
		"			  THEN prices6_.prc_val" + 
		"             ELSE 0" + 
		"           END)                              AS retailPrice," + 
		"       Max(CASE" + 
		"             WHEN productpri7_.prc_typ_cd = '" + Constants.markdownPriceCode + "'" +
		"			  THEN prices6_.prc_val" + 
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
		? " AND prices6_.prc_val <= :maxPrice " + 
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
		((countOnly) 
		? " " 
		: " ORDER BY 	:orderby ") +
			((offset) 
			? "LIMIT :limit OFFSET :offset " 
			: "")
		: "");
		
		return sql;
	}
	
	private String getOrderby(String param) {
		switch (param) {
			case "nameAsc":
				return "lower(attr.prd_desc) asc";
			case "nameDesc":
				return "lower(attr.prd_desc) desc";
			case "priceAsc":
				return "mprc.prc_val asc";
			case "priceDesc":
			  	return "mprc.prc_val desc";
			default:
				return "lower(prd_desc) asc";
			}
	}

}