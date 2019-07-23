package io.nzbee.entity.product.attribute;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IProductAttributeService extends IDao<ProductAttribute> {

	List<ProductAttribute> findAll(String lcl);
	
	Optional<ProductAttribute> findByIdAndLocale(Long id, String lcl);
	
	Optional<ProductAttribute> getProductAttributeEN(Long id);
	
	Optional<ProductAttribute> getProductAttributeHK(Long id);
}
