package io.nzbee.domain.brand;

import java.util.List;
import io.nzbee.domain.IService;

public interface IBrandService extends IService<Brand> {
	
	List<Brand> findAll(String locale, String category);

	List<Brand> findAllByCategory(String locale, String category);

}
