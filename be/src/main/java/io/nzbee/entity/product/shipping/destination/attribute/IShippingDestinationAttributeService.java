package io.nzbee.entity.product.shipping.destination.attribute;

import java.util.Optional;
import io.nzbee.entity.IService;

public interface IShippingDestinationAttributeService extends IService<ShippingDestinationAttributeEntity> {

	Optional<ShippingDestinationAttributeEntity> findByCode(String locale, String code);
	
}
