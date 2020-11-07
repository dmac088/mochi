package io.nzbee.entity.category;

import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.search.ISearchDimensionService;

public interface ICategoryService extends ILocalizedService<CategoryDTO, CategoryEntity>, ISearchDimensionService<CategoryDTO> {

	Set<CategoryEntity> findByParent(String locale, String parentCategoryCode);

	Set<CategoryEntity> findAllForLevel(String locale, Long level);
	
	<T> Set<CategoryDTO> findAll(String locale, Class<T> classType);

	Set<CategoryDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brands, Set<String> tags, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brands, Set<String> tags);

	Set<CategoryEntity> findAll();
	

}
