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

import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.resources.category.CategoryResource;

@RestController
@RequestMapping(value = "/api/categories", produces = "application/hal+json")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    public CategoryController() {
        super();
    }
    
    /*
    @GetMapping("/Category/{locale}/{currency}/level/{level}")
    public List<Category> getCategoriesForLevel(@PathVariable String locale, @PathVariable String currency, @PathVariable Long level) {
    	return categoryService.findAllForLevel(locale, currency, level);
    }
    
    @GetMapping("/Category/{locale}/{currency}/desc/{categoryDesc}")
    public Category getCategory(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryDesc) {
    	return categoryService.findByDesc(locale, currency, categoryDesc).get();
    }
    */
    
    @GetMapping("/test")
    	public ResponseEntity<Resources<CategoryResource >> getCategories(String locale, String currency) {
        final List<CategoryResource> collection = 
        		categoryService.findAll(locale, currency).stream()
        		.map(c -> new CategoryResource(c))
        		.collect(Collectors.toList());
        
        final Resources <CategoryResource> resources = new Resources < > (collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }
   
}
