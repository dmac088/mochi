package io.nzbee.entity.product.shipping.destination;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.ILightLocalizedService;

public interface IShippingDestinationService extends ILightLocalizedService<ShippingDestinationDTO, ShippingDestinationEntity> {

	List<ShippingDestinationDTO> findAllActive(String locale);

	List<ShippingDestinationDTO> findAllActiveByBagWeight(String locale, Double bagWeight);

	Optional<ShippingDestinationEntity> findByCode(String code);

	Optional<ShippingDestinationEntity> findById(Long id);
	
}
