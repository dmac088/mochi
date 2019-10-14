package io.nzbee.dto.category;


import java.util.List;
import io.nzbee.dto.ILocalizedService;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.tag.Tag;

public interface ICategoryService extends ILocalizedService<Category> {
	 
	List<Category> findAllForLevel(String locale, String currency, Long level);
	 
	List<Category> findAll(String locale, String currency, String categoryDesc, List<Brand> brands, List<Tag> tags);

	List<Category> findAll(String locale, String currency);
	
	List<Category> findAll(String locale, String currency, List<String> categoryCodes);

	List<Category> findByParent(String locale, String currency, String parentCategoryCode);

}
