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
			value = "WITH RECURSIVE MyCTE AS ( SELECT cat_id, cat_cd FROM mochi.category "
					+ "WHERE cat_cd = :categoryCode UNION ALL "
					+ "SELECT c.cat_id, c.cat_cd "
					+ "FROM mochi.category c "
					+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
					+ "WHERE c.cat_prnt_id IS NOT NULL ) "
					+ "SELECT count(p.prd_id) "
					+ "FROM MyCTE c "
					+ "inner join mochi.product_category pc on c.cat_id = pc.cat_id  "
					+ "inner join mochi.product p  on pc.prd_id = p.prd_id",
		nativeQuery = true)
	Long countByCategoriesCategoryCode(@Param("categoryCode") String categoryCode);
		
	
	@Query(
			value = "WITH RECURSIVE MyCTE AS ( SELECT cat_id, cat_cd FROM mochi.category "
					+ "WHERE cat_cd = :categoryCode UNION ALL SELECT c.cat_id, c.cat_cd "
					+ "FROM mochi.category c "
					+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id WHERE c.cat_prnt_id IS NOT NULL ) "
					+ "SELECT max(prc.prc_val) "
					+ "FROM MyCTE c "
					+ "inner join mochi.product_category pc on c.cat_id = pc.cat_id  "
					+ "inner join mochi.product p  on pc.prd_id = p.prd_id "
					+ "inner join mochi.brand b on p.bnd_id = b.bnd_id "
					+ "inner join mochi.price prc on p.prd_id = prc.prd_id "
					+ "inner join mochi.price_type pt on prc.prc_typ_id = pt.prc_typ_id "
					+ "inner join mochi.currency ccy on prc.ccy_id = ccy.ccy_id "
					
					+ "WHERE pt.prc_typ_desc = 'markdown' "
					+ "AND ccy.ccy_cd = :currencyCode "
					+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt",
			nativeQuery = true)	
	Long maxMarkDownPriceByCategoriesCategoryCodeAndPriceCurrencyCode(@Param("categoryCode") String categoryCode, @Param("currencyCode") String currencyCode);

	
	@Query(
		value = "WITH RECURSIVE MyCTE AS ( SELECT cat_id, cat_cd FROM mochi.category "
				+ "WHERE cat_cd = :categoryCode UNION ALL SELECT c.cat_id, c.cat_cd "
				+ "FROM mochi.category c "
				+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id "
				+ "WHERE c.cat_prnt_id IS NOT NULL ) "
				+ "SELECT count(p.prd_id) "
				+ "FROM MyCTE c inner join mochi.product_category pc on c.cat_id = pc.cat_id  "
				+ "inner join mochi.product p  on pc.prd_id = p.prd_id "
				+ "inner join mochi.brand b on p.bnd_id = b.bnd_id "
				+ "WHERE b.bnd_cd = :brandCode",
		nativeQuery = true)	
	Long countByCategoriesCategoryCodeAndBrandBrandCode(@Param("categoryCode") String categoryCode, @Param("brandCode") String brandCode);


	@Query(
		value = "WITH RECURSIVE MyCTE AS ( SELECT cat_id, cat_cd FROM mochi.category "
				+ "WHERE cat_cd = :categoryCode UNION ALL SELECT c.cat_id, c.cat_cd "
				+ "FROM mochi.category c "
				+ "INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id WHERE c.cat_prnt_id IS NOT NULL ) "
				+ "SELECT max(prc.prc_val) "
				+ "FROM MyCTE c "
				+ "inner join mochi.product_category pc on c.cat_id = pc.cat_id  "
				+ "inner join mochi.product p  on pc.prd_id = p.prd_id "
				+ "inner join mochi.brand b on p.bnd_id = b.bnd_id "
				+ "inner join mochi.price prc on p.prd_id = prc.prd_id "
				+ "inner join mochi.price_type pt on prc.prc_typ_id = pt.prc_typ_id "
				+ "inner join mochi.currency ccy on prc.ccy_id = ccy.ccy_id "
				
				+ "WHERE b.bnd_cd = :brandCode "
				+ "AND pt.prc_typ_desc = 'markdown' "
				+ "AND ccy.ccy_cd = :currencyCode "
				+ "AND now() BETWEEN prc.prc_st_dt AND prc.prc_en_dt",
		nativeQuery = true)	
	Long maxMarkDownPriceByCategoriesCategoryCodeAndBrandBrandCodeAndPriceCurrencyCode(@Param("categoryCode") String categoryCode, @Param("brandCode") String brandCode, @Param("currencyCode") String currencyCode);

	
}
