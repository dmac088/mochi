package io.nzbee.domain;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FacetServices {
	
	private List<IFacetService> facetServices;

	public FacetServices(List<IFacetService> facets) {
		this.facetServices = facets;
	}

	public List<IFacetService> getFacetServices() {
		return facetServices;
	}

	public void setFacetServices(List<IFacetService> facets) {
		this.facetServices = facets;
	}

	public void showFacetServices() {
		facetServices.forEach(System.out::println);
	}
}
