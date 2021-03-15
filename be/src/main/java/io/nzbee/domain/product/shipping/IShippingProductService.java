package io.nzbee.domain.product.shipping;

import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.domain.ILocalizedService;

public interface IShippingProductService extends ILocalizedService<ShippingProduct> {


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

	

}
