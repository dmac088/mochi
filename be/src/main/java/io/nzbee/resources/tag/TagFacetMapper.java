package io.nzbee.resources.tag;

import org.springframework.stereotype.Component;
import io.nzbee.domain.tag.Tag;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacetMapper;

@Component
public class TagFacetMapper implements IFacetMapper<Tag> {

	public EntityFacet toEntityFacet(Tag tag) {
		EntityFacet ef = new EntityFacet();
		ef.setFacetingName("Tag");
		ef.setObjectType(tag.getClass().getSimpleName());
		ef.setId(tag.getTagCode());
		ef.setDesc(tag.getTagDesc());
		ef.setHierarchical(true);
		ef.setValue(tag.getTagCode());
		ef.setCount(tag.getCount());
		return ef;
	}

}
