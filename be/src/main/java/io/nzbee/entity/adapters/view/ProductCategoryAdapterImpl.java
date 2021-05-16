package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.category.product.view.IProductCategoryService;
import io.nzbee.entity.category.product.view.IProductCategoryViewMapper;
import io.nzbee.view.category.product.ProductCategoryView;
import io.nzbee.view.ports.ICategoryViewPortService;

public class ProductCategoryAdapterImpl implements ICategoryViewPortService {
	
	@Autowired
	private IProductCategoryService categoryService;
	
	@Autowired
	private IProductCategoryViewMapper categoryMapper;
	
	@Override
	public List<ProductCategoryView> findAll(String locale, String currency, String rootCategoryCode,
			Set<String> collect, Set<String> collect2, Set<String> collect3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategoryView> findAll(String locale, String currency, String rootCategoryCode,
			Set<String> collect, Set<String> collect2, Set<String> collect3, Double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getMaxPrice(String locale, String currency, String rootCategoryCode, Set<String> collect,
			Set<String> collect2, Set<String> collect3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductCategoryView> findAll(String locale, String rootCategoryCode) {
		return categoryService.findAll(locale, rootCategoryCode)
				.stream().map(c -> categoryMapper.toView(c)).collect(Collectors.toList());
	}

}
