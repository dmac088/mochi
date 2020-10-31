package io.nzbee.entity.tag.attribute;

import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface ITagAttributeService extends ILocalizedDao<TagAttributeDTO, TagAttribute> {
	
	Optional<TagAttribute> getTagAttributeEN(Long id);
	
	Optional<TagAttribute> getTagAttributeHK(Long id);

	Optional<TagAttribute> getTagAttribute(Long id, String locale);
}
