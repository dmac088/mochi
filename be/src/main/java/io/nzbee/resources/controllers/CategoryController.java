package io.nzbee.resources.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.resources.category.CategoryResource;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class CategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ICategoryService categoryService;

    public CategoryController() {
        super();
    }
	
    @GetMapping("/Category/{locale}/{currency}")
    public ResponseEntity<Resources<CategoryResource>> getCategories(@PathVariable String locale, @PathVariable String currency) {
    	LOGGER.debug("Fetching categories for parameters : {}, {}", locale, currency);

    	final List<CategoryResource> collection = 
        		categoryService.findAll(locale, currency).stream()
        		.map(c -> new CategoryResource(locale, currency, c))
        		.collect(Collectors.toList());
        
        final Resources <CategoryResource> resources = new Resources <> (collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    } 
    
    @GetMapping("/Category/{locale}/{currency}/code/{categoryCode}")
    public ResponseEntity<CategoryResource> get(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryCode) {
    	LOGGER.debug("Fetching category for parameters : {}, {}, {}", locale, currency, categoryCode);
    	
    	Category c = categoryService.findByCode(locale, currency, categoryCode);
    	CategoryResource cr = new CategoryResource(locale, currency, c);
    	return ResponseEntity.ok(cr);
    }
   
}
