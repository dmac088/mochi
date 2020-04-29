package io.nzbee.domain;

import java.util.List;

public interface IFacetServices {

	public List<IFacetService> getFacetServices();

	public void setFacetServices(List<IFacetService> facets);

	public void showFacetServices();
}
