package io.javabrains.springbootstarter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ProductAttributeRepository extends CrudRepository<ProductAttribute, Long> {

	List<ProductAttribute> findAll();

	List<ProductAttribute> findAllByLclCd(String lcl);

	Optional<ProductAttribute> findByProductId(Long id);

}
