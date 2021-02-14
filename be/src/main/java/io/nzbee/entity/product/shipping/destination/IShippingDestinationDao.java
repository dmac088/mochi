package io.nzbee.entity.product.shipping.destination;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface IShippingDestinationDao extends ILocalizedDao<ShippingDestinationDTO, ShippingDestinationEntity> {
	
	List<ShippingDestinationDTO> findAll(String locale,  Set<String> PromotionCodes); 
	
	Optional<ShippingDestinationDTO> findByProductCode(String locale, String productCode);

	List<ShippingDestinationDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	List<ShippingDestinationDTO> findAllByCategory(String locale, String categoryCode);

	Optional<ShippingDestinationEntity> findByCode(String code);
}
