package io.nzbee.entity.category.product.view;

import java.util.List;

import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface IProductCategoryViewDao extends IDao<ProductCategoryViewDTO> {

	List<ProductCategoryViewDTO> findAll(String locale, String rootCategoryCode);

	List<ProductCategoryViewDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes);

}
