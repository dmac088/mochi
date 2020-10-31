package io.nzbee.entity.category;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface ICategoryDao extends ILocalizedDao<CategoryDTO, CategoryEntity> {
	
	Set<CategoryEntity> findByParent(String locale, String parentCategoryCode);
	
	Set<CategoryEntity> findByLevel(String locale, Long level);

	Set<CategoryDTO> findAll(String locale);
	
	Set<CategoryDTO> findAll(String locale, Set<String> categoryCodes);
	
	Set<CategoryEntity> findAllByProductCode(String locale, String productCode);

	<T> Set<CategoryDTO> findAllByType(String locale, Class<T> cls);

	Set<CategoryDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes);

	Set<CategoryEntity> findAll();

	Optional<CategoryEntity> findByCode(String categoryCode);
	
}
