package io.nzbee.resource.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.resources.brand.BrandResource;

@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    public BrandController() {
        super();
    }
    
    @GetMapping("/Brand/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<Resources<BrandResource>> getBrands(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryCode) {
    	final List<BrandResource> collection = 
    			brandService.findAll(locale, currency, categoryCode).stream()
        		.map(b -> new BrandResource(locale, currency, b))
        		.collect(Collectors.toList());
    	
    	final Resources <BrandResource> resources = new Resources <> (collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "brands"));
        return ResponseEntity.ok(resources);
    }
    
    @GetMapping("/Brand/{locale}/{currency}")
    public List<Brand> getBrands(@PathVariable String locale, @PathVariable String currency) {
    	return brandService.findAll(locale, currency);
    }

    @GetMapping("/Brand/{locale}/{currency}/id/{brandId}")
    public Brand getBrand(@PathVariable String locale, @PathVariable String currency, @PathVariable Long brandId) {
    	return brandService.findById(locale, currency, brandId).get();
    }
}
