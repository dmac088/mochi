package io.nzbee.entity.product.type;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IProductTypeRepository extends CrudRepository<ProductType, Long> {

	Optional<ProductType> findByCode(String code);
	
}
