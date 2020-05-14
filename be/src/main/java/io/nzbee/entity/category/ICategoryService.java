package io.nzbee.entity.category;

import java.util.List;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.category.Category;
import io.nzbee.search.ISearchDimensionService;

public interface ICategoryService extends ILocalizedService<Category>, ISearchDimensionService<Category> {

	List<Category> findByParent(String locale, String parentCategoryCode);

	List<Category> findAllForLevel(String locale, String currency, Long level);
	
	<T> List<Category> findAll(String locale, String currency, Class<T> classType);

}
