package io.nzbee.entity.product.price;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.variables.ProductVars;

@Service
public class ProductPriceService {

	@Autowired
	private ProductPriceRepository productPriceRepository; 
	
	public List<ProductPrice> getProductPrices() {
		return productPriceRepository.findAll();
	}

	public Optional<ProductPrice> getProductPrice(Long productId, 
										String priceTypeCode,
										Date priceDateA,
										Date priceDateB,
										String currencyCode
										) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				priceTypeCode,
				priceDateA,
				priceDateB,
				currencyCode
		);
	}
	
	public Optional<ProductPrice> getCurrentRetailPriceUSD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_RETAIL_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_US
		);
	}

	public Optional<ProductPrice> getCurrentMarkdownPriceUSD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_MARKDOWN_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_US
		);
	}
	
	public Optional<ProductPrice> getCurrentRetailPriceHKD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_RETAIL_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_HONG_KONG 
		);
	}
	
	public Optional<ProductPrice> getCurrentMarkdownPriceHKD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_MARKDOWN_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_HONG_KONG 
		);
	}
}
