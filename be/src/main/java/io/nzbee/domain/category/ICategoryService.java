package io.nzbee.domain.category;

import java.util.Set;
import io.nzbee.domain.ILocalizedService;

public interface ICategoryService extends ILocalizedService<Category> {

	Set<Category> findAll(String locale, String currency);

	Set<Category> findByParent(String locale, String currency, String parentCategoryCode);

	Set<Category> findAllForLevel(String locale, String currency, Long level);

	Set<ProductCategory> findAllByProductCode(String locale, String currency, String code);

	Set<ProductCategory> findAllProductCategories(String locale, String currency);
	
	Set<BrandCategory> findAllBrandCategories(String locale, String currency);
}
