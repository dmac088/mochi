package io.javabrains.springbootstarter.dto;

import java.util.List;
import org.springframework.data.domain.Page;

import io.javabrains.springbootstarter.domain.Product;

public class ProductsDTO {
	
	Page<Product> products;
	
	List<SidebarFacetDTO> Facets;
	
	//List<Facet> brandFacets;

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
