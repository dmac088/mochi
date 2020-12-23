package io.nzbee.test.unit.domain.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.product.PhysicalProduct;
import io.nzbee.domain.promotion.Promotion;

@Service("ut")
@Profile(value = "tst")
public class ProductDoBeanFactory {

	@Autowired
	private BrandDoBeanFactory brandDoBeanFactory;
	
	@Autowired 
	private DepartmentDoBeanFactory departmentDoBeanFactory;
	
	@Autowired 
	private CategoryDoBeanFactory categoryDoBeanFactory;
	
	@Autowired 
	private PromotionDoBeanFactory promotionDoBeanFactory;
	
	public final PhysicalProduct getInStockPhysicalProductDoBean() {
				
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
								   brandDoBeanFactory.getBrandDoBean(),
								   departmentDoBeanFactory.getDepartmentDoBean(),
								   new ArrayList<ProductCategory>(Arrays.asList(categoryDoBeanFactory.getProductCategoryDoBean())),
								   new ArrayList<Promotion>(Arrays.asList(promotionDoBeanFactory.getPromotionDoBean())));
	}
	
	public final PhysicalProduct getOutOfStockPhysicalProductDoBean() {
		
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
								   brandDoBeanFactory.getBrandDoBean(),
								   departmentDoBeanFactory.getDepartmentDoBean(),
								   new ArrayList<ProductCategory>(Arrays.asList(categoryDoBeanFactory.getProductCategoryDoBean())),
								   new ArrayList<Promotion>(Arrays.asList(promotionDoBeanFactory.getPromotionDoBean())));
	}
	
}
