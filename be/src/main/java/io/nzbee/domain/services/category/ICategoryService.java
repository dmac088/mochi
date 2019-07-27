package io.nzbee.domain.services.category;


import java.util.List;

import io.nzbee.domain.Category;

public interface ICategoryService {
	 
	List<Category> findAll(String lcl, String currency);
	 
	List<Category> findAllForLevel(String lcl, String currency, Long level);
	 
	List<Category> getCategoryParent(String lcl, String currency, Long parentCategoryId);
	 
	Category getCategory(String lcl, String currency, Long categoryId);
	 
	Category getCategory(String lcl, String currency, String categoryDesc);

	List<Category> findAll(String locale, String currency, String category, List<Long> brandIds, List<Long> tagIds);

}
