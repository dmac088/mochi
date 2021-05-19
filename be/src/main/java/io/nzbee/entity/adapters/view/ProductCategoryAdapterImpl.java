package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetViewMapper;
import io.nzbee.entity.category.product.view.facet.IProductCategoryFacetViewService;
import io.nzbee.view.category.product.ProductCategoryView;
import io.nzbee.view.ports.ICategoryViewPortService;

public class ProductCategoryAdapterImpl implements ICategoryViewPortService {
	
	@Autowired
	private IProductCategoryFacetViewService productCategoryService;
	
	@Autowired
	private IProductCategoryFacetViewMapper categoryMapper;
	

	@Override
	public List<ProductCategoryView> findAll(String locale, String currency, String rootCategoryCode,
			Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		return productCategoryService.findAll(locale, currency, rootCategoryCode, new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), new StringCollectionWrapper(tagCodes), maxPrice)
				.stream().map(c -> categoryMapper.toView(c)).collect(Collectors.toList());
	}

	@Override
	public Double getMaxPrice(String locale, String currency, String rootCategoryCode, Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagcodes) {
		return productCategoryService.getMaxPrice(locale, currency, rootCategoryCode, new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), new StringCollectionWrapper(tagcodes));
	}

	@Override
	public List<ProductCategoryView> findAll(String locale, String rootCategoryCode) {
		return productCategoryService.findAll(locale, rootCategoryCode)
				.stream().map(c -> categoryMapper.toView(c)).collect(Collectors.toList());
	}

}
