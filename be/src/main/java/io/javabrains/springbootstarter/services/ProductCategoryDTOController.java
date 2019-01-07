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
    
    @GetMapping("/ProductCategory/{lcl}/cat/{parentCat}")
    public List<ProductCategoryDTO> getProductCategories(@PathVariable String lcl,@PathVariable Long parentCat) {
    	return productCategoryService.getProductCategories(lcl, parentCat);
    }
    
}
