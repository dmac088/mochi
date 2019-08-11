package io.nzbee.entity.product;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

public interface IProductRepository extends CrudRepository<Product, Long> {

	
	@Query(
		value = "WITH RECURSIVE MyCTE AS ( "
				+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
				+ "FROM mochi.category c "
				+ "INNER JOIN mochi.hierarchy h "
				+ "	ON c.hir_id = h.hir_id "
				+ "INNER JOIN mochi.category_attr_lcl ca "
				+ " ON c.cat_id = ca.cat_id "
				+ "INNER JOIN mochi.category_type ct "
				+ " ON c.cat_typ_id = ct.cat_typ_id "
				+ "WHERE ca.cat_desc = :categoryDesc "
				+ "AND ca.lcl_cd = :locale "
				+ "AND ct.cat_typ_cd = :categoryTypeCode "
				+ "UNION ALL "
				+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
				+ "FROM mochi.category c "
				+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
				+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
				+ "WHERE c.cat_prnt_id IS NOT NULL "
				+ ") "
				+ "SELECT coalesce(MAX(prc.prc_val), 0) "
				+ "FROM MyCTE c "
				+ "INNER JOIN mochi.hierarchy h "
				+ "	ON c.hir_id = h.hir_id "
				+ "INNER JOIN mochi.category_attr_lcl ca "
				+ " ON c.cat_id = ca.cat_id "
				+ "INNER JOIN mochi.product_category pc "
				+ "	ON c.cat_id = pc.cat_id  "
				+ "INNER JOIN mochi.product p "
				+ "	ON pc.prd_id = p.prd_id "
				+ "INNER JOIN mochi.brand b "
				+ "	ON p.bnd_id = b.bnd_id "
				+ "INNER JOIN mochi.price prc "
				+ "	ON p.prd_id = prc.prd_id "
				+ "INNER JOIN mochi.price_type pt "
				+ "	ON prc.prc_typ_id = pt.prc_typ_id "
				+ "INNER JOIN mochi.currency ccy "
				+ "	ON prc.ccy_id = ccy.ccy_id "
				+ "INNER JOIN mochi.category_type ct "
				+ " ON c.cat_typ_id = ct.cat_typ_id "
				+ "INNER JOIN mochi.product_status pstat "
				+ " ON p.prd_sts_id = pstat.prd_sts_id "
				+ "WHERE pt.prc_typ_cd 	= 'MKD01' "
				+ "AND pstat.prd_sts_cd 	= :productStatusCode "
				+ "AND ct.cat_typ_cd 		= :categoryTypeCode "
				+ "AND ccy.ccy_cd 			= :currencyCode "
				+ "AND ca.lcl_cd 			= :locale "
				+ "AND ("
				+ "	:inHandlingBrands = 0 "
				+ " OR "
				+ " b.bnd_cd in (:brandCodes) "
				+ ") "
				+ "AND ("
				+ "	:inHandlingCategories = 0 "
				+ " OR "
				+ " c.cat_cd in (:categoryCodes) "
				+ ") "
				+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt ",
		nativeQuery = true)	
	Double maxMarkDownPrice(
							@Param("categoryTypeCode") 		String categoryTypeCode,
							@Param("categoryDesc") 			String categoryDesc, 
							@Param("locale") 				String locale,
							@Param("currencyCode") 			String currencyCode,
							@Param("productStatusCode")		String productStatusCode,
						   	@Param("brandCodes")			List<String> brandCodes,
						   	@Param("inHandlingBrands")		int inHandlingBrands,
						   	@Param("categoryCodes") 		List<String> categoryCodes,
						   	@Param("inHandlingCategories")	int inHandlingCategories);
	
	
	@Query(
			value = "WITH RECURSIVE MyCTE AS ( "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h "
					+ "	ON c.hir_id = h.hir_id "
					+ "INNER JOIN mochi.category_type ct "
					+ " ON c.cat_typ_id = ct.cat_typ_id "
					+ "WHERE c.cat_cd = :categoryCode "
					+ "AND ct.cat_typ_cd = '" + CategoryVars.CATEGORY_TYPE_CODE_PRODUCT + "'"
					+ "UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
					+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL "
					+ ") "
					+ "SELECT coalesce(MAX(prc.prc_val), 0) "
					+ "FROM MyCTE c "
					+ "INNER JOIN mochi.hierarchy h "
					+ "	ON c.hir_id = h.hir_id "
					+ "INNER JOIN mochi.product_category pc "
					+ "	ON c.cat_id = pc.cat_id  "
					+ "INNER JOIN mochi.product p "
					+ "	ON pc.prd_id = p.prd_id "
					+ "INNER JOIN mochi.price prc "
					+ "	ON p.prd_id = prc.prd_id "
					+ "INNER JOIN mochi.price_type pt "
					+ "	ON prc.prc_typ_id = pt.prc_typ_id "
					+ "INNER JOIN mochi.currency ccy "
					+ "	ON prc.ccy_id = ccy.ccy_id "
					+ "INNER JOIN mochi.category_type ct "
					+ " ON c.cat_typ_id = ct.cat_typ_id "
					+ "INNER JOIN mochi.product_status pstat "
					+ " ON p.prd_sts_id = pstat.prd_sts_id "
					+ "WHERE pt.prc_typ_cd 		= '" + ProductVars.PRICE_MARKDOWN_CODE + "'"
					+ "AND pstat.prd_sts_cd 	= '" + ProductVars.ACTIVE_SKU_CODE + "'"
					+ "AND ct.cat_typ_cd 		=  '" + CategoryVars.CATEGORY_TYPE_CODE_PRODUCT + "'"
					+ "AND ccy.ccy_cd 			= :currencyCode "
					+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt ",
			nativeQuery = true)	
		Double maxMarkDownPriceForCategory(
				@Param("categoryCode") 		String categoryCode,
				@Param("currencyCode") 		String currencyCode
		);
	
