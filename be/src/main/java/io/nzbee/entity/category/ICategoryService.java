package io.nzbee.entity.category;

import java.util.List;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.search.ISearchDimensionService;

public interface ICategoryService extends ILocalizedService<CategoryDTO, CategoryEntity>, ISearchDimensionService<CategoryDTO> {

	List<CategoryEntity> findByParent(String locale, String parentCategoryCode);

	List<CategoryEntity> findAllForLevel(String locale, Long level);
	
	<T> List<CategoryDTO> findAll(String locale, Class<T> classType);

	List<CategoryDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brands, Set<String> tags, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brands, Set<String> tags);

	List<CategoryEntity> findAll();
	

}
