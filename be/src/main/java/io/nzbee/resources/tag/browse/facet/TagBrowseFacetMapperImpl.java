package io.nzbee.resources.tag.browse.facet;

import org.springframework.stereotype.Component;

import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagBrowseFacetMapperImpl implements IFacetMapper<TagFacetView> {

	public EntityFacet toEntityFacet(TagFacetView tag) {
		EntityFacet ef = new EntityFacet();
		ef.setFacetingName("Tag");
		ef.setObjectType(tag.getClass().getSimpleName());
		ef.setId(tag.getId());
		ef.setDesc(tag.getDesc());
		ef.setHierarchical(true);
		ef.setValue(tag.getId());
		ef.setCount(tag.getCount());
		return ef;
	}

}
