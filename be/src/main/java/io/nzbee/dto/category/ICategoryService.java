package io.nzbee.dto.category;


import java.util.List;
import java.util.Optional;

import io.nzbee.dto.IService;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.tag.Tag;

public interface ICategoryService extends IService<Category> {
	 
	List<Category> findAllForLevel(String locale, String currency, Long level);
	 
	List<Category> findAll(String locale, String currency, String categoryDesc, List<Brand> brands, List<Tag> tags);

	List<Category> findAll(String locale, String currency);

	List<Category> findByParent(String locale, String currency, String parentCategoryCode);

}
