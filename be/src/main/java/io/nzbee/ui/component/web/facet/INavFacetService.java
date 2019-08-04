package io.nzbee.ui.component.web.facet;

import java.util.List;

import io.nzbee.domain.Tag;


public interface INavFacetService {

	//returns a user interface object, rule broken, need to change to return a domain object 
//	Page<Product> findAll(String lcl, 
//				 String currency, 
//				 String categoryDesc, 
//				 String searchTerm, 
//				 int page, 
//				 int size, 
//				 String sortBy, 
//				 List<String> categoryTokens,
//				 List<String> brandTokens,
//				 List<String> tagTokens);
	
	NavFacetResult findAllTags(String locale, String currency, String category, List<NavFacet> selectedFacets);
	
	NavFacetResult findAllCategories(String locale, String currency, String category, List<NavFacet> selectedFacets);
	
	NavFacetResult findAllBrands(String locale, String currency, String category, List<NavFacet> selectedFacets);

	NavFacetResult findAllCategories(String lcl, String curr);


}
