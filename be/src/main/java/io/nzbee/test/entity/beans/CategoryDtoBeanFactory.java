package io.nzbee.test.entity.beans;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.dto.category.Category;
import io.nzbee.dto.category.product.ProductCategory;
import io.nzbee.variables.GeneralVars;


@Service(value = "categoryDtoBeanFactory")
@Profile(value = "dev")
public class CategoryDtoBeanFactory {

	@Bean
	public final Category getCategoryDtoBean() {
		
		ProductCategory category = new ProductCategory();
		
		category.setCategoryCode("TST02");
		category.setCategoryLevel(new Long(1));
		category.setCategoryDesc("test category");
		category.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		
		return category;
	}
	
	
	@Bean
	public final List<Category> getCategoryDtoListBean() {
		List<Category> lc = new ArrayList<Category>();
		
		final Category category = this.getCategoryDtoBean();
		
		lc.add(category);
		
		return lc;
	}
	
	
}
