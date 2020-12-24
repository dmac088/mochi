package io.nzbee.test.integration.entity.beans.category;

import java.util.List;

import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.test.integration.entity.beans.IEntityBeanFactory;

public interface ICategoryEntityBeanFactory extends IEntityBeanFactory<CategoryEntity> {

	CategoryEntity getProductCategoryEntityBean();

	CategoryEntity getBrandCategoryEntityBean();

	List<CategoryEntity> getProductCategoryEntityListBean();

	List<CategoryEntity> getBrandCategoryEntityListBean();

}
