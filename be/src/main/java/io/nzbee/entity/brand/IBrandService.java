package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IService;
import io.nzbee.entity.product.Product;

public interface IBrandService extends IService<Brand> {
	
	List<Brand> findAll(List<String> categoryCodes, List<String> tagCodes);

	Optional<Brand> findOne(Product p);

	List<Brand> findAll(String code);
}
