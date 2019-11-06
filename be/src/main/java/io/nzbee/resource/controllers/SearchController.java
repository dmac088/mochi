package io.nzbee.resource.controllers;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.facet.FacetContainer;
import io.nzbee.ui.component.web.facet.IFacet;
import io.nzbee.ui.component.web.search.ISearchService;
import io.nzbee.ui.component.web.search.Search;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @SuppressWarnings("unchecked")
	@PostMapping(value = "/Search/{locale}/{currency}/Category/{category}/SearchTerm/{term}/SortBy/{sortBy}",
    					params = { "page", "size" })
    public Search search(	@PathVariable String locale, 
    						@PathVariable String currency, 
    						@PathVariable String category,
    						@PathVariable String term, 
    						@RequestParam("page") int page,
					    	@RequestParam("size") int size, 
    						@PathVariable String sortBy, 
    						@RequestBody  FacetContainer selectedFacets,
    						@SuppressWarnings("rawtypes") PagedResourcesAssembler assembler) {
    	
    	final Set<IFacet> returnFacets = new HashSet<IFacet>();
    	
    	final Page<Product> pages = searchService.findAll(	locale, 
    														currency, 
    														category, 
    														term, 
    														page,
    														size, 
    														sortBy, 
    														selectedFacets.getFacets(),
    														returnFacets);
    	
    	Search s = new Search();
    	s.setFacets(returnFacets);
    	s.setProducts(assembler.toResource(pages));
    	return s;
    }
	
}
