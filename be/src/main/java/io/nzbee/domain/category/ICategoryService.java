package io.nzbee.domain.category;


import java.util.List;
import java.util.Optional;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.tag.Tag;


public interface ICategoryService {
	 	 
	List<Category> findByParent(String locale, String parentCategoryCode);

	List<Category> findAll(String locale, String categoryDesc, List<Brand> brands, List<Tag> tags);

	Optional<Category> findOne(String locale, String categoryCode);

	Optional<Category> findOneByCode(String locale, String categoryCode);

	Optional<Category> findParent(String locale, String parentCategoryCode);

	Optional<Category> findOneByDesc(String locale, String categoryDesc);

	List<Category> findAll(String locale, String currency);

	Category convertCategoryDtoToCategoryDO(io.nzbee.entity.category.Category category);

	List<Category> findAllForLevel(Long level, String locale);

}
