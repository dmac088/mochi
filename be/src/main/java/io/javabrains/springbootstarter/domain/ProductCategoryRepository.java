package io.javabrains.springbootstarter.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

	ArrayList<ProductCategory> findAll();
	
	Optional<ProductCategory> findByCategoryId(Long id);
	
	List<ProductCategory> findByParentCategoryId(Long categoryId);
}
