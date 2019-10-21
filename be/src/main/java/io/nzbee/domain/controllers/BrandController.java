package io.nzbee.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.IBrandService;

@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    public BrandController() {
        super();
    }
    
    @GetMapping("/Brand/{locale}/{currency}")
    public List<Brand> getBrands(@PathVariable String locale, @PathVariable String currency) {
    	return brandService.findAll(locale, currency);
    }

    @GetMapping("/Brand/{locale}/{currency}/id/{brandId}")
    public Brand getBrand(@PathVariable String locale, @PathVariable String currency, @PathVariable Long brandId) {
    	return brandService.findById(locale, currency, brandId).get();
    }

//    @GetMapping("/Brand/{locale}/{currency}/category/{categoryDesc}")
//    public List<Brand> getBrands(	@PathVariable String locale, 
//    						@PathVariable String currency, 
//    						@PathVariable String categoryDesc) {
//   
//    	return brandService.findAll(locale, currency, categoryDesc);
//    }
}
