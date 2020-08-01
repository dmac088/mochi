package io.nzbee.entity.category.attribute;

import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface ICategoryAttributeService extends ILocalizedDao<CategoryAttribute> {
	
	Optional<CategoryAttribute> getCategoryAttributeEN(Long id);
	
	Optional<CategoryAttribute> getCategoryAttributeHK(Long id);

	Optional<CategoryAttribute> getCategoryAttribute(Long id, String locale);
}
