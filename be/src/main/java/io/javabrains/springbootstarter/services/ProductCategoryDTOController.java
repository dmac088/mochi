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
    
    @GetMapping("/ProductCategory/{lcl}")
    public List<ProductCategoryDTO> getProductCategories(@PathVariable String lcl) {
    	return productCategoryService.getProductCategories(lcl);
    }
    
    @GetMapping("/ProductCategory/{lcl}/ParentCategory/{parentCat}")
    public List<ProductCategoryDTO> getProductCategories(@PathVariable String lcl,@PathVariable Long parentCat) {
    	return productCategoryService.getProductCategoryParent(lcl, parentCat);
    }
    
    @GetMapping("/ProductCategory/{lcl}/level/{level}")
    public List<ProductCategoryDTO> getProductCategoriesForLevel(@PathVariable String lcl,@PathVariable Long level) {
    	return productCategoryService.getProductCategoriesForLevel(lcl, level);
    }
    
    @GetMapping("/ProductCategory/{lcl}/preview")
    public List<ProductCategoryDTO> getProductCategoriesForLevel(@PathVariable String lcl) {
    	return productCategoryService.getPreviewProductCategories(lcl, new Long(1));
    }

    @GetMapping("/ProductCategory/{lcl}/id/{categoryId}")
    public ProductCategoryDTO getProductCategory(@PathVariable String lcl, @PathVariable Long categoryId) {
    	return productCategoryService.getProductCategory(lcl, categoryId);
    }
    
    @GetMapping("/ProductCategory/{lcl}/desc/{categoryDesc}")
    public ProductCategoryDTO getProductCategory(@PathVariable String lcl, @PathVariable String categoryDesc) {
    	return productCategoryService.getProductCategory(lcl, categoryDesc);
    }
}
