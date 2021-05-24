package io.nzbee.entity.category.product.view.facet;

import org.springframework.stereotype.Component;

import io.nzbee.view.category.product.ProductCategoryView;

@Component
public class ProductCategoryFacetDTOMapperImpl implements IProductCategoryFacetDTOMapper {

	@Override
	public ProductCategoryView toView(ProductCategoryFacetDTO d) {
		ProductCategoryView pcv = new ProductCategoryView();
		pcv.setCategoryCode(d.getCategoryCode());
		pcv.setCategoryDesc(d.getCategoryDesc());
		pcv.setParentCode(d.getParentCode());
		pcv.setLocale(d.getLocale());
		pcv.setChildCount(d.getChildCount());
		pcv.setObjectCount(d.getCount());
		pcv.setLevel(d.getCategorylevel());
		return pcv;
	}

}
