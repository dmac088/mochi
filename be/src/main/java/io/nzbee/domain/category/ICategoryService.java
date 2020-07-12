package io.nzbee.domain.category;

import java.util.Set;
import io.nzbee.domain.ILocalizedService;
import io.nzbee.search.dto.facet.IFacet;

public interface ICategoryService extends ILocalizedService<Category> {

	Set<Category> findAll(String locale, String currency);
	
	Set<Category> findAll(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets);

	Set<Category> findByParent(String locale, String currency, String parentCategoryCode);

	Set<Category> findAllForLevel(String locale, String currency, Long level);

	Set<ProductCategory> findAllByProductCode(String locale, String currency, String code);

	Set<ProductCategory> findAllProductCategories(String locale, String currency);
	
	Set<BrandCategory> findAllBrandCategories(String locale, String currency);

	Double getMaxPrice(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets);
}
