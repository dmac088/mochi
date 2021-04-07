package io.nzbee.view.ports;
import java.util.Set;

import org.springframework.data.domain.Page;

import io.nzbee.domain.product.shipping.ShippingProduct;
import io.nzbee.view.product.shipping.ShippingProductView;

public interface IShippingProductPortService extends IPortService<ShippingProductView> {

	Page<ShippingProduct> findAllShippingProducts(String locale, String currency, String categoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page,
			String size, String sort);

}
