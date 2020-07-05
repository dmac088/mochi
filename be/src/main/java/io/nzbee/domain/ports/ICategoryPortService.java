package io.nzbee.domain.ports;

import java.util.Set;

import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.search.dto.facet.IFacet;

public interface ICategoryPortService  extends IProductDimensionService<Category> {

	Set<Category> findAll(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets);
	
	Set<Category> findAllForLevel(String locale, String currency, Long level);

	Set<Category> findByParent(String parentCategoryCode, String currency, String locale);

	Set<ProductCategory> findAllByProductCode(String locale, String currency, String productCode);

	void save(Category domainObject);

	ProductCategory findPrimaryByProductCode(String locale, String currency, String productCode);

	Set<ProductCategory> findAllProductCategories(String locale, String currency);

	Set<BrandCategory> findAllBrandCategories(String locale, String currency);

}
