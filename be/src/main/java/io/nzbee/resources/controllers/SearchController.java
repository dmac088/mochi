package io.nzbee.resources.controllers;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.dto.product.IProductDTOFullMapper;
import io.nzbee.dto.product.IProductDTOLightMapper;
import io.nzbee.resources.product.ProductLightResource;
import io.nzbee.resources.product.ProductLightResourceAssembler;
import io.nzbee.resources.search.SearchFacetResource;
import io.nzbee.resources.search.SearchFacetResourceAssembler;
import io.nzbee.resources.search.SearchResultResource;
import io.nzbee.search.facet.IFacet;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class SearchController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IProductDTOFullMapper productDTOFullMapper;
	
	@Autowired
	private IProductDTOLightMapper productDTOLightMapper;
	
	@Autowired
    private IProductPortService productService;
	
	@Autowired
    private ProductLightResourceAssembler prodLightResourceAssembler;
	
	@Autowired
    private PagedResourcesAssembler<ProductLightResource> prodPagedAssembler;
	
	@Autowired
	private SearchFacetResourceAssembler searchFacetResourceAssembler;
	
	@Autowired
	private IProductPortService ipps;
	
	
	@PostMapping(value = "/Search/{locale}/{currency}/Category/{category}",
    					params = { "q", "page", "size", "sort" })
    public ResponseEntity<SearchResultResource> search(	
						    						@PathVariable String 		 locale,
						    						@PathVariable String 	  	 currency, 
						    						@PathVariable String 	  	 category,
						    						@RequestParam("q") 	  String term, 
						    						@RequestParam("page") String page,
											    	@RequestParam("size") String size, 
											    	@RequestParam("sort") String sort, 
						    						@RequestBody  Set<IFacet> 	 selectedFacets) {

		LOGGER.debug("Searching for products with patameters: {}, {}, {}, {}, {}, {}, {}", locale, currency, category, term, page, size, sort);
		
		final Set<IFacet> returnFacets = new HashSet<IFacet>();
		
    	//get the resulting pages of product
    	final Page<ProductLightResource> pages = ipps.search(locale, 
		    												currency,
		    												category, 
		    												Integer.parseInt(page), 
		    												Integer.parseInt(size),
		    												sort,
		    												term, 
		    												selectedFacets,
		    												returnFacets).map(p -> prodLightResourceAssembler.toModel(productDTOLightMapper.doToDto(p)));
    	
    	Set<SearchFacetResource> ssf = searchFacetResourceAssembler.toCollectionModel(returnFacets);
    	
    	return ResponseEntity.ok(new SearchResultResource(prodPagedAssembler.toModel(pages), ssf));
    }
	
	@GetMapping(value = "/Search/{locale}/{currency}/Suggest",
			params = { "q" })
	public ResponseEntity<String[]> getSuggestions(	@PathVariable String locale, 
									@PathVariable String currency, 
									@RequestParam("q") String term) {
		LOGGER.debug("Searching for suggestions with patameters: {}, {}", locale, term);
		return new ResponseEntity< >(productService.getSuggestion(term, locale, currency), HttpStatus.OK);
	}
	
}
