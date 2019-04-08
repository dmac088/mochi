package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryAttributeRepository extends CrudRepository<ProductCategoryAttribute, Long> {

	List<ProductCategoryAttribute> findAll();
	
	List<ProductCategoryAttribute> findByLclCd(String lcl);

	Optional<ProductCategoryAttribute> findByLclCdAndCategoryId(String lcl, Long categoryId);
	
	List<ProductCategoryAttribute> findByLclCdAndProductCategoryParentCategoryId(String lcl, Long categoryId);

	ProductCategoryAttribute findByCategoryDesc(String categoryDesc);
}
