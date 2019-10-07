package io.nzbee.entity.product.attribute;

import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IProductAttributeService extends IDao<ProductAttribute> {
	
	Optional<ProductAttribute> getProductAttributeEN(Long id);
	
	Optional<ProductAttribute> getProductAttributeHK(Long id);

	Optional<ProductAttribute> getProductAttribute(Long id, String locale);
}
