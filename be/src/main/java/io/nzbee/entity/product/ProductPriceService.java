package io.nzbee.entity.product;

import java.util.Date;
import java.util.List;
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

	public ProductPrice getProductPrice(Long productId, 
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
	
	public ProductPrice getCurrentRetailPriceUSD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_RETAIL_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_US
		);
	}

	public ProductPrice getCurrentMarkdownPriceUSD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_MARKDOWN_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_US
		);
	}
	
	public ProductPrice getCurrentRetailPriceHKD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_RETAIL_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_HONG_KONG 
		);
	}
	
	public ProductPrice getCurrentMarkdownPriceHKD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_MARKDOWN_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_HONG_KONG 
		);
	}
}
