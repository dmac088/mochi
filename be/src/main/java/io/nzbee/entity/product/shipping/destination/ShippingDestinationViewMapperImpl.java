package io.nzbee.entity.product.shipping.destination;

import org.springframework.stereotype.Component;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationDTO;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;

@Component
public class ShippingDestinationViewMapperImpl implements IShippingDestinationViewMapper {

	@Override
	public ShippingDestinationView DTOToView(ShippingDestinationDTO dto) {
		io.nzbee.view.product.shipping.destination.ShippingDestinationView sp = new io.nzbee.view.product.shipping.destination.ShippingDestinationView();
		sp.setProductDestinationCode(dto.getShippingDestinationCode());
		sp.setProductDestinationDesc(dto.getShippingDestinationDesc());
		return sp;
	}

	@Override
	public ShippingDestinationEntity viewToEntity(ShippingDestinationView d) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
