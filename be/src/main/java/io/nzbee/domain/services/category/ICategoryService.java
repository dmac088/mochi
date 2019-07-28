package io.nzbee.domain.services.category;


import java.util.List;

import io.nzbee.domain.Category;

public interface ICategoryService {
	 
	List<Category> findAll(String lcl);
	 
	List<Category> findAllForLevel(String lcl, Long level);
	 
	Category findParent(String lcl, Long parentCategoryId);
	
	List<Category> findByParent(String locale, Long parentCategoryId);
	 
	Category findOne(String locale, Long categoryId);
	 
	Category findOneByDesc(String locale,  String categoryType, String categoryDesc);
	
	Category findOneByCode(String locale, String categoryType, String categoryCode);

	List<Category> findAll(String locale, String category, List<Long> brandIds, List<Long> tagIds);

}
