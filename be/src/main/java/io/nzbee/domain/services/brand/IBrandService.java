package io.nzbee.domain.services.brand;


import java.util.List;

import io.nzbee.domain.Brand;
import io.nzbee.ui.component.web.sidebar.Sidebar;

public interface IBrandService {
	 
	Brand getBrand(String lcl, String curr, Long brandId);
	
	List<Sidebar> getBrands(String lcl, String curr);

	List<Brand> getBrands(String lcl, String curr, String categoryDesc,
			List<Long> categoryIds, List<Long> tagIds);

}
