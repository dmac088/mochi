package io.nzbee.ui.component.web.facet;

import java.util.List;


public interface INavFacetService<T> {

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
	
	List<NavFacet> findAllTags(String locale, String currency, String category, List<NavFacet> selectedFacets);
	
	List<NavFacet> findAllCategories(String locale, String currency, String category, List<NavFacet> selectedFacets);
	
	List<NavFacet<T>> findAllBrands(String locale, String currency, String category, List<NavFacet> selectedFacets);
}
