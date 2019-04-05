package io.javabrains.springbootstarter.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

	List<ProductCategory> findAll();
	
	ProductCategory findByCategoryId(Long id);
	
	List<ProductCategory> findByCategoryLevel(Long level);
	
	List<ProductCategory> findByPreviewFlag(Long previewFlag);
	
	List<ProductCategory> findByParentCategoryId(Long categoryId);

	ProductCategory findByProductCategoryAttributeLclCdAndProductCategoryAttributeCategoryDesc(String lcl, String categoryDesc);
}
