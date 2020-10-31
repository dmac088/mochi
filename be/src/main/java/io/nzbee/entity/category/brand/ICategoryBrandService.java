package io.nzbee.entity.category.brand;

import java.util.List;

import io.nzbee.entity.ILocalizedService;

public interface ICategoryBrandService extends ILocalizedService<CategoryBrandDTO, CategoryBrandEntity> {

	List<CategoryBrandDTO> findAllByBrandCode(String locale, String currency, String brandCode);
	
}
