package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.category.product.view.IProductCategoryViewService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.product.view.IProductCategoryViewMapper;
import io.nzbee.view.category.product.ProductCategoryView;
import io.nzbee.view.ports.ICategoryViewPortService;

public class ProductCategoryAdapterImpl implements ICategoryViewPortService {
	
	@Autowired
	private IProductCategoryViewService productCategoryService;
	
	
	@Autowired
	private IProductCategoryViewMapper categoryMapper;
	

	@Override
	public List<ProductCategoryView> findAll(String locale, String currency, String rootCategoryCode,
			Set<String> collect, Set<String> collect2, Set<String> collect3, Double maxPrice) {
		return productCategoryService.findAll(locale, currency, rootCategoryCode, new StringCollectionWrapper(collect), new StringCollectionWrapper(collect2), new StringCollectionWrapper(collect3), maxPrice)
				.stream().map(c -> categoryMapper.toView(c)).collect(Collectors.toList());
	}

	@Override
	public Double getMaxPrice(String locale, String currency, String rootCategoryCode, Set<String> collect,
			Set<String> collect2, Set<String> collect3) {
		return productCategoryService.getMaxPrice(locale, currency, rootCategoryCode, new StringCollectionWrapper(collect), new StringCollectionWrapper(collect2), new StringCollectionWrapper(collect3));
	}

	@Override
	public List<ProductCategoryView> findAll(String locale, String rootCategoryCode) {
		return productCategoryService.findAll(locale, rootCategoryCode)
				.stream().map(c -> categoryMapper.toView(c)).collect(Collectors.toList());
	}

}
