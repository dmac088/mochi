package io.nzbee.ui.component.web.search;

import java.util.List;
import org.springframework.data.domain.Page;

import io.nzbee.domain.Product;
import io.nzbee.ui.component.web.sidebar.SidebarDto;

public class SearchDto {
	
	Page<Product> products;
	
	List<SidebarDto> Facets;

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

	public List<SidebarDto> getFacets() {
		return Facets;
	}

	public void setFacets(List<SidebarDto> facets) {
		this.Facets = facets;
	}
	
}
