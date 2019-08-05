package io.nzbee.ui.component.web.facet;



public interface INavFacetService {

	NavFacetResult findAll(String locale, String currency);

	NavFacetResult findAll(String locale, String currency, String category, NavFacetContainer selectedFacets);
}
