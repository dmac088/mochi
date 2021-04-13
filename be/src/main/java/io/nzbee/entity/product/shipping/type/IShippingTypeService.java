package io.nzbee.entity.product.shipping.type;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.IService;

public interface IShippingTypeService extends IService<ShippingTypeEntity> {
	
	Optional<ShippingTypeDTO> findByDesc(String locale, String desc);

	Optional<ShippingTypeDTO> findByCode(String locale, String code);

	List<ShippingTypeDTO> findAll(String locale, String destiantionCode);

	List<ShippingTypeDTO> findAll(String locale, String destinationCode, Double bagWeight);

	Optional<ShippingTypeEntity> findByCode(String code);

	Optional<ShippingTypeEntity> findById(Long id);

}
