package io.nzbee.test.integration.domain.beans.bag.category;

import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.test.integration.domain.beans.IDoBeanFactory;

public interface ICategoryDoBeanFactory extends IDoBeanFactory<Category> {

	ProductCategory getPomegranateBean();

	ProductCategory getCitrusBean();

}
