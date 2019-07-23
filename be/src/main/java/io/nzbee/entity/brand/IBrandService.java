package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {

	List<Brand> getBrands();
	
	Optional<Brand> getBrand(Long Id);
	
	Optional<Brand> getBrand(String brandCode);
	
}
