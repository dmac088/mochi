package io.nzbee.test.integration.domain.beans.category;

import io.nzbee.domain.category.Category;
import io.nzbee.test.integration.domain.beans.IDoBeanFactory;

public interface ICategoryDoBeanFactory extends IDoBeanFactory<Category> {

	Category getPomegranateBean();

	Category getCitrusBean();

}
