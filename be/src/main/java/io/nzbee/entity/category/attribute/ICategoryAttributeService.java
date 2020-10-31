package io.nzbee.entity.category.attribute;

import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface ICategoryAttributeService extends ILocalizedDao<CategoryAttributeDTO, CategoryAttributeEntity> {
	
	Optional<CategoryAttributeEntity> getCategoryAttributeEN(Long id);
	
	Optional<CategoryAttributeEntity> getCategoryAttributeHK(Long id);

	Optional<CategoryAttributeEntity> getCategoryAttribute(Long id, String locale);
}
