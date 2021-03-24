package io.nzbee.test.unit.domain.beans.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.test.unit.domain.beans.brand.IBrandDoBeanFactory;
import io.nzbee.test.unit.domain.beans.category.ICategoryDoBeanFactory;
import io.nzbee.test.unit.domain.beans.department.IDepartmentDoBeanFactory;
import io.nzbee.test.unit.domain.beans.promotion.IPromotionDoBeanFactory;

@Service
@Profile(value = "ut")
public class ProductDoBeanFactory implements IProductDoBeanFactory {

	@Autowired
	private IBrandDoBeanFactory brandDoBeanFactory;
	
	@Autowired 
	private IDepartmentDoBeanFactory departmentDoBeanFactory;
	
	@Autowired 
	private ICategoryDoBeanFactory categoryDoBeanFactory;
	
	@Autowired 
	private IPromotionDoBeanFactory promotionDoBeanFactory;
	
	@Override
	public Product getInStockPhysicalProductDoBean() {
				
		return new PhysicalProduct("3254354673",
								   LocalDateTime.now(),
								   "ACT01",
								   "Test Product Description",
								   "Test Product Long Description",
								   new Double(78),
								   new Double(71),
								   "test_image.jpg",
								   Constants.localeENGB,
								   Constants.currencyHKD,
								   true,
								   new Double(0.72),
								   brandDoBeanFactory.getBean(),
								   departmentDoBeanFactory.getBean(),
								   new ArrayList<ProductCategory>(Arrays.asList(categoryDoBeanFactory.getProductCategoryDoBean())),
								   new ArrayList<Promotion>(Arrays.asList(promotionDoBeanFactory.getBean())));
	}
	
	@Override
	public Product getOutOfStockPhysicalProductDoBean() {
		
		return new PhysicalProduct("3254354673",
								   LocalDateTime.now(),
								   "ACT01",
								   "Test Product Description",
								   "Test Product Long Description",
								   new Double(78),
								   new Double(71),
								   "test_image.jpg",
								   Constants.localeENGB,
								   Constants.currencyHKD,
								   false,
								   new Double(0.52),
								   brandDoBeanFactory.getBean(),
								   departmentDoBeanFactory.getBean(),
								   new ArrayList<ProductCategory>(Arrays.asList(categoryDoBeanFactory.getProductCategoryDoBean())),
								   new ArrayList<Promotion>(Arrays.asList(promotionDoBeanFactory.getBean())));
	}

	@Override
	public Product getBean() {
		return this.getInStockPhysicalProductDoBean();
	}
	
}
