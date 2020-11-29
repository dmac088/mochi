package io.nzbee.entity.product.attribute;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IProductAttributeRepository extends CrudRepository<ProductAttributeEntity, Long> {

	List<ProductAttributeEntity> findAll();

	Optional<ProductAttributeEntity> findByLclCdAndProductProductUPC(String locale, String productUPC);
}
