package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface ICategoryProductDao extends ILocalizedDao<CategoryProduct> {
	
	List<CategoryProduct> findAllByProductCode(String locale, String productCode);

	Optional<CategoryProduct> findPrimaryByProductCode(String locale, String productCode);

	void merge(CategoryProduct t);


}
