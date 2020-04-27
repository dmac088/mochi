package io.nzbee.resources.controllers;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.product.Product;
import io.nzbee.dto.facet.IFacet;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.resources.search.SearchResource;
import io.nzbee.search.ISearchService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class SearchController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private ISearchService searchService;
	
	@Autowired
    private ResourceAssembler<Product, ProductResource> prodAssembler;
	
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
						    						PagedResourcesAssembler<ProductResource> assembler) {

		LOGGER.debug("Searching for products with patameters: {}, {}, {}", locale, currency, term);
		
    	final SearchResource sr = new SearchResource(locale, 
													 currency, 
													 category, 
													 term, 
													 page,
													 size, 
													 sortBy, 
													 selectedFacets,
													 assembler,
													 prodAssembler,
													 searchService);
    	
    	return new ResponseEntity< >(sr, HttpStatus.OK);
    }
	
}
