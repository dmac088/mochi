package io.nzbee.ui.component.web.search;

import java.util.List;

import io.nzbee.ui.component.web.sidebar.Sidebar;

public interface ISearchService {

	String searchService = null;

	Search findAll(String lcl, 
			 String currency, 
			 String categoryDesc, 
			 String searchTerm, 
			 int page, 
			 int size, 
			 String sortBy, 
			 List<Sidebar> selectedFacets);
	
	//returns a user interface object, rule broken, need to change to return a domain object 
	Search findAll(String locale, 
			 String currency, 
			 String categoryDesc, 
			 Double price, 
			 int page, 
			 int size, 
			 String sortBy, 
			 List<Sidebar> selectedFacets);
}
