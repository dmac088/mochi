package io.javabrains.springbootstarter.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();
	
	Category findByCategoryId(Long id);
	
	Category findByCategoryCode(String categoryCode);
	
	List<Category> findDistinctByCategoryLevelAndCategoryTypeCodeAndHierarchyCode(Long level, String categoryTypeCode, String hierarchyCode);
	
	List<Category> findDistinctByHierarchyCodeAndParentCategoryId(String hieararchyCode, Long categoryId);
	
	List<Category> findDistinctByHierarchyCodeAndParentCategoryIdAndProductsBrandBrandIdIn(String hieararchyCode, Long parentCategoryId, List<Long> brandIds);

	Category findByAttributesLclCdAndAttributesCategoryDescAndHierarchyCode(String lcl, String categoryDesc, String hierarchyCode);
	
	Category findByAttributesLclCdAndAttributesCategoryDesc(String lcl, String categoryDesc);
}
