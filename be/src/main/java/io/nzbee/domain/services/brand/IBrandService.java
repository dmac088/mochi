package io.nzbee.domain.services.brand;


import java.util.List;

import io.nzbee.domain.Brand;
import io.nzbee.ui.web.component.sidebar.SidebarDto;

public interface IBrandService {
	 
	Brand getBrand(String lcl, String curr, Long brandId);
	
	List<SidebarDto> getBrands(String lcl, String curr);

	List<SidebarDto> getBrands(String hierarchyCode, String lcl, String curr, String categoryDesc,
			List<SidebarDto> facets);

}
