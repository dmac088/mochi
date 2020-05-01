package io.nzbee.domain.ports;

import java.util.Optional;
import java.util.Set;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;

public interface ICategoryPortService  extends IProductDimensionService<Category> {

	Set<Category> findAllForLevel(String locale, String currency, Long level);

	Set<Category> findByParent(String parentCategoryCode, String currency, String locale);

	Set<ProductCategory> findAllByProductCode(String locale, String currency, String productCode);

	void save(Category domainObject);

	Optional<ProductCategory> findPrimaryByProductCode(String locale, String currency, String productCode);

}
