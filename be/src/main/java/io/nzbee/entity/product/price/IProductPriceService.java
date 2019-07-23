package io.nzbee.entity.product.price;

import java.util.Date;
import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IProductPriceService extends IDao<ProductPrice> {
	
	Optional<ProductPrice> get(Long productId, 
			String priceTypeCode,
			Date priceDateA,
			Date priceDateB,
			String currencyCode
			);
	
	Optional<ProductPrice> getCurrentMarkdownPriceUSD(Long productId);
	
	Optional<ProductPrice> getCurrentMarkdownPriceHKD(Long productId);
	
	Optional<ProductPrice> getCurrentRetailPriceHKD(Long productId);
	
	Optional<ProductPrice> getCurrentRetailPriceUSD(Long productId);
	
}
