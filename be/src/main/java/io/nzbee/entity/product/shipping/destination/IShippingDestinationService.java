package io.nzbee.entity.product.shipping.destination;

import java.util.List;

import io.nzbee.entity.ILocalizedService;

public interface IShippingDestinationService extends ILocalizedService<ShippingDestinationDTO, ShippingDestinationEntity> {

	List<ShippingDestinationDTO> findAllActive(String locale);
	
}
