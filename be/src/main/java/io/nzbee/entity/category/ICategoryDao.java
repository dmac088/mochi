package io.nzbee.entity.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface ICategoryDao extends ILocalizedDao<Category> {
	
	List<Category> findByParent(String locale, String parentCategoryCode);
	
	List<Category> findByLevel(String locale, Long level);

	List<Category> findAll(String locale, String currency);
	
	List<Category> findAll(String locale, String currency, Set<String> categoryCodes);
	
	List<Category> findAllByProductCode(String locale, String currency, String productCode);

	<T> List<Category> findAllByType(String locale, String currency, Class<T> cls);

	List<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes);

	List<Category> findAll();

	Optional<Category> findByCode(String categoryCode);
	
}
