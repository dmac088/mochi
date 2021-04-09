package io.nzbee.entity.product.shipping.type;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.ILightLocalizedService;

public interface IShippingTypeService extends ILightLocalizedService<ShippingTypeDTO, ShippingTypeEntity> {

	List<ShippingTypeDTO> findAll(String locale, String destiantionCode);

	List<ShippingTypeDTO> findAll(String locale, String destinationCode, Double bagWeight);

	Optional<ShippingTypeEntity> findByCode(String code);

	Optional<ShippingTypeEntity> findById(Long id);

	
}
