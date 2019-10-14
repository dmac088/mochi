package io.nzbee.entity.brand;

import java.util.List;
import java.util.Optional;

import io.nzbee.entity.IDao;

public interface IBrandDao extends IDao<Brand> {

	List<Brand> findAll(String brandCategoryCode);
	
	List<Brand> findAll(List<String> categoryCodes, List<String> tagCodes);
	
	List<Brand> findAll(String locale, String currency, List<String> brandCodes);
	
	Optional<Brand> findByCode(String brandCode);

	Optional<Brand> findByDesc(String locale, String brandDesc);

	

	
	
}
