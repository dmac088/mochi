package io.javabrains.springbootstarter.domain;


import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryAttributeRepository extends CrudRepository<ProductCategoryAttribute, Long> {

	ArrayList<ProductCategoryAttribute> findAll();
	
	ArrayList<ProductCategoryAttribute> findByLclCd(String lcl);

	Optional<ProductCategoryAttribute> findByCategoryId(Long id);

}
