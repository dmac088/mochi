package io.javabrains.springbootstarter.domain;


import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CategoryAttributeRepository extends CrudRepository<CategoryAttribute, Long> {

	List<CategoryAttribute> findAll();
	
	List<CategoryAttribute> findByLclCd(String lcl);

	CategoryAttribute findByLclCdAndCategoryId(String lcl, Long categoryId);
	
	CategoryAttribute findByLclCdAndCategoryCategoryCode(String lcl, String categoryCode);
	
	List<CategoryAttribute> findByLclCdAndCategoryParentCategoryId(String lcl, Long categoryId);

	CategoryAttribute findByCategoryDesc(String categoryDesc);
}
