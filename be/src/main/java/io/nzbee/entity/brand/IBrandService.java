package io.nzbee.entity.brand;

import java.util.List;
import io.nzbee.entity.IService;

public interface IBrandService extends IService<Brand> {
	
	List<Brand> findAll(List<String> categoryCodes, List<String> tagCodes);

	List<Brand> findAll(String code);
}
