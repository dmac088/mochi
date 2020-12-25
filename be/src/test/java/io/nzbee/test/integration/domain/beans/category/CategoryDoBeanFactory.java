package io.nzbee.test.integration.domain.beans.category;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;


@Service
@Profile(value = "it")
public class CategoryDoBeanFactory implements ICategoryDoBeanFactory {

	@Override
	public final Category getBean() {
		return new ProductCategory("TST01",
									"test product category",
									true,
									new Long(2),
									new Long(0),									
									"FRT01",
									new Long(10),
									"en-GB");
	}
	
	@Override
	public final Category getPomegranateBean() {
		return new ProductCategory("POM01",
									"Pomegranate",
									true,
									new Long(2),
									new Long(0),									
									"FRT01",
									new Long(10),
									"en-GB");
	}
	
	@Override
	public final Category getCitrusBean() {
	
		return new ProductCategory("CIT01",
									"Citrus",
									true,
									new Long(2),
									new Long(0),									
									"FRT01",
									new Long(10),
									"en-GB");
	}
}
