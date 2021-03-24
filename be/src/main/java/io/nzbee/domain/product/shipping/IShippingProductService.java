package io.nzbee.domain.product.shipping;

import java.util.Set;
import org.springframework.data.domain.Page;

public interface IShippingProductService {

	Page<ShippingProduct> findAll(	String locale, 
									String currency, 
									String categoryCode,
									Set<String> categoryCodes,
									Set<String> brandCodes,
									Set<String> tagCodes,
									Double maxPrice,
									String page, 
									String size, 
									String sort);

	ShippingProduct findByDestinationAndTypeAndWeight(String locale, String currency, String destinationCode,
			String type, Double weightKg);

}
