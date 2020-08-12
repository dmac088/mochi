package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.product.Food;
import io.nzbee.domain.product.Product;

@Service
@Profile(value = "tst")
public class ProductDoBeanFactory {
	
	public final Product getProductDoBean() {
		
		return new Food(		   "3254354673",
								   new Date(),
								   "ACT01",
								   "Test Product Description",
								   new Double(78),
								   new Double(71),
								   "test_image.jpg",
								   "NZL",
								   new Date(),
								   "en-GB",
								   "HKD",
								   new Brand("ENZ01",
											 "Enza",
											 20,
											 "en-GB"),
								   new Department("FOO01",
												  "Food",
												  "en-GB"),
								   new ProductCategory("FRT01", 
										   			   "test category", 
										   			   true, 
										   			   new Long(1), 
										   			   0, 
										   			   "PNT01", 
										   			   new Long(10),
										   			   "en-GB"));
	}
}
