package io.nzbee.entity.category.product;

import java.util.List;

import io.nzbee.entity.ILocalizedService;

public interface ICategoryProductService extends ILocalizedService<CategoryProduct> {

	List<CategoryProduct> findAllByProductCode(String locale, String currency, String productCode);
	
}
