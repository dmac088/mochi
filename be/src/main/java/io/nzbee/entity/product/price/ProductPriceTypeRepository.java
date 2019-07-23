package io.nzbee.entity.product.price;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductPriceTypeRepository extends CrudRepository<ProductPriceType, Long> {

	List<ProductPriceType> findAll();

}