	@Query(
			value = "WITH RECURSIVE MyCTE AS ( "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h "
					+ "	ON c.hir_id = h.hir_id "
					+ "INNER JOIN mochi.category_attr_lcl ca "
					+ " ON c.cat_id = ca.cat_id "
					+ "INNER JOIN mochi.category_type ct "
					+ " ON c.cat_typ_id = ct.cat_typ_id "
					+ "WHERE ca.cat_desc = :categoryDesc "
					+ "AND ca.lcl_cd = :locale "
					+ "AND ct.cat_typ_cd = :categoryTypeCode "
					+ "UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
					+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL "
					+ ") "
					+ "SELECT coalesce(MAX(prc.prc_val), 0) "
					+ "FROM MyCTE c "
					+ "INNER JOIN mochi.hierarchy h "
					+ "	ON c.hir_id = h.hir_id "
					+ "INNER JOIN mochi.category_attr_lcl ca "
					+ " ON c.cat_id = ca.cat_id "
					+ "INNER JOIN mochi.product_category pc "
					+ "	ON c.cat_id = pc.cat_id  "
					+ "INNER JOIN mochi.product p "
					+ "	ON pc.prd_id = p.prd_id "
					+ "INNER JOIN mochi.brand b "
					+ "	ON p.bnd_id = b.bnd_id "
					+ "INNER JOIN mochi.price prc "
					+ "	ON p.prd_id = prc.prd_id "
					+ "INNER JOIN mochi.price_type pt "
					+ "	ON prc.prc_typ_id = pt.prc_typ_id "
					+ "INNER JOIN mochi.currency ccy "
					+ "	ON prc.ccy_id = ccy.ccy_id "
					+ "INNER JOIN mochi.category_type ct "
					+ " ON c.cat_typ_id = ct.cat_typ_id "
					+ "INNER JOIN mochi.product_tag ptag "
					+ " ON p.prd_id = ptag.prd_id "
					+ "INNER JOIN mochi.product_status pstat "
					+ " ON p.prd_sts_id = pstat.prd_sts_id "
					+ "WHERE pt.prc_typ_cd 	=  'MKD01' "
					+ "AND pstat.prd_sts_cd 	= :productStatusCode "
					+ "AND ct.cat_typ_cd 		= :categoryTypeCode "
					+ "AND ccy.ccy_cd 			= :currencyCode "
					+ "AND ca.lcl_cd 			= :locale "
					+ "AND ("
					+ "	:inHandlingBrands = 0 "
					+ " OR "
					+ " b.bnd_cd in (:brandCodes) "
					+ ") "
					+ "AND ("
					+ "	:inHandlingCategories = 0 "
					+ " OR "
					+ " c.cat_cd in (:categoryCodes) "
					+ ") "
					+ "AND ("
					+ "	:inHandlingTags = 0 "
					+ " OR "
					+ " ptag.tag_cd in (:tagCodes) "
					+ ") "
					+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt ",
			nativeQuery = true)	
		Double maxMarkDownPriceForTags(	
										@Param("categoryTypeCode") 		String categoryTypeCode,
										@Param("categoryDesc") 			String categoryDesc, 
										@Param("locale") 				String locale,
										@Param("currencyCode") 			String currencyCode,
										@Param("productStatusCode")		String productStatusCode,
									   	@Param("brandCodes")			List<String> brandCodes,
									   	@Param("inHandlingBrands")		int inHandlingBrands,
									   	@Param("categoryCodes") 		List<String> categoryCodes,
									   	@Param("inHandlingCategories")	int inHandlingCategories,
									   	@Param("tagCodes")  			List<String> tagCodes,
									   	@Param("inHandlingTags")		int inHandlingTags);
	
