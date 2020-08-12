package io.nzbee.domain.category;

import java.util.Set;
import io.nzbee.domain.ILocalizedService;


public interface ICategoryService extends ILocalizedService<Category> {
	
	Set<Category> findByParent(String locale, String parentCategoryCode);

	Set<Category> findAllForLevel(String locale, Long level);

	Set<ProductCategory> findAllByProductCode(String locale, String code);

	Set<ProductCategory> findAllProductCategories(String localey);

	Set<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes);

	Set<BrandCategory> findAllBrandCategories(String locale);

}
