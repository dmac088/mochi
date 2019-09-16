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
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    public CategoryController() {
        super();
    }
//
//    @GetMapping("/Category/{lcl}/{curr}/ParentCategory/{parentCat}")
//    public List<Category> getCategories(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long parentCategory) {
//    	return categoryService.findByParent(lcl, parentCategory);
//    }
    
    @GetMapping("/Category/{lcl}/{curr}/level/{level}")
    public List<Category> getCategoriesForLevel(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long level) {
    	return categoryService.findAllForLevel(lcl, level);
    }
    
    @GetMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}")
    public Category getCategory(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc) {
    	return categoryService.findOneByDesc(lcl, categoryDesc).get();
    }
   
}
