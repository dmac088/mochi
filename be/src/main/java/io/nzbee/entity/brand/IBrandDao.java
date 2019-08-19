package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IBrandDao extends IDao<Brand> {

	Optional<Brand> findByCode(String brandCode);

	Optional<Brand> findByDesc(String brandDesc, String locale);

	List<Brand> findAll(List<String> categoryCodes, List<String> tagCodes);

	List<Brand> findAll(String brandCategoryCode);
	
}
