package io.nzbee.test.integration.domain.beans.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.product.PhysicalProduct;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.test.integration.domain.beans.bag.category.ICategoryDoBeanFactory;
import io.nzbee.test.integration.domain.beans.brand.IBrandDoBeanFactory;
import io.nzbee.test.integration.domain.beans.department.IDepartmentDoBeanFactory;
import io.nzbee.test.integration.domain.beans.promotion.IPromotionDoBeanFactory;

@Service
@Profile(value = "it")
public class ProductDoBeanFactory implements IProductDoBeanFactory {
	
	@Autowired
	private ICategoryDoBeanFactory categoryDoBeanFactory;
	
	@Autowired
	private IBrandDoBeanFactory brandDoBeanFactory;
	
	@Autowired
	private IDepartmentDoBeanFactory departmentDoBeanFactory;
	
	@Autowired
	private IPromotionDoBeanFactory promotionDoBeanFactory;
	
	
	@Override
	public final Product getBean() {
		
		ProductCategory pc1 = (ProductCategory) categoryDoBeanFactory.getPomegranateBean();
		
		ProductCategory pc2 = (ProductCategory) categoryDoBeanFactory.getCitrusBean();
		
		Brand brand = brandDoBeanFactory.getBean();
		
		Department department = departmentDoBeanFactory.getBean();
		
		Promotion promotion = promotionDoBeanFactory.getBean();
		
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
