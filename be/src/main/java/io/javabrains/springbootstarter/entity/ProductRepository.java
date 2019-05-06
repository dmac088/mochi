package io.javabrains.springbootstarter.entity;


import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findAll();
	
	List<Product> findAll(Specification<Product> spec);
	
	List<Product> findByCategoriesCategoryIdIn(List<Long> id);
	
	Product findByProductId(Long id);

	
	@Query(
			value = "WITH RECURSIVE MyCTE AS ( "
					+ "SELECT cat_id, cat_cd, c.hir_id "
					+ "FROM "
					+ "mochi.category c "
					+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
					+ "WHERE cat_cd = :categoryCode "
					+ "AND h.hir_cd = :hierarchyCode "
					+ "UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id "
					+ "FROM mochi.category c "
					+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
					+ "inner join MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL "
					+ "AND h.hir_cd = :hierarchyCode) "
					+ "SELECT count(p.prd_id) "
					+ "FROM MyCTE c "
					+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
					+ "inner join mochi.product_category pc on c.cat_id = pc.cat_id  "
					+ "inner join mochi.product p  on pc.prd_id = p.prd_id "
					+ "WHERE h.hir_cd = :hierarchyCode ",
		nativeQuery = true)
	Long countByCategoriesHierarchyCodeAndCategoriesCategoryCode(@Param("hierarchyCode") String hierarchyCode, @Param("categoryCode") String categoryCode);
		
	
	@Query(
			value = "WITH RECURSIVE MyCTE AS ( "
					+ "SELECT cat_id, cat_cd, c.hir_id "
					+ "FROM mochi.category c "
					+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
					+ "WHERE cat_cd = :categoryCode "
					+ "AND h.hir_cd = :hierarchyCode "
					+ "UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd, c.hir_id "
					+ "FROM mochi.category c "
					+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
					+ "inner join MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL "
					+ "AND h.hir_cd = :hierarchyCode) "
					+ "SELECT max(prc.prc_val) "
					+ "FROM MyCTE c "
					+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
					+ "inner join mochi.product_category pc on c.cat_id = pc.cat_id  "
					+ "inner join mochi.product p  on pc.prd_id = p.prd_id "
					+ "inner join mochi.brand b on p.bnd_id = b.bnd_id "
					+ "inner join mochi.price prc on p.prd_id = prc.prd_id "
					+ "inner join mochi.price_type pt on prc.prc_typ_id = pt.prc_typ_id "
					+ "inner join mochi.currency ccy on prc.ccy_id = ccy.ccy_id "
					+ "WHERE pt.prc_typ_desc = 'markdown' "
					+ "AND ccy.ccy_cd = :currencyCode "
					+ "AND h.hir_cd = :hierarchyCode "
					+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt ",
			nativeQuery = true)	
	Long maxMarkDownPriceByCategoriesHierarchyCodeAndCategoriesCategoryCodeAndPriceCurrencyCode(@Param("hierarchyCode") String hierarchyCode, @Param("categoryCode") String categoryCode, @Param("currencyCode") String currencyCode);

	
	@Query(
		value = "WITH RECURSIVE MyCTE AS ( "
				+ "SELECT cat_id, cat_cd, c.hir_id "
				+ "FROM mochi.category c "
				+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
				+ "WHERE cat_cd = :categoryCode "
				+ "AND h.hir_cd = :hierarchyCode  "
				+ "UNION ALL "
				+ "SELECT c.cat_id, c.cat_cd, c.hir_id "
				+ "FROM mochi.category c "
				+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
				+ "inner join MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
				+ "WHERE c.cat_prnt_id IS NOT NULL "
				+ "AND h.hir_cd = :hierarchyCode) "
				+ "SELECT count(p.prd_id) "
				+ "FROM MyCTE c "
				+ "inner join mochi.product_category pc on c.cat_id = pc.cat_id  "
				+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
				+ "inner join mochi.product p  on pc.prd_id = p.prd_id "
				+ "inner join mochi.brand b on p.bnd_id = b.bnd_id "
				+ "WHERE b.bnd_cd = :brandCode "
				+ "AND h.hir_cd = :hierarchyCode ",
		nativeQuery = true)	
	Long countByCategoriesHierarchyCodeAndCategoriesCategoryCodeAndBrandBrandCode(@Param("hierarchyCode") String hierarchyCode,@Param("categoryCode") String categoryCode, @Param("brandCode") String brandCode);

	
	@Query(
		value = "WITH RECURSIVE MyCTE AS ( "
				+ "SELECT cat_id, cat_cd, c.hir_id "
				+ "FROM mochi.category c "
				+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
				+ "WHERE cat_cd = :categoryCode "
				+ "AND h.hir_cd = :hierarchyCode  "
				+ "UNION ALL "
				+ "SELECT c.cat_id, c.cat_cd, c.hir_id "
				+ "FROM mochi.category c "
				+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
				+ "inner join MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
				+ "WHERE c.cat_prnt_id IS NOT NULL "
				+ "AND h.hir_cd = :hierarchyCode) "
				+ "SELECT count(p.prd_id) "
				+ "FROM MyCTE c inner join mochi.product_category pc on c.cat_id = pc.cat_id  "
				+ "inner join mochi.hierarchy h on c.hir_id = h.hir_id "
				+ "inner join mochi.product p  on pc.prd_id = p.prd_id "
				+ "inner join mochi.brand b on p.bnd_id = b.bnd_id "
				+ "WHERE b.bnd_id in :brandIds "
				+ "AND c.cat_cd = :categoryCode "
				+ "AND h.hir_cd = :hierarchyCode ",
		nativeQuery = true)	
	Long countByCategoriesHierarchyCodeAndCategoriesCategoryCodeAndBrandBrandIdIn(@Param("hierarchyCode") String hierarchyCode,@Param("categoryCode") String categoryCode, @Param("brandIds") List<Long> brandIds);
	
	
}
