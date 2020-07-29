package io.nzbee.search.dto.facet;

import org.springframework.stereotype.Component;
import io.nzbee.domain.category.Category;

@Component
public class CategoryFacetMapper implements IFacetMapper<Category> {

	public EntityFacet toEntityFacet(Category category) {
		EntityFacet ef = new EntityFacet();
		ef.setFacetingName("category");
		ef.setObjectType(category.getClass().getSimpleName());
		ef.setId(category.getCategoryCode());
		ef.setDesc(category.getCategoryDesc());
		ef.setHierarchical(true);
		ef.setValue(category.getCategoryCode());
		ef.setCount(category.getCount());
		return ef;
	}
	
}