	@Query(
		value = "WITH RECURSIVE MyCTE AS ( "
				+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
				+ "FROM mochi.category c "
				+ "INNER JOIN mochi.hierarchy h "
				+ "	ON c.hir_id = h.hir_id "
				+ "INNER JOIN mochi.category_attr_lcl ca "
				+ " ON c.cat_id = ca.cat_id "
				+ "WHERE ca.cat_desc = :categoryDesc "
				+ "AND ca.lcl_cd = :locale "
				+ "UNION ALL "
				+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
				+ "FROM mochi.category c "
				+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
				+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
				+ "WHERE c.cat_prnt_id IS NOT NULL "
				+ ") "
				+ "SELECT COUNT(DISTINCT p.prd_id) "
				+ "FROM MyCTE c "
				+ "INNER JOIN mochi.hierarchy h "
				+ "	ON c.hir_id = h.hir_id "
				+ "INNER JOIN mochi.category_attr_lcl ca "
				+ " ON c.cat_id = ca.cat_id "
				+ "INNER JOIN mochi.product_category pc "
				+ "	ON c.cat_id = pc.cat_id  "
				+ "INNER JOIN mochi.product p "
				+ "	ON pc.prd_id = p.prd_id "
				+ "INNER JOIN mochi.brand b "
				+ "	ON p.bnd_id = b.bnd_id "
				+ "INNER JOIN mochi.product_status pstat "
				+ " ON p.prd_sts_id = pstat.prd_sts_id "
				+ "WHERE pstat.prd_sts_cd 	= :productStatusCode "
				+ "AND ca.lcl_cd 			= :locale "
				+ "AND ("
				+ "	:inHandlingBrands = 0 "
				+ " OR "
				+ " b.bnd_cd in (:brandCodes) "
				+ ") "
				+ "AND ("
				+ "	:inHandlingCategories = 0 "
				+ " OR "
				+ " c.cat_cd in (:categoryCodes) "
				+ ") ",
		nativeQuery = true)	
	Long count(	
			   	@Param("categoryDesc") 			String categoryDesc, 
			   	@Param("locale") 				String locale,
			   	@Param("productStatusCode")		String productStatusCode,
			   	@Param("brandCodes")			List<String> brandCodes,
			   	@Param("inHandlingBrands")		int inHandlingBrands,
			   	@Param("categoryCodes") 		List<String> categoryCodes,
			   	@Param("inHandlingCategories")	int inHandlingCategories);
	
	
	@Query(
			value = "WITH RECURSIVE MyCTE AS ( "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h "
					+ "	ON c.hir_id = h.hir_id "
					+ "INNER JOIN mochi.category_type ct "
					+ " ON c.cat_typ_id = ct.cat_typ_id "
					+ "WHERE c.cat_cd = :categoryCode "
					+ "AND ct.cat_typ_cd = '" + CategoryVars.CATEGORY_TYPE_CODE_PRODUCT + "'"
					+ "UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
					+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL "
					+ ") "
					+ "SELECT count(distinct p.prd_id) "
					+ "FROM MyCTE c "
					+ "INNER JOIN mochi.hierarchy h "
					+ "	ON c.hir_id = h.hir_id "
					+ "INNER JOIN mochi.product_category pc "
					+ "	ON c.cat_id = pc.cat_id  "
					+ "INNER JOIN mochi.product p "
					+ "	ON pc.prd_id = p.prd_id "
					+ "INNER JOIN mochi.category_type ct "
					+ " ON c.cat_typ_id = ct.cat_typ_id "
					+ "INNER JOIN mochi.product_status pstat "
					+ " ON p.prd_sts_id = pstat.prd_sts_id "
					+ "WHERE pstat.prd_sts_cd 	= '" + ProductVars.ACTIVE_SKU_CODE + "'"
					+ "AND ct.cat_typ_cd 		=  '" + CategoryVars.CATEGORY_TYPE_CODE_PRODUCT + "'",
					nativeQuery = true)
	
