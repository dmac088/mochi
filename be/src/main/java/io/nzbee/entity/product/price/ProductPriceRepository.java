package io.nzbee.entity.product.price;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

	List<ProductPrice> findAll();
}