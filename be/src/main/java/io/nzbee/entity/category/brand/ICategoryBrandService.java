package io.nzbee.entity.category.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.ILightLocalizedService;

public interface ICategoryBrandService extends ILightLocalizedService<CategoryBrandDTO, CategoryBrandEntity> {

	List<CategoryBrandDTO> findAllByBrandCode(String locale, String currency, String brandCode);

	Optional<CategoryBrandEntity> findByCode(String code);

	Optional<CategoryBrandEntity> findById(Long id);
	
}
