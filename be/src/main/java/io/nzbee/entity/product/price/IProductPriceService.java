package io.nzbee.entity.product.price;

import java.util.Optional;
import io.nzbee.entity.IService;

public interface IProductPriceService extends IService<ProductPriceEntity> {
	
	Optional<ProductPriceEntity> findByProductId( Long productId, 
												  String priceTypeCode,
												  String currencyCode);
	
	Optional<ProductPriceEntity> findByProductCode(String productCode, 
												   String priceTypeCode,
												   String currencyCode);

	Optional<ProductPriceEntity> findById(String locale, String currency, Long id);

	
	
}
