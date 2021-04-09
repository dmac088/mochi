package io.nzbee.entity.category.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.ILocalizedDao;

public interface ICategoryBrandDao extends ILocalizedDao<CategoryBrandDTO, CategoryBrandEntity> {
	
	List<CategoryBrandEntity> findAllByBrandCode(String locale, String currency, String brandCode);

	Optional<CategoryBrandEntity> findById(Long id);

}
