package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {

	List<Brand> findAll();
	
	Optional<Brand> findById(Long Id);
	
	Optional<Brand> findByCode(String brandCode);

	List<Brand> findAll(List<Long> categoryIds, List<Long> tagIds);
}
