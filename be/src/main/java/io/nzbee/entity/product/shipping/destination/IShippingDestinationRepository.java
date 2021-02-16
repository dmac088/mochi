package io.nzbee.entity.product.shipping.destination;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IShippingDestinationRepository extends CrudRepository<ShippingDestinationEntity, Long> {
		
	Optional<ShippingDestinationEntity> findByAttributesLclCdAndAttributesShippingDestinationDesc(String locale, String shippingDestinationDesc);
	
	Optional<ShippingDestinationEntity> findByShippingDestinationCode(String code);
	
	@Query(	  " SELECT new io.nzbee.entity.product.shipping.destination.ShippingDestinationDTO("
			+ "															 sd.shippingDestinationId, "
			+ "															 sd.shippingDestinationCode, "
			+ "															 sd.shippingDestinationZoneCode, "
			+ "															 at.shippingDestinationDesc, "
			+ "															 at.lclCd "		
			+ ") "
			+ " FROM ShippingDestinationEntity sd "
			+ " JOIN sd.attributes at "
			+ " WHERE sd.shippingDestinationCode = :shippingDestinationCode "
			+ " AND at.lclCd = :locale")
	Optional<ShippingDestinationDTO> findByCode(String locale, String shippingDestinationCode);
	
}

 