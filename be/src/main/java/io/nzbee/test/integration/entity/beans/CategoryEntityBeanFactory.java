package io.nzbee.test.integration.entity.beans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.variables.GeneralVars;


@Service(value = "categoryEntityBeanFactory")
@Profile(value = "dev")
public class CategoryEntityBeanFactory {

	@Bean
	public final Category getCategoryEntityBean() {
		final Category category = new CategoryProduct();
		
		final CategoryType 	categoryType = new CategoryType();
	    categoryType.setCode("TST01");
	    categoryType.setDesc("test category type");
	    
	    final Hierarchy hierarchy = new Hierarchy();
	    hierarchy.setHierarchyCode("TST01");
	    hierarchy.setDesc("test hierarchy");
	    
		category.setCategoryCode("TST02");
		category.setCategoryLevel(new Long(1));
		category.setCategoryType(categoryType);
		category.setHierarchy(hierarchy);

		final CategoryAttribute categoryAttribute = new CategoryAttribute();
		categoryAttribute.setCategory(category);
		categoryAttribute.setCategoryDesc("test category");
		categoryAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		category.addAttribute(categoryAttribute);
		category.setCategoryAttribute(categoryAttribute);
		
		return category;
	}
	
}
