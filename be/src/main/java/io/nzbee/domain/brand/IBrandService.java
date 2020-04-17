package io.nzbee.domain.brand;

import java.util.Set;
import io.nzbee.domain.IService;

public interface IBrandService extends IService<Brand> {
	
	Set<Brand> findAll(String locale, String currency, String category);
	
}
