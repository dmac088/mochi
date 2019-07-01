package io.nzbee.entity;


import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findAll();
	
	List<Product> findAll(Specification<Product> spec);
	
	List<Product> findByCategoriesCategoryIdIn(List<Long> id);
	
	List<Product> findByProductIdIn(Long[] id);
	
	Product findByProductId(Long id);
	
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
				+ "AND h.hir_cd = :hierarchyCode "
				+ "AND ct.cat_typ_cd = :categoryTypeCode "
				+ "UNION ALL "
				+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
				+ "FROM mochi.category c "
				+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
				+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
				+ "WHERE c.cat_prnt_id IS NOT NULL "
				+ "AND h.hir_cd = :hierarchyCode) "
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
				+ "WHERE pt.prc_typ_desc 	= :priceTypeDesc "
				+ "AND ct.cat_typ_cd 		= :categoryTypeCode "
				+ "AND ccy.ccy_cd 			= :currencyCode "
				+ "AND h.hir_cd 			= :hierarchyCode "
				+ "AND ca.lcl_cd 			= :locale "
				+ "AND ("
				+ "	:inHandlingBrands = 0 "
				+ " OR "
				+ " b.bnd_id in (:brandIds) "
				+ ") "
				+ "AND ("
				+ "	:inHandlingCategories = 0 "
				+ " OR "
				+ " c.cat_id in (:categoryIds) "
				+ ") "
				+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt ",
		nativeQuery = true)	
	Double maxMarkDownPrice(@Param("hierarchyCode") 	String hierarchyCode, 
							@Param("categoryTypeCode") 	String categoryTypeCode,
							@Param("categoryDesc") 		String categoryDesc, 
							@Param("locale") 			String locale,
							@Param("currencyCode") 		String currencyCode,
							@Param("priceTypeDesc") 	String priceTypeDesc,
						   	@Param("brandIds") 				List<Long> brandIds,
						   	@Param("inHandlingBrands")		int inHandlingBrands,
						   	@Param("categoryIds") 			List<Long> categoryIds,
						   	@Param("inHandlingCategories")	int inHandlingCategories);
	
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
					+ "AND h.hir_cd = :hierarchyCode "
					+ "AND ct.cat_typ_cd = :categoryTypeCode "
					+ "UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
					+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL "
					+ "AND h.hir_cd = :hierarchyCode) "
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
					+ "WHERE pt.prc_typ_desc 	= :priceTypeDesc "
					+ "AND ct.cat_typ_cd 		= :categoryTypeCode "
					+ "AND ccy.ccy_cd 			= :currencyCode "
					+ "AND h.hir_cd 			= :hierarchyCode "
					+ "AND ca.lcl_cd 			= :locale "
					+ "AND ("
					+ "	:inHandlingBrands = 0 "
					+ " OR "
					+ " b.bnd_id in (:brandIds) "
					+ ") "
					+ "AND ("
					+ "	:inHandlingCategories = 0 "
					+ " OR "
					+ " c.cat_id in (:categoryIds) "
					+ ") "
					+ "AND ("
					+ "	:inHandlingTags = 0 "
					+ " OR "
					+ " ptag.tag_id in (:tagIds) "
					+ ") "
					+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt ",
			nativeQuery = true)	
		Double maxMarkDownPriceForTags(	@Param("hierarchyCode") 	String hierarchyCode, 
										@Param("categoryTypeCode") 	String categoryTypeCode,
										@Param("categoryDesc") 		String categoryDesc, 
										@Param("locale") 			String locale,
										@Param("currencyCode") 		String currencyCode,
										@Param("priceTypeDesc") 	String priceTypeDesc,
									   	@Param("brandIds") 				List<Long> brandIds,
									   	@Param("inHandlingBrands")		int inHandlingBrands,
									   	@Param("categoryIds") 			List<Long> categoryIds,
									   	@Param("inHandlingCategories")	int inHandlingCategories,
									   	@Param("tagIds") 			List<Long> tagIds,
									   	@Param("inHandlingTags")	int inHandlingTags);
	
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
				+ "AND h.hir_cd = :hierarchyCode "
				+ "AND ct.cat_typ_cd = :categoryTypeCode "
				+ "UNION ALL "
				+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
				+ "FROM mochi.category c "
				+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
				+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
				+ "WHERE c.cat_prnt_id IS NOT NULL "
				+ "AND h.hir_cd = :hierarchyCode) "
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
				+ "INNER JOIN mochi.price prc "
				+ "	ON p.prd_id = prc.prd_id "
				+ "INNER JOIN mochi.price_type pt "
				+ "	ON prc.prc_typ_id = pt.prc_typ_id "
				+ "INNER JOIN mochi.currency ccy "
				+ "	ON prc.ccy_id = ccy.ccy_id "
				+ "INNER JOIN mochi.category_type ct "
				+ " ON c.cat_typ_id = ct.cat_typ_id "
				+ "WHERE pt.prc_typ_desc 	= :priceTypeDesc "
				+ "AND ct.cat_typ_cd 		= :categoryTypeCode "
				+ "AND ccy.ccy_cd 			= :currencyCode "
				+ "AND h.hir_cd 			= :hierarchyCode "
				+ "AND ca.lcl_cd 			= :locale "
				+ "AND ("
				+ "	:inHandlingBrands = 0 "
				+ " OR "
				+ " b.bnd_id in (:brandIds) "
				+ ") "
				+ "AND ("
				+ "	:inHandlingCategories = 0 "
				+ " OR "
				+ " c.cat_id in (:categoryIds) "
				+ ") "
				+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt ",
		nativeQuery = true)	
	Long count(	@Param("hierarchyCode") 		String hierarchyCode,
			   	@Param("categoryTypeCode") 		String categoryTypeCode,
			   	@Param("categoryDesc") 			String categoryDesc, 
			   	@Param("locale") 				String locale,
			   	@Param("currencyCode") 			String currencyCode,
			   	@Param("priceTypeDesc") 		String priceTypeDesc,
			   	@Param("brandIds") 				List<Long> brandIds,
			   	@Param("inHandlingBrands")		int inHandlingBrands,
			   	@Param("categoryIds") 			List<Long> categoryIds,
			   	@Param("inHandlingCategories")	int inHandlingCategories);
	
	
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
					+ "AND h.hir_cd = :hierarchyCode "
					+ "AND ct.cat_typ_cd = :categoryTypeCode "
					+ "UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id, c.cat_typ_id "
					+ "FROM mochi.category c "
					+ "INNER JOIN mochi.hierarchy h ON c.hir_id = h.hir_id "
					+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL "
					+ "AND h.hir_cd = :hierarchyCode) "
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
					+ "WHERE pt.prc_typ_desc 	= :priceTypeDesc "
					+ "AND ct.cat_typ_cd 		= :categoryTypeCode "
					+ "AND ccy.ccy_cd 			= :currencyCode "
					+ "AND h.hir_cd 			= :hierarchyCode "
					+ "AND ca.lcl_cd 			= :locale "
					+ "AND ("
					+ "	:inHandlingBrands = 0 "
					+ " OR "
					+ " b.bnd_id in (:brandIds) "
					+ ") "
					+ "AND ("
					+ "	:inHandlingCategories = 0 "
					+ " OR "
					+ " c.cat_id in (:categoryIds) "
					+ ") "
					+ "AND ("
					+ "	:inHandlingTags = 0 "
					+ " OR "
					+ " ptag.tag_id in (:tagIds) "
					+ ") "
					+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt ",
			nativeQuery = true)	
		Long countForTags(	@Param("hierarchyCode") String hierarchyCode,
						   	@Param("categoryTypeCode") 		String categoryTypeCode,
						   	@Param("categoryDesc") 			String categoryDesc, 
						   	@Param("locale") 				String locale,
						   	@Param("currencyCode") 			String currencyCode,
						   	@Param("priceTypeDesc") 		String priceTypeDesc,
						   	@Param("brandIds") 				List<Long> brandIds,
						   	@Param("inHandlingBrands")		int inHandlingBrands,
						   	@Param("categoryIds") 			List<Long> categoryIds,
						   	@Param("inHandlingCategories")	int inHandlingCategories,
						   	@Param("tagIds") 				List<Long> tagIds,
						   	@Param("inHandlingTags")	int inHandlingTags
		);
	
}
