package io.nzbee.domain.category;

import java.util.List;
import java.util.Set;
import io.nzbee.domain.ILocalizedService;


public interface ICategoryService extends ILocalizedService<Category> {
	
	List<Category> findByParent(String locale, String parentCategoryCode);
	
	List<Category> findAllForLevel(String locale, Long level);

	List<ProductCategory> findAllByProductCode(String locale, String code);

	List<ProductCategory> findAllProductCategories(String localey);

	List<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes);

	List<BrandCategory> findAllBrandCategories(String locale);

}
