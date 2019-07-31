package io.nzbee.ui.component.web.facet;

import java.util.List;

import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
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
	
	List<NavFacet<Tag>> findAllTags(String locale, String currency, String category, List<NavFacet> selectedFacets);
	
	List<NavFacet<Category>> findAllCategories(String locale, String currency, String category, List<NavFacet> selectedFacets);
	
	List<NavFacet<Brand>> findAllBrands(String locale, String currency, String category, List<NavFacet> selectedFacets);


}
