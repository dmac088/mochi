package io.nzbee.entity.product.shipping.type;

import org.springframework.stereotype.Component;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

@Component
public class ShippingTypeViewMapperImpl implements IShippingTypeViewMapper {

	@Override
	public ShippingTypeView DTOToView(ShippingTypeDTO dto) {
		io.nzbee.view.product.shipping.type.ShippingTypeView sp = new io.nzbee.view.product.shipping.type.ShippingTypeView();
		sp.setShippingTypeCode(dto.getShippingTypeCode());
		sp.setShippingTypeDesc(dto.getShippingTypeDesc());
		return sp;
	}

	@Override
	public ShippingTypeEntity viewToEntity(ShippingTypeView d) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
