package io.nzbee.entity.product.price;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.ILocalizedService;

public interface IProductPriceService extends ILocalizedService<ProductPrice> {
	
	Optional<ProductPrice> findOne( Long productId, 
									String priceTypeCode,
									String currencyCode);
	
	Optional<ProductPrice> findOne(String productCode, 
			String priceTypeCode,
			String currencyCode);

	Optional<ProductPrice> findById(String locale, String currency, long id);

	List<ProductPrice> findAll(String locale, String currency);

	List<ProductPrice> findAll(String locale, String currency, Set<String> codes);

	Optional<ProductPrice> findByCode(String locale, String currency, String code);

	Optional<ProductPrice> findByDesc(String locale, String currency, String desc);
	
}
