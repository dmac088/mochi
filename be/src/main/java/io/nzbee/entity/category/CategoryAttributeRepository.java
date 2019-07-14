package io.nzbee.entity.category;


import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryAttributeRepository extends CrudRepository<CategoryAttribute, Long> {

	List<CategoryAttribute> findAll();
	
	List<CategoryAttribute> findByLclCd(String lcl);

	CategoryAttribute findByCategoryHierarchyCodeAndLclCdAndCategoryId(String hierarchyCode, String lcl, Long categoryId);
	
	CategoryAttribute findByCategoryHierarchyCodeAndLclCdAndCategoryCategoryCode(String hierarchyCode, String lcl, String categoryCode);
	
	CategoryAttribute findByCategoryHierarchyCodeAndLclCdAndCategoryDesc(String hierarchyCode, String lcl, String categoryDesc);
	
	List<CategoryAttribute> findByCategoryHierarchyCodeAndLclCdAndCategoryParentCategoryId(String hierarchyCode, String lcl, Long categoryId);

}
