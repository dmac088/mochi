package io.nzbee.entity.category.product.view;

import java.util.List;
import io.nzbee.entity.IService;
import io.nzbee.entity.StringCollectionWrapper;

public interface IProductCategoryViewService extends IService<ProductCategoryViewDTO> {

	List<ProductCategoryViewDTO> findAll(String locale, String rootCategory);

	List<ProductCategoryViewDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brands, StringCollectionWrapper tags, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes);
	
}
