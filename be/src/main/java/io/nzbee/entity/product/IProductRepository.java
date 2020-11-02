package io.nzbee.entity.product;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IProductRepository  extends CrudRepository<ProductEntity, Long> {

	Set<ProductEntity> findAll();
	
}
