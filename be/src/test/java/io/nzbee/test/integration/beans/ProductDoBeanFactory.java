package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.product.Food;
import io.nzbee.domain.product.Product;

@Service
public class ProductDoBeanFactory {
	
	public Product getProductDoBean() {
		return new Food(		   "3254354673",
								   new Date(),
								   "Test Product Description",
								   new Double(78),
								   new Double(71),
								   "test_image.jpg",
								   "NZL",
								   new Date(),
								   "test, test",
								   "en-GB",
								   "HKD",
								   new Brand("",
											 "",
											 20,
											 "en-GB",
											 "HKD"),
								   new Department("",
												  "",
												  "en-GB",
												  "HKD"));
	}
		
	
	
}
