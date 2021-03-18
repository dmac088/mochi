package io.nzbee.view.product.shipping.destination;

import org.springframework.stereotype.Component;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationDTO;

@Component
public class ShippingDestinationDTOMapperImpl implements IShippingDestinationDTOMapper {

	@Override
	public io.nzbee.view.product.shipping.destination.ShippingDestinationDTO doToDto(ShippingDestinationDTO d) {
		io.nzbee.view.product.shipping.destination.ShippingDestinationDTO sp = new io.nzbee.view.product.shipping.destination.ShippingDestinationDTO();
		sp.setProductDestinationCode(d.getShippingDestinationCode());
		sp.setProductDestinationDesc(d.getShippingDestinationDesc());
		return sp;
	}

	@Override
	public ShippingDestinationDTO dtoToDo(io.nzbee.view.product.shipping.destination.ShippingDestinationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
