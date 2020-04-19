package io.nzbee.entity.category.brand;

import java.util.List;

public interface ICategoryBrandService {

	List<CategoryBrand> findAllByBrandCode(String locale, String currency, String brandCode);
	
}
