package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryAttributeRepository extends CrudRepository<ProductCategoryAttribute, Long> {

	List<ProductCategoryAttribute> findAll();
	
	List<ProductCategoryAttribute> findAllByLclCd(String lcl);

	Optional<ProductCategoryAttribute> findByLclCdAndCategoryId(String lcl, Long id);

}
