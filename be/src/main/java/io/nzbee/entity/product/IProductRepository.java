package io.nzbee.entity.product;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IProductRepository  extends CrudRepository<ProductEntity, Long> {

	List<ProductEntity> findAll();
	
}
