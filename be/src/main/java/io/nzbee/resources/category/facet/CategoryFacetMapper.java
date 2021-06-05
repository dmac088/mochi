package io.nzbee.resources.category.facet;

import org.springframework.stereotype.Component;
import io.nzbee.enums.FacetNameEnum;
import io.nzbee.search.facet.EntityFacetHierarchical;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.category.product.ProductCategoryView;

@Component
public class CategoryFacetMapper implements IFacetMapper<ProductCategoryView, EntityFacetHierarchical> {

	public EntityFacetHierarchical toEntityFacet(ProductCategoryView category) {
		EntityFacetHierarchical ef = new EntityFacetHierarchical();
		ef.setFacetingName(FacetNameEnum.category);
		ef.setObjectType(category.getClass().getSimpleName());
		ef.setId(category.getCategoryCode());
		ef.setDesc(category.getCategoryDesc());
		ef.setHierarchical(true);
		ef.setValue(category.getCategoryCode());
		ef.setCount(category.getObjectCount().intValue());
		ef.setParentId(category.getParentCode());
		ef.setLevel(category.getLevel().intValue());
		ef.setChildCount(category.getChildCount().intValue());
		return ef;
	}

}
