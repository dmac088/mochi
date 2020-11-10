package io.nzbee.entity.tag;

import org.springframework.stereotype.Component;
import io.nzbee.domain.tag.Tag;

@Component
public class TagMapperImpl implements ITagMapper {

	@Override
	public Tag DTOToDo(TagDTO dto) {
		Tag to = 
				new Tag(
						dto.getTagCode(),
						dto.getTagDesc(),
						dto.getLocale()
						);
		return to;
	}

	@Override
	public TagEntity doToEntity(Tag d) {
		// TODO Auto-generated method stub
		return null;
	}

}
