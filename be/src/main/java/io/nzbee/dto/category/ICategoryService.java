package io.nzbee.dto.category;


import java.util.List;
import java.util.Optional;

import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.tag.Tag;

public interface ICategoryService {
	 
	List<Category> findAllForLevel(String lcl, Long level);
	 
	Optional<Category> findOne(String locale, Long categoryId);

	List<Category> findAll(String locale, String categoryDesc, List<Brand> brands, List<Tag> tags);

	Optional<Category> findOne(String locale, String categoryCode);

	Optional<Category> findOneByCode(String locale, String categoryCode);

	Optional<Category> findParent(String locale, String parentCategoryCode);

	List<Category> findByParent(String locale, String parentCategoryCode);

	Optional<Category> findOneByDesc(String locale, String categoryDesc);

	List<Category> findAll(String locale, String currency);

}
