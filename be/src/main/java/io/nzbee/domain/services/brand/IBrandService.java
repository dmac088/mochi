package io.nzbee.domain.services.brand;


import java.util.List;

import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Tag;

public interface IBrandService {
	 
	Brand findOne(String lcl, Long brandId);
	
	Brand findOneByCode(String lcl, String brandCode);
	
	Brand findOneByDesc(String lcl, String brandDesc);
	
	List<Brand> findAll(String lcl);

	List<Brand> findAll(String locale, String currency, String categoryDesc, List<Category> categories, List<Tag> tags);

}
