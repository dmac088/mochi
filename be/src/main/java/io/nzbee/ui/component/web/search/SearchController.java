package io.nzbee.ui.component.web.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.ui.component.web.facet.NavFacetContainer;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping("/Search/{locale}/{currency}/Category/{category}/SearchTerm/{term}/Page/{page}/Size/{size}/SortBy/{sortBy}")
    public Search search(	@PathVariable String locale, 
    						@PathVariable String currency, 
    						@PathVariable String category,
    						@PathVariable String term, 
    						@PathVariable int page, 
    						@PathVariable int size, 
    						@PathVariable String sortBy, 
    						@RequestBody  NavFacetContainer selectedFacets) {
    	return searchService.findAll(locale, currency, category, term, page, size, sortBy, selectedFacets);
    }
	
}
