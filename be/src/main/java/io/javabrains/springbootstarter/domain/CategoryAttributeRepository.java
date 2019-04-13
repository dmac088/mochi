package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CategoryAttributeRepository extends CrudRepository<CategoryAttribute, Long> {

	List<CategoryAttribute> findAll();
	
	List<CategoryAttribute> findByLclCd(String lcl);

	Optional<CategoryAttribute> findByLclCdAndCategoryId(String lcl, Long categoryId);
	
	List<CategoryAttribute> findByLclCdAndProductCategoryParentCategoryId(String lcl, Long categoryId);

	CategoryAttribute findByCategoryDesc(String categoryDesc);
}
