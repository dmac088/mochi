package io.nzbee.entity.category.product;

import io.nzbee.domain.category.ProductCategory;
import io.nzbee.entity.IMapper;

public interface ICategoryProductMapper extends IMapper<ProductCategory, io.nzbee.entity.category.product.CategoryProduct> { 

	CategoryProduct entityToDo(io.nzbee.entity.category.product.CategoryProduct e);

	CategoryProduct entityToDo(io.nzbee.entity.category.product.CategoryProduct e, String locale, String currency);
	
}
