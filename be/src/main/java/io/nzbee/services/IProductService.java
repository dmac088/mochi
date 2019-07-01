package io.nzbee.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.nzbee.domain.Product;
import io.nzbee.dto.SearchDTO;
import io.nzbee.dto.SidebarFacetDTO;

public interface IProductService {

	 SearchDTO findProduct(String locale, String currency, String categoryCode, String term, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets);
	
	 SearchDTO getProducts(String locale, String currency, String categoryDesc, Double price, int page, int size, String sortBy, List<SidebarFacetDTO> selectedFacets);
	 
	 List<Product> getProducts(@PathVariable String locale, @PathVariable String currency, @RequestBody final List<Long> productIds);
	 
	 Double getMaxPrice(String categoryDesc, String locale, String currency, List<SidebarFacetDTO> selectedFacets);
	 
	 Product getProduct(String locale, String currency, Long id);
	 
	 List<SidebarFacetDTO> getProductTags(String locale, String currency, String categoryDesc, Double price, List<SidebarFacetDTO> selectedFacets);
	 
	 List<SidebarFacetDTO> getProductTags(String locale, String currency, String categoryDesc, List<SidebarFacetDTO> selectedFacets);
	 
}
