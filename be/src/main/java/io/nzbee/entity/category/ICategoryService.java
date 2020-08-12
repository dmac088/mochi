package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.category.Category;
import io.nzbee.search.ISearchDimensionService;

public interface ICategoryService extends ILocalizedService<Category>, ISearchDimensionService<Category> {

	List<Category> findByParent(String locale, String parentCategoryCode);

	List<Category> findAllForLevel(String locale, Long level);
	
	<T> List<Category> findAll(String locale, Class<T> classType);

	List<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brands, Set<String> tags, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brands, Set<String> tags);

	List<Category> findAll();
	
	Optional<Category> findByCode(String categoryCode);

	Optional<Category> findByCode(String locale, String categoryCode);


}
