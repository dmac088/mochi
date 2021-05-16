package io.nzbee.view.ports;

import java.util.List;
import java.util.Set;

import io.nzbee.view.category.product.ProductCategoryView;

public interface ICategoryViewPortService {

	List<ProductCategoryView> findAll(String locale, String currency, String rootCategoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String rootCategoryCode, Set<String> categoryCodes, Set<String> brandCodes,
			Set<String> tagCodes);

	List<ProductCategoryView> findAll(String locale, String rootCategoryCode);
}
