package io.nzbee.entity.product.shipping.destination;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IShippingDestinationRepository extends CrudRepository<ShippingDestinationEntity, Long> {
		
	Optional<ShippingDestinationEntity> findByAttributesLclCdAndAttributesShippingDestinationDesc(String locale, String shippingDestinationDesc);
}

 