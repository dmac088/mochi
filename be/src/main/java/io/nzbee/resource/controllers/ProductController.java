package io.nzbee.resource.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.nzbee.dto.product.IProductService;
import io.nzbee.dto.product.Product;
import io.nzbee.resources.brand.BrandResource;
import io.nzbee.resources.product.ProductResource;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;
    
    @GetMapping("/Product/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<Resources<ProductResource>> getBrands(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryCode) {
    	final List<BrandResource> collection = 
    			productService.findAll(locale, currency, page, size, categoryDesc, ldto, sortBy)
    			productService.findAll(locale, currency, categoryCode).stream()
        		.map(b -> new BrandResource(locale, currency, b))
        		.collect(Collectors.toList());
    	
    	final Resources <BrandResource> resources = new Resources <> (collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "brands"));
        return ResponseEntity.ok(resources);
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
