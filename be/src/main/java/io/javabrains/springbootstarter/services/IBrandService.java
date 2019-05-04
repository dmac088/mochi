package io.javabrains.springbootstarter.services;


import java.util.List;
import io.javabrains.springbootstarter.domain.Brand;
import io.javabrains.springbootstarter.dto.SidebarFacetDTO;

public interface IBrandService {
	 
	Brand getBrand(String lcl, String curr, Long brandId);
	
	List<SidebarFacetDTO> getBrands(String lcl, String curr);

	List<SidebarFacetDTO> getBrandsForCategory(String hierarchyCode, String lcl, String curr, String categoryDesc,
			List<SidebarFacetDTO> categoryFacets);

}
