package io.nzbee.resources.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.resources.search.SearchResource;
import io.nzbee.ui.component.web.facet.IFacet;
import io.nzbee.ui.component.web.search.ISearchService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class SearchController {

	@Autowired
    @Qualifier(value = "SearchService")
    private ISearchService searchService;
	
	@PostMapping(value = "/Search/{locale}/{currency}/Category/{category}/SortBy/{sortBy}",
    					params = { "q", "page", "size" })
    public ResponseEntity<SearchResource> search(	
						    						@PathVariable String 		 locale,
						    						@PathVariable String 	  	 currency, 
						    						@PathVariable String 	  	 category,
						    						@RequestParam("q") String 	 term, 
						    						@RequestParam("page") int 	 page,
											    	@RequestParam("size") int 	 size, 
						    						@PathVariable String 	  	 sortBy, 
						    						@RequestBody  Set<IFacet> 	 selectedFacets,
						    						@SuppressWarnings("rawtypes") PagedResourcesAssembler assembler) {

    	final SearchResource sr = new SearchResource(locale, 
													 currency, 
													 category, 
													 term, 
													 page,
													 size, 
													 sortBy, 
													 selectedFacets,
													 assembler,
													 searchService);
    	
    	return new ResponseEntity< >(sr, HttpStatus.OK);
    }
	
}
