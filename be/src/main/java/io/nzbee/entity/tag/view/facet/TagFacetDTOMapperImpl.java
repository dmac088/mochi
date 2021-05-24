package io.nzbee.entity.tag.view.facet;

import org.springframework.stereotype.Component;

import io.nzbee.entity.tag.TagEntity;
import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagFacetDTOMapperImpl implements ITagFacetMapper {

	@Override
	public TagFacetView DTOToDo(TagFacetDTO dto) {
		TagFacetView to = 
				new TagFacetView(
						dto.getTagCode(),
						dto.getTagDesc(),
						dto.getCount().intValue(),
						dto.getLocale()
						);
		return to;
	}

	@Override
	public TagEntity doToEntity(TagFacetView d) {
		// TODO Auto-generated method stub
		return null;
	}

}
