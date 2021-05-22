package io.nzbee.test.integration.view.beans.category;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.Constants;
import io.nzbee.view.category.product.ProductCategoryView;


@Service
@Profile(value = "it")
public class CategoryViewBeanFactory implements ICategoryViewBeanFactory {

	@Override
	public ProductCategoryView getBean() {
		ProductCategoryView pcv = new ProductCategoryView();
		pcv.setCategoryCode("TST01");
		pcv.setCategoryDesc("test product category");
		pcv.setParentCode("FRT01");
		pcv.setChildCount(Long.valueOf(0));
		pcv.setObjectCount(Long.valueOf(10));
		pcv.setLocale(Constants.localeENGB);
		return pcv;
	}
	
	@Override
	public ProductCategoryView getPomegranateBean() {
		ProductCategoryView pcv = new ProductCategoryView();
		pcv.setCategoryCode("POM01");
		pcv.setCategoryDesc("Pomegranate");
		pcv.setParentCode("FRT01");
		pcv.setChildCount(Long.valueOf(0));
		pcv.setObjectCount(Long.valueOf(10));
		pcv.setLocale(Constants.localeENGB);
		return pcv;
	}
	
	@Override
	public ProductCategoryView getCitrusBean() {
		ProductCategoryView pcv = new ProductCategoryView();
		pcv.setCategoryCode("CIT01");
		pcv.setCategoryDesc("Citrus");
		pcv.setParentCode("FRT01");
		pcv.setChildCount(Long.valueOf(0));
		pcv.setObjectCount(Long.valueOf(10));
		pcv.setLocale(Constants.localeENGB);
		return pcv;
	}
}
