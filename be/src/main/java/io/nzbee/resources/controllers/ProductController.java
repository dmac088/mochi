package io.nzbee.resources.controllers;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.resources.product.ProductResourceAssembler;
import io.nzbee.search.dto.facet.FacetContainer;
import io.nzbee.search.dto.facet.IFacet;

@RestController
@RequestMapping("/api")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IProductService productService;
    
    @Autowired
    private ProductResourceAssembler prodResourceAssembler;
    
    @Autowired
    private PagedResourcesAssembler<ProductResource> prodPagedAssembler;
    
    
	@GetMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<PagedModel<EntityModel<ProductResource>>> getProducts(@PathVariable String locale, 
															    	   @PathVariable String currency, 
															    	   @PathVariable String categoryCode,
															    	   @RequestParam(value = "page", defaultValue = "0") String page,
															    	   @RequestParam(value = "size", defaultValue = "10") String size,
															    	   @RequestParam(value = "sort", defaultValue = "10") String sort
															    	   ) {
    	
    	LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, page, size);
    	
    	final Page<ProductResource> pages = productService.findAll(	locale, 
    																currency, 
    																categoryCode, 
																	new HashSet<String>(), 
																	new HashSet<String>(),
																	new HashSet<String>(),
																	null,
																	page, 
																	size, 
																	sort
														  		  ).map(p -> prodResourceAssembler.toModel(p));
    			
    	return ResponseEntity.ok(prodPagedAssembler.toModel(pages));
    }
    
	@GetMapping(value = "/Product/{locale}/{currency}/brand/code/{brandCode}", 
    			params = { "page", "size" })
    public ResponseEntity<PagedModel<ProductResource>> getProductsByBrand(	@PathVariable String locale, 
															    	   		@PathVariable String currency, 
															    	   		@PathVariable String brandCode,
															    	   		@RequestParam("page") int page,
															    	   		@RequestParam("size") int size) {
    	return null;
    }
    
    @GetMapping("/Product/{locale}/{currency}/code/{code}")
    public ResponseEntity<ProductResource> get(	@PathVariable String locale, 
    											@PathVariable String currency, 
    											@PathVariable String code) {
    	ProductResource pr = prodResourceAssembler.toModel(productService.findByCode(locale, currency, code));
    	return new ResponseEntity< >(pr, HttpStatus.OK);
    }
    
    @PostMapping("/Product/{locale}/{currency}")
    public ResponseEntity<CollectionModel<ProductResource>> getProducts(	@PathVariable String locale, 
    																		@PathVariable String currency, 
    																		@RequestBody final Set<String> productCodes) {
    	
    	LOGGER.debug("Fetching product for parameters : {}, {}, {}}", locale, currency, productCodes);
    	
    	final Set<Product> collection = productService.findAll(locale, currency, productCodes);
    	
        return ResponseEntity.ok(prodResourceAssembler.toCollectionModel(collection));
    }
    
  
	@PostMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}",
			 	 params = { "page", "size", "sort" })
	public ResponseEntity<PagedModel<EntityModel<ProductResource>>> getProducts(	
										@PathVariable String 			locale, 
										@PathVariable String 			currency, 
										@PathVariable 					String 	categoryCode,
										@RequestParam("page")			String page,
								    	@RequestParam("size") 			String size, 
								    	@RequestParam("sort") 			String sort,
										@RequestBody  					Set<IFacet> selectedFacets) {
		
		
		LOGGER.debug("call ProductController.getProducts with parameters : {}, {}, {}, {}, {}, {}, {}", locale, currency, categoryCode, page, size, sort, selectedFacets.size());
		
		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("maxPrice")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}

			
		final Page<ProductResource> pages = productService.findAll(locale, currency, categoryCode, 
																	selectedFacets.stream().filter(c -> c.getFacetingName().equals("category")).map(c -> c.getId())
																	.collect(Collectors.toSet()), 
																	selectedFacets.stream().filter(c -> c.getFacetingName().equals("brand")).map(c -> c.getId())
																	.collect(Collectors.toSet()), 
																	selectedFacets.stream().filter(c -> c.getFacetingName().equals("tag")).map(c -> c.getId())
																	.collect(Collectors.toSet()), 
																	maxPrice,
																	page, 
																	size, 
																	sort
														  		  ).map(p -> prodResourceAssembler.toModel(p));
		
		return ResponseEntity.ok(prodPagedAssembler.toModel(pages));
	}
    
}
