package io.nzbee.entity.product.shipping.destination;

import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IShippingDestinationDao extends IDao<ShippingDestinationDTO> {

	Optional<ShippingDestinationEntity> findByCode(String code);

	Optional<ShippingDestinationEntity> findById(Long id);

	Optional<ShippingDestinationDTO> findByCode(String locale, String code);
	
	
}
