package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductAttributeRepository extends CrudRepository<ProductAttribute, Long> {

	List<ProductAttribute> findAll();

	List<ProductAttribute> findByLclCd(String lcl);

	Optional<ProductAttribute> findByLclCdAndProductProductId(String lcl, Long id);
	
	Optional<ProductAttribute> findByLclCdAndProductProductUPC(String lcl, String code);

}
