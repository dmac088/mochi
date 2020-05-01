package io.nzbee.entity.category.product;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILocalizedService;

public interface ICategoryProductService extends ILocalizedService<CategoryProduct> {

	List<CategoryProduct> findAllByProductCode(String locale, String currency, String productCode);

	Optional<CategoryProduct> findPrimaryByProductCode(String locale, String currency, String productCode);
	
}
