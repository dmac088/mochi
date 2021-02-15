package io.nzbee.entity.product.shipping.type;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface IShippingTypeDao extends ILocalizedDao<ShippingTypeDTO, ShippingTypeEntity> {
	
	List<ShippingTypeDTO> findAll(String locale,  Set<String> PromotionCodes); 
	
	Optional<ShippingTypeDTO> findByProductCode(String locale, String productCode);

	List<ShippingTypeDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> tagCodes, Double maxPrice);

	List<ShippingTypeDTO> findAllByCategory(String locale, String categoryCode);

	Optional<ShippingTypeEntity> findByCode(String code);
}
