package io.nzbee.resources.tag.facet;

import org.springframework.stereotype.Component;
import io.nzbee.enums.FacetNameEnum;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagFacetMapperImpl implements IFacetMapper<TagFacetView, EntityFacet> {

	public EntityFacet toEntityFacet(TagFacetView tag) {
		EntityFacet ef = new EntityFacet();
		
		ef.setFacetingName(FacetNameEnum.tag);
		ef.setObjectType(tag.getClass().getSimpleName());
		ef.setId(tag.getTagCode());
		ef.setDesc(tag.getTagDesc());
		ef.setHierarchical(false);
		ef.setValue(tag.getTagCode());
		ef.setCount(tag.getProductCount());
		
		return ef;
	}

}
