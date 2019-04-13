package io.javabrains.springbootstarter.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();
	
	Category findByCategoryId(Long id);
	
	List<Category> findByCategoryLevel(Long level);
	
	List<Category> findByPreviewFlag(Long previewFlag);
	
	List<Category> findByParentCategoryId(Long categoryId);

	Category findByProductCategoryAttributeCategoryDesc(String categoryDesc);
}
