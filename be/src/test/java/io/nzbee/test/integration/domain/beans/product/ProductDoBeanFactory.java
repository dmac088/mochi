package io.nzbee.test.integration.domain.beans.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.test.integration.domain.beans.category.ICategoryDoBeanFactory;
import io.nzbee.test.integration.domain.beans.department.IDepartmentDoBeanFactory;
import io.nzbee.test.integration.domain.beans.promotion.IPromotionDoBeanFactory;

@Service
@Profile(value = "it")
public class ProductDoBeanFactory implements IProductDoBeanFactory {
	
	@Autowired
	private ICategoryDoBeanFactory categoryDoBeanFactory;
	
	
	@Autowired
	private IDepartmentDoBeanFactory departmentDoBeanFactory;
	
	@Autowired
	private IPromotionDoBeanFactory promotionDoBeanFactory;
	
	
	@Override
	public Product getBean() {
		
		ProductCategory pc1 = (ProductCategory) categoryDoBeanFactory.getPomegranateBean();
		
		ProductCategory pc2 = (ProductCategory) categoryDoBeanFactory.getCitrusBean();
		
	
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
								   new Double(0.24),
								   department,
								   new ArrayList<ProductCategory>(Arrays.asList(pc1,pc2)),
								   promos);

	}
}
