package io.nzbee.entity.category;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface ICategoryDao extends ILocalizedDao<Category> {
	
	Set<Category> findByParent(String locale, String parentCategoryCode);
	
	Set<Category> findByLevel(String locale, Long level);

	Set<Category> findAll(String locale);
	
	Set<Category> findAll(String locale, Set<String> categoryCodes);
	
	Set<Category> findAllByProductCode(String locale, String productCode);

	<T> Set<Category> findAllByType(String locale, Class<T> cls);

	Set<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes);

	Set<Category> findAll();

	Optional<Category> findByCode(String categoryCode);
	
}
