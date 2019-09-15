package io.nzbee.dto.category;


import java.util.List;
import java.util.Optional;

import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.tag.Tag;

public interface ICategoryService {
	 
	List<Category> findAllForLevel(String lcl, Long level);
	 
	Optional<Category> findParent(String lcl, Long parentCategoryId);
	
	List<Category> findByParent(String locale, Long parentCategoryId);
	 
	Optional<Category> findOne(String locale, Long categoryId);
	 
	Optional<Category> findOneByDesc(String locale,  String categoryType, String categoryDesc);
	
	//Category createCategory(io.nzbee.entity.category.Category pc, String locale);

	List<Category> findAll(String locale, String categoryDesc, List<Brand> brands,
			List<Tag> tags);

	List<Category> findAll(String locale);

	Optional<Category> findOne(String locale, String categoryCode);

	Optional<Category> findOneByCode(String locale, String categoryCode);

}
