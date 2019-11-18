package io.nzbee.resources.controllers;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
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
    									new HashSet<IFacet>(), 
    									"1");
    			

    	//convert the page of products to a page of product resources
    	final Page<ProductResource> prPages = new PageImpl<ProductResource>(pages.stream()
							    											.map(p -> new ProductResource(p))
							    											.collect(Collectors.toList()),
							    											pages.getPageable(),
							    											pages.getTotalElements());
    	
    	return new ResponseEntity< >(assembler.toResource(prPages), HttpStatus.OK);
    }
    
	@GetMapping(value = "/Product/{locale}/{currency}/brand/code/{brandCode}", 
    			params = { "page", "size" })
    public ResponseEntity<PagedResources<ProductResource>> getProductsByBrand(@PathVariable String locale, 
															    	   @PathVariable String currency, 
															    	   @PathVariable String categoryCode,
															    	   @RequestParam("page") int page,
															    	   @RequestParam("size") int size,
															    	   @SuppressWarnings("rawtypes") PagedResourcesAssembler assembler) {
    	return null;
    }
    
    @GetMapping("/Product/{locale}/{currency}/code/{code}")
    public ResponseEntity<ProductResource> get(@PathVariable String locale, @PathVariable String currency, @PathVariable String code) {
    	ProductResource pr = new ProductResource(productService.findByCode(locale, currency, code).get());
    	return new ResponseEntity< >(pr, HttpStatus.OK);
    }
    
    @PostMapping("/Product/{locale}/{currency}")
    public ResponseEntity<Resources<ProductResource>> getProducts(@PathVariable String locale, @PathVariable String currency, @RequestBody final List<String> productCodes) {
    	
    	final List<ProductResource> collection = 
    			productService.findAll(locale, currency, productCodes)
    			.stream().map(p -> new ProductResource(p))
    			.collect(Collectors.toList());
    	
    	final Resources <ProductResource> resources = new Resources <> (collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }
    
}
