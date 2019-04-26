package io.javabrains.springbootstarter.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductAttributeRepository extends CrudRepository<ProductAttribute, Long> {

	List<ProductAttribute> findAll();

	List<ProductAttribute> findByLclCd(String lcl);

	ProductAttribute findByLclCdAndProductId(String lcl, Long id);

}
