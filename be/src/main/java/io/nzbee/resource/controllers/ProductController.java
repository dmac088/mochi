package io.nzbee.resource.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
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
import io.nzbee.ui.component.web.facet.IFacet;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;
    
    @SuppressWarnings("unchecked")
	@GetMapping(value = "/Product/{locale}/{currency}/category/{categoryCode}", 
    			params = { "page", "size" })
    public ResponseEntity<PagedResources<ProductResource>> getProducts(@PathVariable String locale, 
															    	   @PathVariable String currency, 
															    	   @PathVariable String categoryCode,
															    	   @RequestParam("page") int page,
															    	   @RequestParam("size") int size,
															    	   @SuppressWarnings("rawtypes") PagedResourcesAssembler assembler) {
    	final Page<Product> pages =
    					productService.findAll(	
    									locale, 
    									currency,
    									PageRequest.of(page, size), 
    									categoryCode, 
    									new ArrayList<IFacet>(), 
    									"1");
    			

    	return new ResponseEntity< >(assembler.toResource(pages), HttpStatus.OK);
    	
//    	final Resources <BrandResource> resources = new Resources <> (collection);
//        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
//        resources.add(new Link(uriString, "brands"));
//        return ResponseEntity.ok(resources);
    }
    
    @GetMapping("/Product/{locale}/{currency}/code/{code}")
    public Product getProduct(@PathVariable String locale, @PathVariable String currency, @PathVariable String code) {
    	return productService.findByCode(locale, currency, code).get();
    }
    
    @PostMapping("/Product/{locale}/{currency}")
    public List<Product> getProducts(@PathVariable String locale, @PathVariable String currency, @RequestBody final List<String> productCodes) {
    	return productService.findAll(locale, currency, productCodes);
    }
    
}
