package io.nzbee.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.Brand;
import io.nzbee.domain.services.brand.IBrandService;

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
    	return brandService.findAll(lcl);
    }

    @GetMapping("/Brand/{lcl}/{curr}/id/{brandId}")
    public Brand getBrand(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long brandId) {
    	return brandService.findOne(lcl, brandId);
    }

}
