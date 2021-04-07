package io.nzbee.entity.product.physical.light;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.product.physical.PhysicalProductEntity;

public interface IPhysicalProductLightRepository extends CrudRepository<PhysicalProductEntity, Long> {
		
	
	@Query(	  " SELECT new io.nzbee.entity.product.shipping.type.ShippingTypeDTO("
			+ "															 sd.shippingTypeId, "
			+ "															 sd.shippingTypeCode, "
			+ "															 at.shippingTypeDesc, "
			+ "															 at.lclCd "		
			+ ") "
			+ " FROM ShippingTypeEntity sd "
			+ " JOIN sd.attributes at "
			+ " WHERE at.lclCd = :locale")
	List<PhysicalProductLightDTO> findAll(String locale, String currency,
			Set<String> productCodes);
	
}

 