package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import io.nzbee.view.category.product.ProductCategoryView;
import io.nzbee.view.ports.ICategoryViewPortService;

public class ProductCategoryAdapterImpl implements ICategoryViewPortService {

	@Override
	public List<ProductCategoryView> findAllProductCategories(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategoryView> findAll(String locale, String currency, String code, Set<String> collect,
			Set<String> collect2, Set<String> collect3, Double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMaxPrice(String locale, String currency, String code, Set<String> collect, Set<String> collect2,
			Set<String> collect3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategoryView> findAll(String locale, String currency, String code, Set<String> collect,
			Set<String> collect2, Set<String> collect3) {
		// TODO Auto-generated method stub
		return null;
	}

}
