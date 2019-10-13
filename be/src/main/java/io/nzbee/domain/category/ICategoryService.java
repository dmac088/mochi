package io.nzbee.domain.category;


import java.util.List;
import io.nzbee.domain.IService;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.tag.Tag;


public interface ICategoryService extends IService<Category> {

	List<Category> findAll(String locale, String currency);

	List<Category> findByParent(String locale, String currency, String parentCategoryCode);
	
	List<Category> findAll(String locale, String currency, List<String> categoryCodes);

	List<Category> findAll(String locale, String currency, String categoryDesc, List<Brand> brands, List<Tag> tags);

	List<Category> findAllForLevel(String locale, String currency, Long level);

}
