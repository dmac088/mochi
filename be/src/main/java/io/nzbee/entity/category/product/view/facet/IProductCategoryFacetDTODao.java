package io.nzbee.entity.category.product.view.facet;

import java.util.List;

import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface IProductCategoryFacetDTODao extends IDao<ProductCategoryFacetDTO> {

	List<ProductCategoryFacetDTO> findAll(String locale, String rootCategoryCode);

	List<ProductCategoryFacetDTO> findAll(String locale, String currency, String categoryCode,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice);

	Double getMaxPrice(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes);

	List<ProductCategoryFacetDTO> findAll(String locale, StringCollectionWrapper codes);

}
