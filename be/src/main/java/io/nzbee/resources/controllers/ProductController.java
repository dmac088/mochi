package io.nzbee.resources.controllers;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.search.dto.facet.IFacet;

@RestController
@RequestMapping("/api")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IProductPortService productService;
    
    @Autowired
    private RepresentationModelAssemblerSupport<Product, ProductResource> prodAssembler;
    
    @Autowired
    private PagedResourcesAssembler<ProductResource> prodPagedAssembler;
    
    @SuppressWarnings("unchecked")
	@GetMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<PagedModel<ProductResource>> getProducts(@PathVariable String locale, 
															    	   @PathVariable String currency, 
															    	   @PathVariable String categoryCode,
															    	   @RequestParam(value = "page", defaultValue = "0") String page,
															    	   @RequestParam(value = "size", defaultValue = "10") String size,
															    	   @SuppressWarnings("rawtypes") PagedResourcesAssembler assembler) {
    	
    	LOGGER.debug("Fetching products for parameters : {}, {}, {}, {}, {}", locale, currency, categoryCode, page, size);
    	
    	final Page<Product> pages =
    					productService.findAll(	
    									locale, 
    									currency,
    									categoryCode, 
    									Integer.parseInt(page), 
    									Integer.parseInt(size),
    									new HashSet<IFacet>(), 
    									"1");
    			

    	//convert the page of products to a page of product resources
    	final Page<ProductResource> prPages = new PageImpl<ProductResource>(pages.stream()
							    											.map(p -> prodAssembler.toModel(p))
							    											.collect(Collectors.toList()),
							    											pages.getPageable(),
							    											pages.getTotalElements());
    	
    	return new ResponseEntity< >(assembler.toModel(prPages), HttpStatus.OK);
    }
    
	@GetMapping(value = "/Product/{locale}/{currency}/brand/code/{brandCode}", 
    			params = { "page", "size" })
    public ResponseEntity<PagedModel<ProductResource>> getProductsByBrand(	@PathVariable String locale, 
															    	   			@PathVariable String currency, 
															    	   			@PathVariable String brandCode,
															    	   			@RequestParam("page") int page,
															    	   			@RequestParam("size") int size,
															    	   			@SuppressWarnings("rawtypes") PagedResourcesAssembler assembler,
															    	   			@SuppressWarnings("rawtypes") RepresentationModelAssemblerSupport prodAssembler) {
    	return null;
    }
    
    @GetMapping("/Product/{locale}/{currency}/code/{code}")
    public ResponseEntity<ProductResource> get(	@PathVariable String locale, 
    											@PathVariable String currency, 
    											@PathVariable String code) {
    	ProductResource pr = prodAssembler.toModel(productService.findByCode(locale, currency, code));
    	return new ResponseEntity< >(pr, HttpStatus.OK);
    }
    
    @PostMapping("/Product/{locale}/{currency}")
    public ResponseEntity<PagedModel<EntityModel<ProductResource>>> getProducts(	@PathVariable String locale, 
    																				@PathVariable String currency, 
    																				@RequestBody final Set<String> productCodes) {
    	
    	LOGGER.debug("Fetching product for parameters : {}, {}, {}}", locale, currency, productCodes);
    	
    	final List<ProductResource> collection = 
    			productService.findAll(locale, currency, productCodes)
    			.stream().map(p -> prodAssembler.toModel(p))
    			.collect(Collectors.toList());
    	
    	Page<ProductResource> pages = new PageImpl<>(collection);
    	
    	final PagedModel<EntityModel<ProductResource>> resources = prodPagedAssembler.toModel(pages);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }
    
}
