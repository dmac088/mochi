package io.nzbee.entity.product.physical.light;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import io.nzbee.Constants;
import io.nzbee.entity.product.physical.PhysicalProductEntity;

public interface IPhysicalProductLightRepository extends CrudRepository<PhysicalProductEntity, Long> {
		
	
	@Query(	  " SELECT new  io.nzbee.entity.product.physical.light.PhysicalProductLightDTO(	"
			+ "															 pp.productUPC, 	"
			+ "															 at.productDesc, 	"
			+ "															 ba.brandDesc, 		"
			+ "															 MAX(CASE 			"
			+ "															 WHEN t.code 		= '" + Constants.retailPriceCode +"'"
			+ "															 THEN p.priceValue 	"
			+ "															 ELSE 0 			"
			+ "															 END),				"
			+ "															 MAX(CASE 			"
			+ "															 WHEN t.code 		= '" + Constants.markdownPriceCode +"'"
			+ "															 THEN p.priceValue 	"
			+ "															 ELSE 0 			"		
			+ "															 END),				"
			+ " 														 coalesce(soh.stockOnHand, 0) > 0, "	
			+ "															 at.ProductImage 	"
			+ ") "
			+ " FROM PhysicalProductEntity pp "
			
			+ " JOIN pp.attributes at "
			
			+ " JOIN pp.brand b "
			
			+ " JOIN b.attributes ba"
			
			+ " JOIN pp.productStatus s "
			
			+ " LEFT JOIN pp.stockOnHand soh "
			
			//prices
			+ " JOIN pp.prices p "
			
			//price type 
			+ " JOIN p.type t "
			
			//currency
			+ " JOIN p.currency c "
			
			+ " WHERE at.lclCd 			= :locale "
			+ " AND ba.lclCd 			= :locale "
			+ " AND c.code 				= :currency "
			+ " AND pp.productUPC 		in :productCodes "
			+ " AND s.productStatusCode = '" + Constants.activeSKUCode + "'"
			+ " GROUP BY pp.productUPC, "
			+ " at.productDesc,"
			+ " ba.brandDesc,"
			+ " at.ProductImage, "
			+ " soh.stockOnHand ")
	List<PhysicalProductLightDTO> findAll(String locale, String currency, Set<String> productCodes);
	
}

 