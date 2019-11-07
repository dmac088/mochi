package io.nzbee.entity.product.price;

import java.util.Optional;
import io.nzbee.entity.ILocalizedService;

public interface IProductPriceService extends ILocalizedService<ProductPrice> {
	
	Optional<ProductPrice> get(Long productId, 
									String priceTypeCode,
									String currencyCode);
	
}
