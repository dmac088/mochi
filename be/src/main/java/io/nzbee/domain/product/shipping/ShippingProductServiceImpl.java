package io.nzbee.domain.product.shipping;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import io.nzbee.domain.ports.IProductPortService;

public class ShippingProductServiceImpl implements IShippingProductService {

	@Autowired
	private IProductPortService productService;
	
	@Override
	public Page<ShippingProduct> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort) {

		return productService.findAllShippingProducts(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice, page, size, sort);
	}
	
	@Override
	public ShippingProduct findByDestinationAndTypeAndWeight(String locale, String currency, String destinationCode, String type, Double weightKg) {
		return (ShippingProduct) productService.findByDestinationAndTypeAndWeight(locale, currency, destinationCode, type, weightKg);
	}

}
