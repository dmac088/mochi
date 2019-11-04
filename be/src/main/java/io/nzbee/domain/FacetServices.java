package io.nzbee.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacetServices {
	List<IFacetService> facets;

	@Autowired
	public FacetServices(List<IFacetService> facets) {
		this.facets = facets;
	}

	public List<IFacetService> getFacets() {
		return facets;
	}

	public void setFacets(List<IFacetService> facets) {
		this.facets = facets;
	}

	public void showFacets() {
		facets.forEach(System.out::println);
	}
}
