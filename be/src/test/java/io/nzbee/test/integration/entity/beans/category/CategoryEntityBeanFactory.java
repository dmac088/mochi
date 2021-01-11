package io.nzbee.test.integration.entity.beans.category;


import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;


@Service
@Profile(value = "it")
public class CategoryEntityBeanFactory implements ICategoryEntityBeanFactory {

	@Override
	public final CategoryEntity getProductCategoryEntityBean() {
		final CategoryEntity category = new CategoryProductEntity();
	
		category.setCategoryCode("TST02");
		category.setCategoryLevel(new Long(1));

		final CategoryAttributeEntity categoryAttributeEn = new CategoryAttributeEntity();
		categoryAttributeEn.setCategory(category);
		categoryAttributeEn.setCategoryDesc("test product category");
		categoryAttributeEn.setLclCd(Constants.localeENGB);
		category.addCategoryAttribute(categoryAttributeEn);
		
		final CategoryAttributeEntity categoryAttributeCn = new CategoryAttributeEntity();
		categoryAttributeCn.setCategory(category);
		categoryAttributeCn.setCategoryDesc("測試產品類別");
		categoryAttributeCn.setLclCd(Constants.localeZHHK);
		category.addCategoryAttribute(categoryAttributeCn);
		
		return category;
	}
	
	@Override
	public final CategoryEntity getBrandCategoryEntityBean() {
		final CategoryEntity category = new CategoryBrandEntity();
		
		category.setCategoryCode("TST02");
		category.setCategoryLevel(new Long(2));

		final CategoryAttributeEntity categoryAttribute = new CategoryAttributeEntity();
		categoryAttribute.setCategory(category);
		categoryAttribute.setCategoryDesc("test brand category");
		categoryAttribute.setLclCd(Constants.localeENGB);
		category.addCategoryAttribute(categoryAttribute);
		
		return category;
	}
	
	
	@Override
	public final List<CategoryEntity> getProductCategoryEntityListBean() {
		List<CategoryEntity> lc = new ArrayList<CategoryEntity>();
		
		final CategoryEntity category = this.getProductCategoryEntityBean();
		
		lc.add(category);
		
		return lc;
	}
	
	@Override
	public final List<CategoryEntity> getBrandCategoryEntityListBean() {
		List<CategoryEntity> lc = new ArrayList<CategoryEntity>();
		
		final CategoryEntity category = this.getBrandCategoryEntityBean();
		
		lc.add(category);
		
		return lc;
	}

	@Override
	public CategoryEntity getBean() {
		return this.getProductCategoryEntityBean();
	}
	
	
}
