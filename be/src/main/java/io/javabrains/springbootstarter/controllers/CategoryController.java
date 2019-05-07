package io.javabrains.springbootstarter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.javabrains.springbootstarter.domain.Category;
import io.javabrains.springbootstarter.dto.SidebarFacetDTO;
import io.javabrains.springbootstarter.services.ICategoryService;
import variables.CategoryVars;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    public CategoryController() {
        super();
    }
    
    @GetMapping("/Category/{lcl}/{curr}")
    public List<Category> getCategories(@PathVariable String lcl, @PathVariable String curr) {
    	return categoryService.getCategories(lcl, curr);
    }
    
    @GetMapping("/Category/{lcl}/{curr}/ParentCategory/{parentCat}")
    public List<Category> getCategories(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long parentCat) {
    	return categoryService.getCategoryParent(lcl, curr, parentCat);
    }
    
    @GetMapping("/Category/{lcl}/{curr}/level/{level}")
    public List<Category> getCategoriesForLevel(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long level) {
    	return categoryService.getCategoriesForLevel(lcl, curr, level);
    }

    @GetMapping("/Category/{lcl}/{curr}/id/{categoryId}")
    public Category getCategory(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long categoryId) {
    	return categoryService.getCategory(lcl, curr, categoryId);
    }
    
    @GetMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}")
    public Category getCategory(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc) {
    	return categoryService.getCategory(lcl, curr, categoryDesc);
    }
    
    @PostMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}/children")
    public List<SidebarFacetDTO> getCategoryChildren(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<SidebarFacetDTO> brandFacets) {
    	return categoryService.getCategoryChildren(CategoryVars.PRIMARY_HIERARCHY_CODE, lcl, curr, categoryDesc, brandFacets);
    }
}
