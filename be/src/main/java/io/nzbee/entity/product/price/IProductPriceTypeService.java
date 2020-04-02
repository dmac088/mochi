package io.nzbee.entity.product.price;

import java.util.List;
import java.util.Optional;

public interface IProductPriceTypeService {

	List<ProductPriceType> findAll();

	Optional<ProductPriceType> findById(Long Id);
	
	Optional<ProductPriceType> findByCode(String code);

}
