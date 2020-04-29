package io.nzbee.search;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacetServicesImpl implements IFacetServices {
	
	@Autowired
	private List<IFacetService> facetServices;

	public void FacetServices(List<IFacetService> facets) {
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
