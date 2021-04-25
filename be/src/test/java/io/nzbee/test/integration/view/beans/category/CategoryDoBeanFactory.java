package io.nzbee.test.integration.view.beans.category;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.view.category.product.ProductCategoryView;


@Service
@Profile(value = "it")
public class CategoryDoBeanFactory implements ICategoryViewBeanFactory {

	@Override
	public ProductCategoryView getBean() {
		return new ProductCategoryView( "TST01",
										"test product category",									
										"FRT01",
										new Long(10),
										"en-GB");
	}
	
	@Override
	public ProductCategoryView getPomegranateBean() {
		return new ProductCategoryView(	"POM01",
										"Pomegranate",									
										"FRT01",
										new Long(10),
										"en-GB");
	}
	
	@Override
	public ProductCategoryView getCitrusBean() {
	
		return new ProductCategoryView(	"CIT01",
										"Citrus",									
										"FRT01",
										new Long(10),
										"en-GB");
	}
}
