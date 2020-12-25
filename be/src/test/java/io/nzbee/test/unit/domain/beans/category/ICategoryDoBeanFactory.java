package io.nzbee.test.unit.domain.beans.category;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.test.unit.domain.beans.IDoBeanFactory;

public interface ICategoryDoBeanFactory extends IDoBeanFactory<Category> {

	Category getProductCategoryDoBean();

	BrandCategory getBrandCategoryDoBean();

}
