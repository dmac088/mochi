package io.nzbee.domain.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ICategoryService;

@RestController
@RequestMapping("/api", produces = "application/hal+json")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    public CategoryController() {
        super();
    }
    
    @GetMapping("/Category/{locale}/{currency}/level/{level}")
    public List<Category> getCategoriesForLevel(@PathVariable String locale, @PathVariable String currency, @PathVariable Long level) {
    	return categoryService.findAllForLevel(locale, currency, level);
    }
    
    @GetMapping("/Category/{locale}/{currency}/desc/{categoryDesc}")
    public Category getCategory(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryDesc) {
    	return categoryService.findByDesc(locale, currency, categoryDesc).get();
    }
   
}
