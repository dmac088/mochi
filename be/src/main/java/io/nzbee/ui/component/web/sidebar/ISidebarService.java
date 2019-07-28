package io.nzbee.ui.component.web.sidebar;

import java.util.List;


public interface ISidebarService {

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
	
	List<Sidebar> findAllTags(String locale, String currency, String category, List<Sidebar> selectedFacets);
	
	List<Sidebar> findAllCategories(String locale, String currency, String category, List<Sidebar> selectedFacets);
	
	List<Sidebar> findAllBrands(String locale, String currency, String category, List<Sidebar> selectedFacets);
}
