package io.nzbee.entity.category.product.view;

import java.util.List;
import io.nzbee.entity.IService;

public interface IProductCategoryService extends IService<ProductCategoryViewDTO> {

	List<ProductCategoryViewDTO> findAll(String locale, String rootCategory);

}
