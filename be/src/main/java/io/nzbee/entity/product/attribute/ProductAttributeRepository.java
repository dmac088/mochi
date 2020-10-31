package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductAttributeRepository extends CrudRepository<ProductAttributeEntity, Long> {

	List<ProductAttributeEntity> findAll();

	List<ProductAttributeEntity> findByLclCd(String lcl);

	Optional<ProductAttributeEntity> findByLclCdAndProductProductId(String lcl, Long id);
	
	Optional<ProductAttributeEntity> findByLclCdAndProductProductUPC(String lcl, String code);

}
