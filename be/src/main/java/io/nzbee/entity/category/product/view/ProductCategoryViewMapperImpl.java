package io.nzbee.entity.category.product.view;

import org.springframework.stereotype.Component;

import io.nzbee.view.category.product.ProductCategoryView;

@Component
public class ProductCategoryViewMapperImpl implements IProductCategoryViewMapper {

	@Override
	public ProductCategoryView toView(ProductCategoryViewDTO d) {
		ProductCategoryView pcv = new ProductCategoryView();
		pcv.setCategoryCode(d.getCategoryCode());
		pcv.setCategoryDesc(d.getCategoryDesc());
		pcv.setParentCode(d.getParentCode());
		pcv.setLocale(d.getLocale());
		pcv.setObjectCount(d.getCount());
		return pcv;
	}

}
