package io.nzbee.entity.product.shipping.type.attribute;

import java.util.Optional;
import io.nzbee.entity.IService;

public interface IShippingTypeAttributeService extends IService<ShippingTypeAttributeEntity> {

	Optional<ShippingTypeAttributeEntity> findByCode(String locale, String code);
	
}
