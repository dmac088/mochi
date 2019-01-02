package io.javabrains.springbootstarter.domain;


import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

	ArrayList<ProductCategory> findAll();
	
	ArrayList<ProductCategory> findByLclCd(String lcl);

	Optional<ProductCategory> findByCategoryId(Long id);
	
	Optional<ProductCategory> findByLclCdAndCategoryId(String lcl, Long id);
	
	ArrayList<ProductCategory> findByParentCategoryIdAndLclCd(Long parentId, String lcl);
	
}
