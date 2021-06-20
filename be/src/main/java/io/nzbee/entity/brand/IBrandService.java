package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IService;

public interface IBrandService extends IService<BrandEntity> {

	Optional<BrandEntity> findByCode(String brandCode);
	
	List<BrandEntity> findAll();
}
