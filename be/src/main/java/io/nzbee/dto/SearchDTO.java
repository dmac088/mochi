package io.nzbee.dto;

import java.util.List;
import org.springframework.data.domain.Page;

import io.nzbee.domain.Product;

public class SearchDTO {
	
	Page<Product> products;
	
	List<SidebarFacetDTO> Facets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<SidebarFacetDTO> getFacets() {
		return Facets;
	}

	public void setFacets(List<SidebarFacetDTO> facets) {
		this.Facets = facets;
	}
	
}
