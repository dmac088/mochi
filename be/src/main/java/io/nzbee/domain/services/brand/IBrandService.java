package io.nzbee.domain.services.brand;


import java.util.List;

import io.nzbee.domain.Brand;

public interface IBrandService {
	 
	Brand findOne(String lcl, Long brandId);
	
	Brand findOneByCode(String lcl, String brandCode);
	
	Brand findOneByDesc(String lcl, String brandDesc);
	
	List<Brand> findAll(String lcl);

	List<Brand> findAll(String lcl, String curr, String categoryDesc,
			List<Long> categoryIds, List<Long> tagIds);

}
