package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILocalizedDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface ICategoryDao extends ILocalizedDao<CategoryDTO, CategoryEntity> {
	
	List<CategoryEntity> findByParent(String locale, String parentCategoryCode);
	
	List<CategoryEntity> findByLevel(String locale, Long level);

	List<CategoryDTO> findAll(String locale);
	
	List<CategoryEntity> findAllByProductCode(String locale, String productCode);

	<T> List<CategoryDTO> findAllByType(String locale, String rootCategory, Class<T> cls);

	List<CategoryEntity> findAll();

	Optional<CategoryEntity> findByCode(String categoryCode);

	List<CategoryDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes);

	
}
