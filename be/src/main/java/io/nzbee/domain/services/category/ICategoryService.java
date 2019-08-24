package io.nzbee.domain.services.category;


import java.util.List;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Tag;

public interface ICategoryService {
	 
	List<Category> findAllForLevel(String lcl, Long level);
	 
	Category findParent(String lcl, Long parentCategoryId);
	
	List<Category> findByParent(String locale, Long parentCategoryId);
	 
	Category findOne(String locale, Long categoryId);
	 
	Category findOneByDesc(String locale,  String categoryType, String categoryDesc);
	
	Category findOneByCode(String locale, String categoryType, String categoryCode);
	
	Category createCategory(io.nzbee.entity.category.Category pc, String locale);

	List<Category> findAll(String locale, String categoryDesc, List<Brand> brands,
			List<Tag> tags);

	List<Category> findAll(String locale);

	Category findOne(String locale, String categoryCode);

}
