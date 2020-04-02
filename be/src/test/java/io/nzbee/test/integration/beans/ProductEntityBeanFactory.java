package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyService;
import io.nzbee.entity.product.food.Food;
import io.nzbee.entity.product.price.IProductPriceTypeService;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.price.ProductPriceType;

@Service(value = "productEntityBeanFactory")
@Profile(value = "dev")
public class ProductEntityBeanFactory {

	@Autowired
	private ICurrencyService currencyService;
	
	@Autowired 
	private IProductPriceTypeService productPriceTypeService; 
	
	@Bean
	public final Product getProductEntityBean() {
	
		Food product = new Food();
		product.setProductCreateDt(new Date());
		product.setUPC("123456789");
		product.setExpiryDate(new Date());
		product.setCountryOfOrigin("NZL");
		
		ProductAttribute paEng = new ProductAttribute();
		paEng.setProductDesc("test product");
		paEng.setLclCd("en-GB");
		paEng.setProduct(product);
		product.addProductAttribute(paEng);
		
		ProductAttribute paCn = new ProductAttribute();
		paCn.setProductDesc("測試產品");
		paCn.setLclCd("zh-HK");
		paCn.setProduct(product);
		product.addProductAttribute(paCn);

		ProductPriceType ppt = productPriceTypeService.findByCode("RET01").get();
		Currency currHKD = currencyService.findByCode("HKD").get();
		Currency currUSD = currencyService.findByCode("USD").get();
		ProductPrice priceHKD = new ProductPrice();
		ProductPrice priceUSD = new ProductPrice();
		priceHKD.setType(ppt);
		priceUSD.setType(ppt);
		priceHKD.setCurrency(currHKD);
		priceHKD.setPriceValue(new Double(78));
		priceUSD.setCurrency(currUSD);
		priceUSD.setPriceValue(new Double(7.8));
		product.getPrices().add(priceHKD);
		product.getPrices().add(priceUSD);
		priceHKD.setProduct(product);
		priceUSD.setProduct(product);
		
		return product;
	}
	
	@Bean
	public final ProductAttribute getProductAttributeEntityBean() {
		
		Food product = new Food();
		ProductAttribute paEng = new ProductAttribute();
		paEng.setProductDesc("test product");
		paEng.setLclCd("en-GB");
		paEng.setProduct(product);
		
		ProductAttribute paCn = new ProductAttribute();
		paCn.setProductDesc("測試產品");
		paCn.setLclCd("zh-HK");
		paCn.setProduct(product);
		
		product.setProductCreateDt(new Date());
		product.setExpiryDate(new Date());
		product.setUPC("123456789");
		
		product.addProductAttribute(paEng);
		product.setProductAttribute(paEng);
		product.addProductAttribute(paCn);
		
		ProductPriceType ppt = productPriceTypeService.findByCode("RET01").get();
		Currency currHKD = currencyService.findByCode("HKD").get();
		Currency currUSD = currencyService.findByCode("USD").get();
		ProductPrice priceHKD = new ProductPrice();
		ProductPrice priceUSD = new ProductPrice();
		priceHKD.setType(ppt);
		priceUSD.setType(ppt);
		priceHKD.setCurrency(currHKD);
		priceHKD.setPriceValue(new Double(78));
		priceUSD.setCurrency(currUSD);
		priceUSD.setPriceValue(new Double(7.8));
		product.getPrices().add(priceHKD);
		product.getPrices().add(priceUSD);
		priceHKD.setProduct(product);
		priceUSD.setProduct(product);
		
		return paEng;
	}
	
}
