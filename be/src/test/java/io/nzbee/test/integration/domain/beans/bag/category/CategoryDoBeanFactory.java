package io.nzbee.test.integration.domain.beans.bag.category;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.category.ProductCategory;


@Service
@Profile(value = "it")
public class CategoryDoBeanFactory implements ICategoryDoBeanFactory {

	@Override
	public final ProductCategory getBean() {
		return this.getPomegranateBean();
	}
	
	@Override
	public final ProductCategory getPomegranateBean() {
	
		ProductCategory category = 
				new ProductCategory("POM01",
									"Pomegranate",
									true,
									new Long(2),
									new Long(0),									
									"FRT01",
									new Long(10),
									"en-GB");
		
		return category;
	}
	
	@Override
	public final ProductCategory getCitrusBean() {
	
		ProductCategory category = 
				new ProductCategory("CIT01",
									"Citrus",
									true,
									new Long(2),
									new Long(0),									
									"FRT01",
									new Long(10),
									"en-GB");
		
		return category;
	}
}
