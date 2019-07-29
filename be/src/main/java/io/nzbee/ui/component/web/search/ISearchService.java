package io.nzbee.ui.component.web.search;

import java.util.List;

import org.hibernate.search.query.facet.Facet;
import org.springframework.data.domain.Page;

import io.nzbee.domain.Product;
import io.nzbee.ui.component.web.facet.NavFacet;

public interface ISearchService {

	String searchService = null;

	Search findAll(String lcl, 
			 String currency, 
			 String categoryDesc, 
			 String searchTerm, 
			 int page, 
			 int size, 
			 String sortBy, 
			 List<NavFacet> selectedFacets);
	
	NavFacet getMaxPrice(String categoryDesc, String locale, String currency, List<NavFacet> selectedFacets);

	Page<Product> findAll(String lcl, String currency, String categoryDesc, String searchTerm, int page, int size,
			String sortBy, List<String> categoryTokens, List<String> brandTokens, List<String> tagTokens);

}
