package io.javabrains.springbootstarter.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();
	
	Category findByCategoryId(Long id);
	
	Category findByCategoryCode(String categoryCode);
	
	List<Category> findByCategoryLevelAndCategoryTypeCodeAndHierarchyCode(Long level, String categoryTypeCode, String hierarchyCode);
	
	List<Category> findByParentCategoryId(Long categoryId);

	Category findByAttributesLclCdAndAttributesCategoryDescAndHierarchyCode(String lcl, String categoryDesc, String hierarchyCode);
}
