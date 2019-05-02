package io.javabrains.springbootstarter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.javabrains.springbootstarter.domain.Brand;
import io.javabrains.springbootstarter.services.IBrandService;

@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    public BrandController() {
        super();
    }
    
    @GetMapping("/Brand/{lcl}/{curr}")
    public List<Brand> getBrands(@PathVariable String lcl, @PathVariable String curr) {
    	return brandService.getBrands(lcl, curr);
    }

    @GetMapping("/Brand/{lcl}/{curr}/id/{brandId}")
    public Brand getBrand(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long brandId) {
    	return brandService.getBrand(lcl, curr, brandId);
    }
    
    @GetMapping("/Brand/{lcl}/{curr}/category/{categoryDesc}")
    public List<Brand> getBrand(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc) {
    	return brandService.getBrandsForCategory(lcl, curr, categoryDesc);
    }
}
