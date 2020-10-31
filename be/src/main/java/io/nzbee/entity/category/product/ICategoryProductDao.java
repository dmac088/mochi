package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface ICategoryProductDao extends ILocalizedDao<CategoryProductDTO, CategoryProduct> {
	
	List<CategoryProductDTO> findAllByProductCode(String locale, String productCode);

	Optional<CategoryProductDTO> findPrimaryByProductCode(String locale, String productCode);

}
