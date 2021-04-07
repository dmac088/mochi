package io.nzbee.entity.product.shipping;

import org.springframework.stereotype.Component;

import io.nzbee.view.product.shipping.ShippingProductView;

@Component(value="shippingProductViewMapper")
public class ShippingProductViewMapperImpl implements IShippingProductViewMapper {

	@Override
	public ShippingProductView DTOToView(ShippingProductDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShippingProductEntity viewToEntity(ShippingProductView d) {
		// TODO Auto-generated method stub
		return null;
	}

}
