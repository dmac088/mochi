package io.nzbee.entity.category.product;

import java.util.List;

import io.nzbee.entity.IService;

public interface ICategoryProductService extends IService<CategoryProductEntity> {

	List<CategoryProductEntity> findAll();

}
