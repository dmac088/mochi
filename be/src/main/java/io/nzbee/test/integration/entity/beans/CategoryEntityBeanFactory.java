package io.nzbee.test.integration.entity.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		final Category 			category 		= new CategoryProduct();
	
		
		final Category 			parentCategory 	= new CategoryProduct();
		parentCategory.setCategoryId(new Long(2));
	    
		final CategoryType 		categoryType 	= new CategoryType();
	    categoryType.setId(new Long(1));
	    
	    final Hierarchy 		hierarchy 		= new Hierarchy();
	    hierarchy.setHierarchyId(new Long(41));
		
		category.setCategoryCode("TST01");
		category.setCategoryLevel(new Long(1));
		category.setCategoryType(categoryType);
		category.setParent(parentCategory);
		category.setHierarchy(hierarchy);
		
		final List<CategoryAttribute> categoryAttributes = new ArrayList<CategoryAttribute>();
		final CategoryAttribute categoryAttribute = new CategoryAttribute();
		categoryAttribute.setCategory(Optional.ofNullable(category));
		categoryAttribute.setCategoryId(category.getCategoryId());
		categoryAttribute.setCategoryDesc("testCategory");
		categoryAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		categoryAttributes.add(categoryAttribute);
		category.setAttributes(categoryAttributes);
		category.setCategoryAttribute(categoryAttribute);
		
		return category;
	}
	
}
