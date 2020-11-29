package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface ICategoryDao extends ILocalizedDao<CategoryDTO, CategoryEntity> {
	
	List<CategoryEntity> findByParent(String locale, String parentCategoryCode);
	
	List<CategoryEntity> findByLevel(String locale, Long level);

	List<CategoryDTO> findAll(String locale);
	
	List<CategoryDTO> findAll(String locale, Set<String> categoryCodes);
	
	List<CategoryEntity> findAllByProductCode(String locale, String productCode);

	<T> List<CategoryDTO> findAllByType(String locale, Class<T> cls);

	List<CategoryDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes);

	List<CategoryEntity> findAll();

	Optional<CategoryEntity> findByCode(String categoryCode);
	
}
