package io.nzbee.search;

import java.util.List;

public interface IFacetServices {

	public List<IFacetService> getFacetServices();

	public void setFacetServices(List<IFacetService> facets);

	public void showFacetServices();
}
