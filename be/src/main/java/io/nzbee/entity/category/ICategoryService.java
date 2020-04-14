package io.nzbee.entity.category;

import java.util.List;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.category.Category;

public interface ICategoryService extends ILocalizedService<Category> {

	List<Category> findByParent(String locale, String parentCategoryCode);

	List<Category> findAllForLevel(String locale, String currency, Long level);
	

	<T> List<Category> findAll(String locale, String currency, Class<T> classType);
}
