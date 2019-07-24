package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {

	List<Brand> findAll();
	
	List<Brand> findAll(List<Long> categoryIds, String locale, List<Long> tagIds);
	
	Optional<Brand> findById(Long Id);
	
	Optional<Brand> findByCode(String brandCode);
}
