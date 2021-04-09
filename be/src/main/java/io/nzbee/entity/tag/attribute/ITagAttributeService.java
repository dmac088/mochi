package io.nzbee.entity.tag.attribute;

import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface ITagAttributeService extends ILocalizedDao<TagAttributeDTO, TagAttributeEntity> {
	
	Optional<TagAttributeEntity> getTagAttributeEN(Long id);
	
	Optional<TagAttributeEntity> getTagAttributeHK(Long id);

	Optional<TagAttributeEntity> getTagAttribute(Long id, String locale);

	Optional<TagAttributeEntity> findById(long id);
}
