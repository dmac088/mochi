package io.nzbee.domain.ports;

import java.util.List;
import java.util.Set;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;

public interface ICategoryPortService  extends IProductDimensionService<Category> {

	List<Category> findAllForLevel(String locale, Long level);

	List<Category> findByParent(String parentCategoryCode, String locale);

	void save(Category domainObject);

	List<BrandCategory> findAllBrandCategories(String locale);

	List<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes);

	List<ProductCategory> findAllProductCategories(String locale);

}
