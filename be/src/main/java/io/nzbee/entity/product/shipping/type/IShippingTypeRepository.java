package io.nzbee.entity.product.shipping.type;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IShippingTypeRepository extends CrudRepository<ShippingTypeEntity, Long> {
		
	Optional<ShippingTypeEntity> findByAttributesLclCdAndAttributesShippingTypeDesc(String locale, String shippingTypeDesc);
	
	Optional<ShippingTypeEntity> findByShippingTypeCode(String code); 
	
	@Query(	  " SELECT DISTINCT new io.nzbee.entity.product.shipping.type.ShippingTypeDTO("
			+ "															 sd.shippingTypeId, "
			+ "															 sd.shippingTypeCode, "
			+ "															 at.shippingTypeDesc, "
			+ "															 at.lclCd "		
			+ ") "
			+ " FROM ShippingTypeEntity sd "
			+ " JOIN sd.products p"
			+ " JOIN sd.attributes at "
			+ " WHERE sd.shippingTypeCode = :shippingTypeCode "
			+ " AND at.lclCd = :locale")
	Optional<ShippingTypeDTO> findByCode(String locale, String shippingTypeCode);
	
	@Query(	  " SELECT DISTINCT new io.nzbee.entity.product.shipping.type.ShippingTypeDTO("
			+ "															 sd.shippingTypeId, "
			+ "															 sd.shippingTypeCode, "
			+ "															 at.shippingTypeDesc, "
			+ "															 at.lclCd "		
			+ ") "
			+ " FROM ShippingTypeEntity sd "
			+ " JOIN sd.products p"
			+ " JOIN sd.attributes at "
			+ " WHERE at.shippingTypeDesc = :shippingTypeDesc "
			+ " AND at.lclCd = :locale")
	Optional<ShippingTypeDTO> findByDesc(String locale, String shippingTypeDesc);

	@Query(	  " SELECT DISTINCT new io.nzbee.entity.product.shipping.type.ShippingTypeDTO("
			+ "															 sd.shippingTypeId, "
			+ "															 sd.shippingTypeCode, "
			+ "															 at.shippingTypeDesc, "
			+ "															 at.lclCd "		
			+ ") "
			+ " FROM ShippingTypeEntity sd "
			+ " JOIN sd.products p"
			+ " JOIN sd.attributes at "
			+ " WHERE at.lclCd = :locale")
	List<ShippingTypeDTO> findAll(String locale);
	
	@Query(	  " SELECT DISTINCT new io.nzbee.entity.product.shipping.type.ShippingTypeDTO("
			+ "															 st.shippingTypeId, "
			+ "															 st.shippingTypeCode, "
			+ "															 at.shippingTypeDesc, "
			+ "															 at.lclCd "		
			+ ") "
			+ " FROM ShippingTypeEntity st "
			+ " JOIN st.products p"
			+ " JOIN p.shippingDestination sd "
			+ " JOIN st.attributes at "
			+ " WHERE at.lclCd = :locale "
			+ " AND sd.shippingDestinationCode = :destinationCode")
	List<ShippingTypeDTO> findAllByDestinationCode(String locale, String destinationCode);
	
}

 