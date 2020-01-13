package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.Product;

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
	
}
