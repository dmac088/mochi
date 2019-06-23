package io.nzbee.services;

import java.util.List;

import io.nzbee.domain.Product;
import io.nzbee.dto.SearchDTO;
import io.nzbee.dto.SidebarFacetDTO;

public interface IProductService {

	 SearchDTO findProduct(String lcl, String currency, String categoryCode, String term, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets);
	
	 SearchDTO getProducts(String lcl, String currency, String categoryDesc, Double price, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets);
	 
	 public Product getProduct(String lcl, String currency, Long id);
	 
	 List<SidebarFacetDTO> getProductTags(String lcl, String curr, String category, List<SidebarFacetDTO> selectedFacets);
	 
	 Double getMaxPrice(String lcl, String curr, String category, List<SidebarFacetDTO> selectedFacets);
	
}
