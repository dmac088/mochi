package io.nzbee.test.integration.view.beans.product;

import java.math.BigDecimal;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.view.product.physical.light.PhysicalProductLightView;

@Service
@Profile(value = "it")
public class ProductViewBeanFactory implements IProductViewBeanFactory {
	
	@Override
	public PhysicalProductLightView getBean() {
		
		/*
			String productUPC, String productDesc, BigDecimal productRetail,
			BigDecimal productMarkdown, String productType, String brandDesc, boolean inStock, String productImage
		 */
		
		return new PhysicalProductLightView("3254354673",
										    "Test Product Description",
										    new BigDecimal(78),
										    new BigDecimal(71),
										    "physical",
										    "Test Brand Desc",
										    true,
										    "dummyImage.jpg");
										  
	}
}
