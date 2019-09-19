package io.nzbee.entity.category.attribute;


import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryAttributeRepository extends CrudRepository<CategoryAttribute, Long> {

	List<CategoryAttribute> findAll();
	
	List<CategoryAttribute> findByLclCd(String lcl);

	CategoryAttribute findByCategoryHierarchyHierarchyCodeAndLclCdAndCategoryId(String hierarchyCode, String lcl, Long categoryId);
	
	CategoryAttribute findByCategoryHierarchyHierarchyCodeAndLclCdAndCategoryCategoryCode(String hierarchyCode, String lcl, String categoryCode);
	
	CategoryAttribute findByCategoryHierarchyHierarchyCodeAndLclCdAndCategoryDesc(String hierarchyCode, String lcl, String categoryDesc);
	
	List<CategoryAttribute> findByCategoryHierarchyHierarchyCodeAndLclCdAndCategoryParentCategoryId(String hierarchyCode, String lcl, Long categoryId);

}
