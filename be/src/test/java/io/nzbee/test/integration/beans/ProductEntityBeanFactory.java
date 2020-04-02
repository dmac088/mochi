package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.currency.Currency;
import io.nzbee.entity.product.currency.ICurrencyRepository;
import io.nzbee.entity.product.food.Food;
import io.nzbee.entity.product.price.ProductPrice;

@Service(value = "productEntityBeanFactory")
@Profile(value = "dev")
public class ProductEntityBeanFactory {

	@Autowired
	ICurrencyRepository currencyRepository;
	
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

		Currency currHKD = currencyRepository.findByCode("HKD").get();
		Currency currUSD = currencyRepository.findByCode("USD").get();
		ProductPrice priceHKD = new ProductPrice();
		ProductPrice priceUSD = new ProductPrice();
		priceHKD.setCurrency(currHKD);
		priceHKD.setPriceValue(new Double(78));
		priceUSD.setCurrency(currUSD);
		priceUSD.setPriceValue(new Double(7.8));
		product.getPrices().add(priceHKD);
		product.getPrices().add(priceUSD);
		
		return product;
	}
	
	@Bean
	public final ProductAttribute getProductAttributeEntityBean() {
		
		Food p = new Food();
		ProductAttribute paEng = new ProductAttribute();
		paEng.setProductDesc("test product");
		paEng.setLclCd("en-GB");
		paEng.setProduct(p);
		
		ProductAttribute paCn = new ProductAttribute();
		paCn.setProductDesc("測試產品");
		paCn.setLclCd("zh-HK");
		paCn.setProduct(p);
		
		p.setProductCreateDt(new Date());
		p.setExpiryDate(new Date());
		p.setUPC("123456789");
		
		p.addProductAttribute(paEng);
		p.setProductAttribute(paEng);
		p.addProductAttribute(paCn);
		
		return paEng;
	}
	
}
