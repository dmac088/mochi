package io.nzbee.ui.component.web.facet;
 
import io.nzbee.domain.IDomainObject;
import io.nzbee.ui.component.web.facet.search.SearchFacet;

public interface IFacetService {

	FacetResult findAll(String locale, String currency);

	FacetResult findAll(String locale, String currency, String category, FacetContainer selectedFacets);

	SearchFacet toEntityFacet(IDomainObject dO);

	String calcFacetId(String className, String id);

	String calcToken(String className, String id);

	FacetResult findAllBrands(String locale, String category);
}
