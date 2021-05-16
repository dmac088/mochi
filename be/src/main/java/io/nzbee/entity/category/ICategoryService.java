package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILightLocalizedService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;
import io.nzbee.view.category.product.ProductCategoryView;

public interface ICategoryService extends ILightLocalizedService<CategoryDTO, CategoryEntity>, ISearchDimensionService<CategoryDTO> {

	List<CategoryEntity> findByParent(String locale, String parentCategoryCode);

	List<CategoryEntity> findAllForLevel(String locale, Long level);
	
	<T> List<CategoryDTO> findAll(String locale, String rootCategory, Class<T> classType);

	List<CategoryDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brands, StringCollectionWrapper tags, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brands, StringCollectionWrapper tags);

	List<CategoryEntity> findAll();

	Optional<CategoryEntity> findByCode(String categoryCode);

	Optional<CategoryEntity> findById(Long categoryId);


}
