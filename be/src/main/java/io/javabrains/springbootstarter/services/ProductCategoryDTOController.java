package io.javabrains.springbootstarter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductCategoryDTOController {

    @Autowired
    private IProductCategoryDTOService productCategoryService;

    public ProductCategoryDTOController() {
        super();
    }
    
    @GetMapping("/ProductCategory/{lcl}/{curr}")
    public List<ProductCategoryDTO> getProductCategories(@PathVariable String lcl, @PathVariable String curr) {
    	return productCategoryService.getProductCategories(lcl, curr);
    }
    
    @GetMapping("/ProductCategory/{lcl}/{curr}/ParentCategory/{parentCat}")
    public List<ProductCategoryDTO> getProductCategories(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long parentCat) {
    	return productCategoryService.getProductCategoryParent(lcl, curr, parentCat);
    }
    
    @GetMapping("/ProductCategory/{lcl}/{curr}/level/{level}")
    public List<ProductCategoryDTO> getProductCategoriesForLevel(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long level) {
    	return productCategoryService.getProductCategoriesForLevel(lcl, curr, level);
    }
    
    @GetMapping("/ProductCategory/{lcl}/{curr}/preview")
    public List<ProductCategoryDTO> getProductCategoriesForLevel(@PathVariable String lcl, @PathVariable String curr) {
    	return productCategoryService.getPreviewProductCategories(lcl, curr, new Long(1));
    }

    @GetMapping("/ProductCategory/{lcl}/{curr}/id/{categoryId}")
    public ProductCategoryDTO getProductCategory(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long categoryId) {
    	return productCategoryService.getProductCategory(lcl, curr, categoryId);
    }
    
    @GetMapping("/ProductCategory/{lcl}/{curr}/desc/{categoryDesc}")
    public ProductCategoryDTO getProductCategory(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc) {
    	return productCategoryService.getProductCategory(lcl, curr, categoryDesc);
    }
}
