package io.javabrains.springbootstarter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ICategoryService CategoryService;

    public CategoryController() {
        super();
    }
    
    @GetMapping("/Category/{lcl}/{curr}")
    public List<Category> getCategories(@PathVariable String lcl, @PathVariable String curr) {
    	return CategoryService.getCategories(lcl, curr);
    }
    
    @GetMapping("/Category/{lcl}/{curr}/ParentCategory/{parentCat}")
    public List<Category> getCategories(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long parentCat) {
    	return CategoryService.getCategoryParent(lcl, curr, parentCat);
    }
    
    @GetMapping("/Category/{lcl}/{curr}/level/{level}")
    public List<Category> getCategoriesForLevel(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long level) {
    	return CategoryService.getCategoriesForLevel(lcl, curr, level);
    }

    @GetMapping("/Category/{lcl}/{curr}/id/{categoryId}")
    public Category getCategory(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long categoryId) {
    	return CategoryService.getCategory(lcl, curr, categoryId);
    }
    
    @GetMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}")
    public Category getCategory(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc) {
    	return CategoryService.getCategory(lcl, curr, categoryDesc);
    }
}
