package io.nzbee.entity.product.attribute;

import java.util.Optional;
import io.nzbee.entity.IService;

public interface IProductAttributeService extends IService<ProductAttributeEntity> {
	
	Optional<ProductAttributeEntity> findByCode(String locale, String code);
	
}
