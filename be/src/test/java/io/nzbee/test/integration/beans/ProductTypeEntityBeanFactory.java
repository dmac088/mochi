package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.type.ProductType;

@Service(value = "productTypeEntityBeanFactory")
@Profile(value = "dev")
public class ProductTypeEntityBeanFactory {

	@Bean
	public final ProductType getProductTypeEntityBean() {
		ProductType productType = new ProductType();
		productType.setCode("TST01");
		productType.setDesc("test product type");
		return productType;
	}
	
}
