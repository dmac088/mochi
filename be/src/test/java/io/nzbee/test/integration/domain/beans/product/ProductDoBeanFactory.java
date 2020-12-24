package io.nzbee.test.integration.domain.beans.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import io.nzbee.domain.product.PhysicalProduct;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.promotion.IPromotionService;
import io.nzbee.domain.promotion.Promotion;

@Service
@Profile(value = "it")
public class ProductDoBeanFactory implements IProductDoBeanFactory {
	
	@Autowired
	private ICategoryService cs;
	
	@Autowired
	private IBrandService bs;
	
	@Autowired
	private IDepartmentService ds;
	
	@Autowired
	private IPromotionService ps;
	
	
	@Override
	public final Product getBean() {
		
		ProductCategory pc1 = (ProductCategory) cs.findByCode(Constants.localeENGB, "POM01");
		
		ProductCategory pc2 = (ProductCategory) cs.findByCode(Constants.localeENGB, "CIT01");
		
		Brand brand = bs.findByCode(Constants.localeENGB, "ENZ01");
		
		Department department = ds.findByCode(Constants.localeENGB, "ACC01");
		
		Promotion promotion = ps.findByCode(Constants.localeENGB, "B2G50");
		
		List<Promotion> promos = new ArrayList<Promotion>();
		
		promos.add(promotion);
		
		return new PhysicalProduct("3254354673",
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
								   new ArrayList<ProductCategory>(Arrays.asList(pc1,pc2)),
								   promos);
	}
}
