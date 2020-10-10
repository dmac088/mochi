package io.nzbee.test.integration.beans;

import java.time.LocalDateTime;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.product.Accessories;
import io.nzbee.domain.product.Product;

@Service
@Profile(value = "tst")
public class ProductDoBeanFactory {
	
	public final Product getProductDoBean() {
		
		return new Accessories(	"3254354673",
								   LocalDateTime.now(),
								   "ACT01",
								   "Test Product Description",
								   "Test Product Long Description",
								   new Double(78),
								   new Double(71),
								   "test_image.jpg",
								   "en-GB",
								   "HKD",
								   true,
								   new Brand("ENZ01",
											 "Enza",
											 20,
											 "en-GB"),
								   new Department("ACC01",
												  "Accessories",
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
