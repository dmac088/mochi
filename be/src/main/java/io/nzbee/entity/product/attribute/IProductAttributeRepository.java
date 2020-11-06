package io.nzbee.entity.product.attribute;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IProductAttributeRepository extends CrudRepository<ProductAttributeEntity, Long> {

	Set<ProductAttributeEntity> findAll();

	Optional<ProductAttributeEntity> findByProductProductUPC(String productUPC);
}
