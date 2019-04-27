package io.javabrains.springbootstarter.services;

import java.util.List;
import org.springframework.data.domain.Page;

import io.javabrains.springbootstarter.domain.Product;
import io.javabrains.springbootstarter.dto.SidebarDTO;

public class ResultContainer {
	
	Page<Product> products;
	
	List<SidebarDTO> categoryFacets;
	
	//List<CategoryFacet> brandFacets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<SidebarDTO> getCategoryFacets() {
		return categoryFacets;
	}

	public void setCategoryFacets(List<SidebarDTO> facets) {
		this.categoryFacets = facets;
	}
	
}
