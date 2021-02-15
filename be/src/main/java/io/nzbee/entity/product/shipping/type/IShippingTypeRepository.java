package io.nzbee.entity.product.shipping.type;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IShippingTypeRepository extends CrudRepository<ShippingTypeEntity, Long> {
		
	Optional<ShippingTypeEntity> findByAttributesLclCdAndAttributesShippingTypeDesc(String locale, String shippingTypeDesc);
	
	Optional<ShippingTypeEntity> findByShippingTypeCode(String code); 
	
	@Query(	  " SELECT new io.nzbee.entity.product.shipping.ShippingTypeDTO("
			+ "															 sd.shippingTypeId, "
			+ "															 sd.shippingTypeCode, "
			+ "															 sd.shippingTypeZoneCode, "
			+ "															 at.shippingTypeDesc, "
			+ "															 at.lclCd "		
			+ ") "
			+ " FROM ShippingTypeEntity sd "
			+ " JOIN sd.attributes at "
			+ " WHERE sd.shippingTypeCode = :shippingTypeCode "
			+ " AND at.lclCd = :locale")
	Optional<ShippingTypeDTO> findByCode(String locale, String shippingTypeCode);
	
}

 