package io.javabrains.springbootstarter.entity;


import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryAttributeRepository extends CrudRepository<CategoryAttribute, Long> {

	List<CategoryAttribute> findAll();
	
	List<CategoryAttribute> findByLclCd(String lcl);

	CategoryAttribute findByLclCdAndCategoryId(String lcl, Long categoryId);
	
	CategoryAttribute findByLclCdAndCategoryCategoryCode(String lcl, String categoryCode);
	
	CategoryAttribute findByLclCdAndCategoryDesc(String lcl, String categoryDesc);
	
	List<CategoryAttribute> findByLclCdAndCategoryParentCategoryId(String lcl, Long categoryId);

	CategoryAttribute findByCategoryDesc(String categoryDesc);
}
