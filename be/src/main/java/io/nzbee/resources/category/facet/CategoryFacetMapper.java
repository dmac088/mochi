package io.nzbee.resources.category.facet;

import org.springframework.stereotype.Component;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.category.product.ProductCategoryView;

@Component
public class CategoryFacetMapper implements IFacetMapper<ProductCategoryView> {

	public EntityFacet toEntityFacet(ProductCategoryView category) {
		EntityFacet ef = new EntityFacet();
		ef.setFacetingName("category");
		ef.setObjectType(category.getClass().getSimpleName());
		ef.setId(category.getCategoryCode());
		ef.setDesc(category.getCategoryDesc());
		ef.setHierarchical(true);
		ef.setValue(category.getCategoryCode());
		ef.setCount(category.getObjectCount().intValue());
		return ef;
	}

}
