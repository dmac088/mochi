package io.nzbee.domain.product.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IProductPortService;

public class ShippingProductServiceImpl implements IShippingProductService {

	@Autowired
	private IProductPortService productService;
	
	@Override
	public ShippingProduct findByDestinationAndTypeAndWeight(String locale, String currency, String destinationCode, String type, Double weightKg) {
		return (ShippingProduct) productService.findByDestinationAndTypeAndWeight(locale, currency, destinationCode, type, weightKg);
	}

}