		Long countForCategory(
				@Param("categoryCode") 		String categoryCode
		);
	
	@Query(
			value = "WITH RECURSIVE MyCTE AS ( "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h "
					+ "	ON c.hir_id = h.hir_id "
					+ "INNER JOIN mochi.category_attr_lcl ca "
					+ " ON c.cat_id = ca.cat_id "
					+ "WHERE ca.cat_desc = :categoryDesc "
					+ "AND ca.lcl_cd = :locale "
					+ "UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
					+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL "
					+ ") "
					+ "SELECT COUNT(DISTINCT p.prd_id) "
					+ "FROM MyCTE c "
					+ "INNER JOIN mochi.hierarchy h "
					+ "	ON c.hir_id = h.hir_id "
					+ "INNER JOIN mochi.category_attr_lcl ca "
					+ " ON c.cat_id = ca.cat_id "
					+ "INNER JOIN mochi.product_category pc "
					+ "	ON c.cat_id = pc.cat_id  "
					+ "INNER JOIN mochi.product p "
					+ "	ON pc.prd_id = p.prd_id "
					+ "INNER JOIN mochi.brand b "
					+ "	ON p.bnd_id = b.bnd_id "
					+ "INNER JOIN mochi.product_tag ptag "
					+ " ON p.prd_id = ptag.prd_id "
					+ "INNER JOIN mochi.product_status pstat "
					+ " ON p.prd_sts_id = pstat.prd_sts_id "
					+ "WHERE pstat.prd_sts_cd 	= :productStatusCode "
					+ "AND ca.lcl_cd 			= :locale "
					+ "AND ("
					+ "	:inHandlingBrands = 0 "
					+ " OR "
					+ " b.bnd_cd in (:brandCodes) "
					+ ") "
					+ "AND ("
					+ "	:inHandlingCategories = 0 "
					+ " OR "
					+ " c.cat_cd in (:categoryCodes) "
					+ ") "
					+ "AND ("
					+ "	:inHandlingTags = 0 "
					+ " OR "
					+ " ptag.tag_cd in (:tagCodes) "
					+ ") ",
			nativeQuery = true)	
		Long countForTags(	
						   	@Param("categoryDesc") 			String categoryDesc, 
						   	@Param("locale") 				String locale,
						   	@Param("productStatusCode")		String productStatusCode,
						   	@Param("brandCodes")			List<String> brandCodes,
						   	@Param("inHandlingBrands")		int inHandlingBrands,
						   	@Param("categoryCodes") 		List<String> categoryCodes,
						   	@Param("inHandlingCategories")	int inHandlingCategories,
						   	@Param("tagCodes")  			List<String> tagCodes,
						   	@Param("inHandlingTags")		int inHandlingTags
		);
	
}
