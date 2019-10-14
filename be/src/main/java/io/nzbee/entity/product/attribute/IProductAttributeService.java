package io.nzbee.entity.product.attribute;

import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface IProductAttributeService extends ILocalizedDao<ProductAttribute> {
	
	Optional<ProductAttribute> getProductAttributeEN(Long id);
	
	Optional<ProductAttribute> getProductAttributeHK(Long id);

	Optional<ProductAttribute> getProductAttribute(Long id, String locale);
}
