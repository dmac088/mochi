package io.nzbee.entity.brand;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IBrandService extends IService<BrandEntity> {

	Optional<BrandEntity> findByCode(String brandCode);
	
}
