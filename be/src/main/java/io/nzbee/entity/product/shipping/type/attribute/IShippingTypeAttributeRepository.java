package io.nzbee.entity.product.shipping.type.attribute;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IShippingTypeAttributeRepository extends CrudRepository<ShippingTypeAttributeEntity, Long> {
		
	Optional<ShippingTypeAttributeEntity> findByLclCdAndShippingTypeShippingTypeCode(String locale, String shippingTypeCode);
	
	Optional<ShippingTypeAttributeEntity> findByLclCdAndShippingTypeDesc(String locale, String shippingTypeDesc);
	
}

 