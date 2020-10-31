package io.nzbee.entity.product.price;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import io.nzbee.entity.ILocalizedService;

public interface IProductPriceService extends ILocalizedService<ProductPriceDTO, ProductPriceEntity> {
	
	Optional<ProductPriceEntity> findOne( Long productId, 
									String priceTypeCode,
									String currencyCode);
	
	Optional<ProductPriceEntity> findOne(String productCode, 
			String priceTypeCode,
			String currencyCode);

	Optional<ProductPriceEntity> findById(String locale, String currency, Long id);

	List<ProductPriceEntity> findAll(String locale, String currency);

	List<ProductPriceEntity> findAll(String locale, String currency, Set<String> codes);

	Optional<ProductPriceEntity> findByCode(String locale, String currency, String code);

	Optional<ProductPriceEntity> findByDesc(String locale, String currency, String desc);

	
}
