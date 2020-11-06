package io.nzbee.entity.product.attribute;

import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IProductAttributeService extends IDao<ProductAttributeEntity> {
	
	Optional<ProductAttributeEntity> findByCode(String locale, String code);
	
}
