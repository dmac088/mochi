package io.nzbee.entity.category.product;

import io.nzbee.domain.category.ProductCategory;
import io.nzbee.entity.IMapper;

public interface ICategoryProductMapper extends IMapper<ProductCategory, io.nzbee.entity.category.product.CategoryProduct> { 

	ProductCategory entityToDo(io.nzbee.entity.category.product.CategoryProduct e);

}
