package io.nzbee.view.product.shipping.type;

import org.springframework.stereotype.Component;
import io.nzbee.entity.product.shipping.type.ShippingTypeDTO;

@Component
public class ShippingTypeDTOMapperImpl implements IShippingTypeDTOMapper {

	@Override
	public io.nzbee.view.product.shipping.type.ShippingTypeDTO toDto(ShippingTypeDTO d) {
		io.nzbee.view.product.shipping.type.ShippingTypeDTO sp = new io.nzbee.view.product.shipping.type.ShippingTypeDTO();
		sp.setShippingTypeCode(d.getShippingTypeCode());
		sp.setShippingTypeDesc(d.getShippingTypeDesc());
		return sp;
	}

	@Override
	public ShippingTypeDTO toDo(io.nzbee.view.product.shipping.type.ShippingTypeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
