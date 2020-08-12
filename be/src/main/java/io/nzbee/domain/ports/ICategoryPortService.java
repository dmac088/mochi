package io.nzbee.domain.ports;

import java.util.Set;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;

public interface ICategoryPortService  extends IProductDimensionService<Category> {

	Set<Category> findAllForLevel(String locale, Long level);

	Set<Category> findByParent(String parentCategoryCode, String locale);

	Set<ProductCategory> findAllByProductCode(String locale, String productCode);

	void save(Category domainObject);

	ProductCategory findPrimaryByProductCode(String locale, String productCode);

	Set<BrandCategory> findAllBrandCategories(String locale);

	Set<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes);

	Set<ProductCategory> findAllProductCategories(String locale);

}
