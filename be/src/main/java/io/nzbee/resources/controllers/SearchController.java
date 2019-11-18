package io.nzbee.resources.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.ui.component.web.facet.FacetContainer;
import io.nzbee.ui.component.web.facet.IFacet;
import io.nzbee.ui.component.web.search.ISearchService;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @SuppressWarnings("unchecked")
	@PostMapping(value = "/Search/{locale}/{currency}/Category/{category}/SortBy/{sortBy}",
    					params = { "q", "page", "size" })
    public ResponseEntity<PagedResources<ProductResource>> search(	@PathVariable String locale, 
    						@PathVariable String currency, 
    						@PathVariable String category,
    						@RequestParam("q") String term, 
    						@RequestParam("page") int page,
					    	@RequestParam("size") int size, 
    						@PathVariable String sortBy, 
    						@RequestBody  FacetContainer selectedFacets,
    						@SuppressWarnings("rawtypes") PagedResourcesAssembler assembler) {
    	
    	final Set<IFacet> returnFacets = new HashSet<IFacet>();
    	
    	//get teh resulting pages of product
    	final Page<Product> pages = searchService.findAll(	locale, 
    														currency, 
    														category, 
    														term, 
    														page,
    														size, 
    														sortBy, 
    														selectedFacets.getFacets(),
    														returnFacets);
    	
    	//convert the page of products to a page of product resources
    	final Page<ProductResource> prPages = new PageImpl<ProductResource>(pages.stream()
							    											.map(p -> new ProductResource(p))
							    											.collect(Collectors.toList()),
							    											pages.getPageable(),
							    											pages.getTotalElements());
    	
    	
    	return new ResponseEntity< >(assembler.toResource(prPages), HttpStatus.OK);
    }
	
}
