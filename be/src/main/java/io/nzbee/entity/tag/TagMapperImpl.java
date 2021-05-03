package io.nzbee.entity.tag;

import org.springframework.stereotype.Component;

import io.nzbee.view.product.tag.facet.TagFacetView;

@Component
public class TagMapperImpl implements ITagMapper {

	@Override
	public TagFacetView DTOToDo(TagFacetViewDTO dto) {
		TagFacetView to = 
				new TagFacetView(
						dto.getTagCode(),
						dto.getTagDesc(),
						dto.getCount(),
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
