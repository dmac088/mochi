package io.nzbee.dto.search;

import java.util.List;
import org.springframework.data.domain.Page;

import io.nzbee.domain.Product;
import io.nzbee.dto.sidebar.SidebarDTO;

public class SearchDTO {
	
	Page<Product> products;
	
	List<SidebarDTO> Facets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<SidebarDTO> getFacets() {
		return Facets;
	}

	public void setFacets(List<SidebarDTO> facets) {
		this.Facets = facets;
	}
	
}
