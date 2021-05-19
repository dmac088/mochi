package io.nzbee.entity.category.product.view.facet;

import java.util.List;
import io.nzbee.entity.IService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;

public interface IProductCategoryFacetViewService extends IService<ProductCategoryFacetViewDTO>, ISearchDimensionService<ProductCategoryFacetViewDTO> {

	List<ProductCategoryFacetViewDTO> findAll(String locale, String rootCategory);

	List<ProductCategoryFacetViewDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brands, StringCollectionWrapper tags, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes);
	
}
