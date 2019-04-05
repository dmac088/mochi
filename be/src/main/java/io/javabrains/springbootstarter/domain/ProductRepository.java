package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findAll();
	
	List<Product> findAll(Specification<Product> spec);
	
	List<Product> findByPreviewFlag(Long previewFlag);
	
	@Query(
		value = "WITH RECURSIVE MyCTE AS ( SELECT cat_id, cat_cd FROM mochi.category WHERE cat_cd = :categoryCode UNION ALL SELECT c.cat_id, c.cat_cd FROM mochi.category c INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id WHERE c.cat_prnt_id IS NOT NULL ) SELECT count(p.prd_id) FROM MyCTE c inner join mochi.product_category pc on c.cat_id = pc.cat_id  inner join mochi.product p  on pc.prd_id = p.prd_id",
		nativeQuery = true)
	Long countByCategoriesCategoryCode(@Param("categoryCode") String categoryCode);
		
	@Query(
		value = "WITH RECURSIVE MyCTE AS ( SELECT cat_id, cat_cd FROM mochi.category WHERE cat_cd = :categoryCode UNION ALL SELECT c.cat_id, c.cat_cd FROM mochi.category c INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id WHERE c.cat_prnt_id IS NOT NULL ) SELECT count(p.prd_id) FROM MyCTE c inner join mochi.product_category pc on c.cat_id = pc.cat_id  inner join mochi.product p  on pc.prd_id = p.prd_id inner join mochi.brand b on p.bnd_id = b.bnd_id WHERE b.bnd_cd = :brandCode",
		nativeQuery = true)	
	Long countByCategoriesCategoryCodeAndBrandBrandCode(@Param("categoryCode") String categoryCode, @Param("brandCode") String brandCode);

		/*
	@Query(
		value = "WITH RECURSIVE MyCTE AS ( SELECT cat_id, cat_cd FROM mochi.category WHERE cat_cd = :categoryCode UNION ALL SELECT c.cat_id, c.cat_cd FROM mochi.category c INNER JOIN MyCTE ON c.cat_prnt_id = MyCTE.cat_id WHERE c.cat_prnt_id IS NOT NULL ) SELECT max(p.prd_id) FROM MyCTE c inner join mochi.product_category pc on c.cat_id = pc.cat_id  inner join mochi.product p  on pc.prd_id = p.prd_id inner join mochi.brand b on p.bnd_id = b.bnd_id WHERE b.bnd_cd = :brandCode",
		nativeQuery = true)	
	Long maxPriceByCategoriesCategoryCodeAndBrandBrandCode(@Param("categoryCode") String categoryCode, @Param("brandCode") String brandCode);
		*/
	
	
	Optional<Product> findByProductId(Long id);
}
