package io.nzbee.services.brand;


import java.util.List;

import io.nzbee.domain.Brand;
import io.nzbee.dto.SidebarFacetDTO;

public interface IBrandService {
	 
	Brand getBrand(String lcl, String curr, Long brandId);
	
	List<SidebarFacetDTO> getBrands(String lcl, String curr);

	List<SidebarFacetDTO> getBrands(String hierarchyCode, String lcl, String curr, String categoryDesc,
			List<SidebarFacetDTO> facets);

}
