package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.product.Product;

public interface IBrandService {

	List<Brand> findAll();
	
	Optional<Brand> findById(Long Id);
	
	Optional<Brand> findByCode(String brandCode);
	
	Optional<Brand> findByDesc(String brandDesc, String locale);

	List<Brand> findAll(List<String> categoryCodes, List<String> tagCodes);

	Optional<Brand> findOne(Product p);
}
