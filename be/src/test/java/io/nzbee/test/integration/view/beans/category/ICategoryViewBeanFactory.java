package io.nzbee.test.integration.view.beans.category;

import io.nzbee.test.integration.view.beans.IViewBeanFactory;
import io.nzbee.view.category.product.ProductCategoryView;

public interface ICategoryViewBeanFactory extends IViewBeanFactory<ProductCategoryView> {

	ProductCategoryView getPomegranateBean();

	ProductCategoryView getCitrusBean();

}
