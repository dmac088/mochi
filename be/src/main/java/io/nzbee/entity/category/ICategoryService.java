package io.nzbee.entity.category;

import java.util.List;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.search.ISearchDimensionService;

public interface ICategoryService extends ILocalizedService<CategoryDTO, CategoryEntity>, ISearchDimensionService<CategoryDTO> {

	List<CategoryEntity> findByParent(String locale, String parentCategoryCode);

	List<CategoryEntity> findAllForLevel(String locale, Long level);
	
	<T> List<CategoryDTO> findAll(String locale, Class<T> classType);

	List<CategoryDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brands, StringCollectionWrapper tags, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brands, StringCollectionWrapper tags);

	List<CategoryEntity> findAll();
	

}
