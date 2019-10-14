package io.nzbee.entity.product.price;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.variables.ProductVars;

@Service
public class ProductPriceServiceImpl implements IProductPriceService {

	@Autowired
	private ProductPriceRepository productPriceRepository; 
	
	@Override
	public Optional<ProductPrice> findById(String locale, String currency, long id) {
		// TODO Auto-generated method stub
		return productPriceRepository.findById(id);
	}

	@Override
	public List<ProductPrice> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return productPriceRepository.findAll();
	}
	

	@Override
	public List<ProductPrice> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return productPriceRepository.findAll();
	}
	
	@Override
	public Optional<ProductPrice> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductPrice> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ProductPrice t) {
		// TODO Auto-generated method stub
		productPriceRepository.save(t);
	}

	@Override
	public void update(ProductPrice t) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public void update(ProductPrice t, String[] params) {
//		// TODO Auto-generated method stub
//		productPriceRepository.save(t);
//	}

	@Override
	public void delete(ProductPrice t) {
		// TODO Auto-generated method stub
		productPriceRepository.delete(t);
	}

	@Override
	public Optional<ProductPrice> get(Long productId, String priceTypeCode, Date priceDateA, Date priceDateB,
			String currencyCode) {
		// TODO Auto-generated method stub
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				priceTypeCode,
				priceDateA,
				priceDateB,
				currencyCode
		);
	}
	
	@Override
	public Optional<ProductPrice> getCurrentRetailPriceUSD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_RETAIL_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_US
		);
	}

	@Override
	public Optional<ProductPrice> getCurrentMarkdownPriceUSD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_MARKDOWN_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_US
		);
	}
	
	@Override
	public Optional<ProductPrice> getCurrentRetailPriceHKD(Long productId) {
		return productPriceRepository.findByProductProductIdAndTypeCodeAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndCurrencyCode(
				productId, 
				ProductVars.PRICE_RETAIL_CODE,
				new Date(),
				new Date(),
				ProductVars.CURRENCY_HONG_KONG 
		);
	}
	
	@Override
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
