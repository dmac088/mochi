package io.nzbee.dto.category;


import java.util.List;
import java.util.Optional;

import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.tag.Tag;

public interface ICategoryService {
	 
	List<CategoryWithNameAndStats> findAllForLevel(String lcl, Long level);
	 
	Optional<CategoryWithNameAndStats> findOne(String locale, Long categoryId);

	List<CategoryWithNameAndStats> findAll(String locale, String categoryDesc, List<Brand> brands, List<Tag> tags);

	Optional<CategoryWithNameAndStats> findOne(String locale, String categoryCode);

	Optional<CategoryWithNameAndStats> findOneByCode(String locale, String categoryCode);

	Optional<CategoryWithNameAndStats> findParent(String locale, String parentCategoryCode);

	List<CategoryWithNameAndStats> findByParent(String locale, String parentCategoryCode);

	Optional<CategoryWithNameAndStats> findOneByDesc(String locale, String categoryDesc);

	List<CategoryWithNameAndStats> findAll(String locale, String currency);

}
