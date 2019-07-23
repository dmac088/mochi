package io.nzbee.domain.services.brand;


import java.util.List;

import io.nzbee.domain.Brand;
import io.nzbee.dto.sidebar.SidebarDTO;

public interface IBrandService {
	 
	Brand getBrand(String lcl, String curr, Long brandId);
	
	List<SidebarDTO> getBrands(String lcl, String curr);

	List<SidebarDTO> getBrands(String hierarchyCode, String lcl, String curr, String categoryDesc,
			List<SidebarDTO> facets);

}
