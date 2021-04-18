package io.nzbee.view.ports;

import java.util.List;
import java.util.Set;

import io.nzbee.view.category.product.ProductCategoryView;

public interface ICategoryViewPortService {

	List<ProductCategoryView> findAllProductCategories(String locale);

	List<ProductCategoryView> findAll(String locale, String currency, String code, Set<String> collect,
			Set<String> collect2, Set<String> collect3);
	
	List<ProductCategoryView> findAll(String locale, String currency, String code, Set<String> collect,
			Set<String> collect2, Set<String> collect3, Double maxPrice);

	Double getMaxPrice(String locale, String currency, String code, Set<String> collect, Set<String> collect2,
			Set<String> collect3);
}
