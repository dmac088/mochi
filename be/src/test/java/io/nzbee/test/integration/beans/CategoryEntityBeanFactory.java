package io.nzbee.test.integration.beans;


import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.variables.GeneralVars;


@Service(value = "categoryEntityBeanFactory")
@Profile(value = "dev")
public class CategoryEntityBeanFactory {

	@Bean
	public final Category getProductCategoryEntityBean() {
		final Category category = new CategoryProduct();
	
		category.setCategoryCode("TST02");
		category.setCategoryLevel(new Long(1));

		final CategoryAttribute categoryAttribute = new CategoryAttribute();
		categoryAttribute.setCategory(category);
		categoryAttribute.setCategoryDesc("test product category");
		categoryAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		category.addAttribute(categoryAttribute);
		
		return category;
	}
	
	@Bean
	public final Category getBrandCategoryEntityBean() {
		final Category category = new CategoryBrand();
		
		category.setCategoryCode("TST02");
		category.setCategoryLevel(new Long(2));

		final CategoryAttribute categoryAttribute = new CategoryAttribute();
		categoryAttribute.setCategory(category);
		categoryAttribute.setCategoryDesc("test brand category");
		categoryAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		category.addAttribute(categoryAttribute);
		
		return category;
	}
	
	
	@Bean
	public final List<Category> getProductCategoryEntityListBean() {
		List<Category> lc = new ArrayList<Category>();
		
		final Category category = this.getProductCategoryEntityBean();
		
		lc.add(category);
		
		return lc;
	}
	
	@Bean
	public final List<Category> getBrandCategoryEntityListBean() {
		List<Category> lc = new ArrayList<Category>();
		
		final Category category = this.getBrandCategoryEntityBean();
		
		lc.add(category);
		
		return lc;
	}
	
	
}
