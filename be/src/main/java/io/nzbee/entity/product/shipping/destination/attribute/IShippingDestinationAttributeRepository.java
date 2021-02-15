package io.nzbee.entity.product.shipping.destination.attribute;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IShippingDestinationAttributeRepository extends CrudRepository<ShippingDestinationAttributeEntity, Long> {
		
	Optional<ShippingDestinationAttributeEntity> findByLclCdAndShippingDestinationShippingDestinationCode(String locale, String shippingDestinationCode);
	
	Optional<ShippingDestinationAttributeEntity> findByLclCdAndShippingDestinationDesc(String locale, String shippingDestinationDesc);
	
}

 