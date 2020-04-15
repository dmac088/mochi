package io.nzbee.entity.category.product;

import java.util.List;

public interface ICategoryProductService {

	List<CategoryProduct> findAllByProductCode(String locale, String currency, String productCode);
	
}
