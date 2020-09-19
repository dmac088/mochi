package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.status.ProductStatus;

@Service(value = "productStatusEntityBeanFactory")
@Profile(value = "tst")
public class ProductStatusEntityBeanFactory {

	@Bean
	public final ProductStatus getProductStatusEntityBean() {
		ProductStatus productStatus = new ProductStatus();
		productStatus.setCode("TST01");
		productStatus.setDesc("test product status");
		return productStatus;
	}
	
}