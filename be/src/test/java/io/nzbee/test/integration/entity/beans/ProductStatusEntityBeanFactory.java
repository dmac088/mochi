package io.nzbee.test.integration.entity.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.product.status.ProductStatusEntity;

@Service(value = "productStatusEntityBeanFactory")
@Profile(value = "tst")
public class ProductStatusEntityBeanFactory {

	@Bean
	public final ProductStatusEntity getProductStatusEntityBean() {
		ProductStatusEntity productStatus = new ProductStatusEntity();
		productStatus.setCode("TST01");
		productStatus.setDesc("test product status");
		return productStatus;
	}
	
}
