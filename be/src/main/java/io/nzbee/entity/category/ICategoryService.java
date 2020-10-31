package io.nzbee.entity.category;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.category.Category;
import io.nzbee.search.ISearchDimensionService;

public interface ICategoryService extends ILocalizedService<CategoryDTO, Category>, ISearchDimensionService<Category> {

	Set<Category> findByParent(String locale, String parentCategoryCode);

	Set<Category> findAllForLevel(String locale, Long level);
	
	<T> Set<CategoryDTO> findAll(String locale, Class<T> classType);

	Set<CategoryDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brands, Set<String> tags, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brands, Set<String> tags);

	Set<Category> findAll();
	
	Optional<Category> findByCode(String categoryCode);

	Optional<CategoryDTO> findByCode(String locale, String categoryCode);


}
