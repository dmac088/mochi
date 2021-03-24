package io.nzbee.entity.product.shipping.type;

import java.util.List;
import io.nzbee.entity.ILocalizedService;

public interface IShippingTypeService extends ILocalizedService<ShippingTypeDTO, ShippingTypeEntity> {

	List<ShippingTypeDTO> findAll(String locale, String destiantionCode);

	List<ShippingTypeDTO> findAll(String locale, String destinationCode, Double bagWeight);

	
}
