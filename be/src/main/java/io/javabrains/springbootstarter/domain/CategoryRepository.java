package io.javabrains.springbootstarter.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();
	
	Category findByCategoryId(Long id);
	
	Category findByCategoryCode(String categoryCode);
	
	List<Category> findByCategoryLevelAndCategoryTypeCode(Long level, String categoryTypeCode);
	
	List<Category> findByParentCategoryId(Long categoryId);

	Category findByAttributesCategoryDesc(String categoryDesc);
}
