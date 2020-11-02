package io.nzbee.test.integration.beans;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;


@Service
@Profile(value = "tst")
public class CategoryDoBeanFactory {

	
	public final ProductCategory getProductCategoryDoBean() {
		
	
		
		ProductCategory category = 
				new ProductCategory("TST01",
									"test product category",
									true,
									new Long(2),
									new Long(0),									
									"FRT01",
									new Long(10),
									"en-GB");
		
		return category;
	}
	
	
	public final BrandCategory getBrandCategoryDoBean() {
		return new BrandCategory( 	"TST02",
									"test brand category",
									false,
									new Long(0),
									"en-GB");
		
	}
	
	
	@Bean
	public final List<Category> getProductCategoryDoListBean() {
		List<Category> lc = new ArrayList<Category>();
		
		final Category category = this.getProductCategoryDoBean();
		
		lc.add(category);
		
		return lc;
	}
	
	
}
