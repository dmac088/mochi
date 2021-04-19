package io.nzbee.entity.category.product.view;

import java.util.List;

import io.nzbee.entity.IDao;

public interface IProductCategoryViewDao extends IDao<ProductCategoryViewDTO> {

	List<ProductCategoryViewDTO> findAll(String locale);

}
