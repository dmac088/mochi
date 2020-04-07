package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.stereotype.Service;
import io.nzbee.domain.product.Product;

@Service
public class ProductDoBeanFactory {
	
	
	public Product getProductDoBean() {
		return new Product("3254354673",
								   new Date(),
								   "Test Product Description",
								   new Double(78),
								   new Double(71),
								   "test_image.jpg",
								   "test, test",
								   "en-GB",
								   "HKD");
	}
		
	
	
}
