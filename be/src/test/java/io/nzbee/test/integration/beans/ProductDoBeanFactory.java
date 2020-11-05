package io.nzbee.test.integration.beans;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.department.IDepartmentService;
import io.nzbee.domain.product.BasicProduct;
import io.nzbee.domain.product.Product;

@Service
@Profile(value = "tst")
public class ProductDoBeanFactory {
	
	@Autowired
	private ICategoryService cs;
	
	@Autowired
	private IBrandService bs;
	
	@Autowired
	private IDepartmentService ds;
	
	
	public final Product getProductDoBean() {
		
		ProductCategory pc1 = (ProductCategory) cs.findByCode(Constants.localeENGB, "POM01");
		
		ProductCategory pc2 = (ProductCategory) cs.findByCode(Constants.localeENGB, "CIT01");
		
		Brand brand = bs.findByCode(Constants.localeENGB, "ENZ01");
		
		Department department = ds.findByCode(Constants.localeENGB, "ACC01");
		
		return new BasicProduct(   "3254354673",
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
								   brand,
								   department,
								   new HashSet<ProductCategory>(Arrays.asList(pc1,pc2)));
	}
}
