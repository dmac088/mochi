package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.attribute.ProductAttribute;

@Service(value = "productEntityBeanFactory")
@Profile(value = "dev")
public class ProductEntityBeanFactory {

	@Bean
	public final Product getProductEntityBean() {
		
		Product product = new Product();
		product.setProductCreateDt(new Date());
		product.setUPC("123456789");
		
		return product;
	}
	
	@Bean
	public final ProductAttribute getProductAttributeEntityBean() {
		
		Product p = new Product();
		ProductAttribute paEng = new ProductAttribute();
		paEng.setProductDesc("test product");
		paEng.setLclCd("en-GB");
		paEng.setProduct(p);
		
		p.setProductCreateDt(new Date());
		p.setUPC("123456789");
		
		p.addProductAttribute(paEng);
		p.getAttributes().add(paEng);
		
		return paEng;
	}
	
}
