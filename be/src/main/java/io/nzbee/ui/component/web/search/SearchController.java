package io.nzbee.ui.component.web.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.ui.component.web.facet.FacetContainer;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping(value = "/Search/{locale}/{currency}/Category/{category}/SearchTerm/{term}/SortBy/{sortBy}",
    					params = { "page", "size" })
    public Search search(	@PathVariable String locale, 
    						@PathVariable String currency, 
    						@PathVariable String category,
    						@PathVariable String term, 
    						@RequestParam("page") int page,
					    	@RequestParam("size") int size, 
    						@PathVariable String sortBy, 
    						@RequestBody  FacetContainer selectedFacets) {
    	System.out.println(selectedFacets.getFacets().size());
    	return searchService.findAll(locale, currency, category, term, page, size, sortBy, selectedFacets);
    }
	
}
