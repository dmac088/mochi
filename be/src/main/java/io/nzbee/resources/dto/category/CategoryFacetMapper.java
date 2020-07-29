package io.nzbee.resources.dto.category;

import org.springframework.stereotype.Component;
import io.nzbee.domain.category.Category;
import io.nzbee.search.dto.facet.EntityFacet;
import io.nzbee.search.dto.facet.IFacetMapper;

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
