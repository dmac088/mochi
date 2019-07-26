package io.nzbee.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.domain.Brand;
import io.nzbee.domain.services.brand.IBrandService;
import io.nzbee.ui.component.web.sidebar.Sidebar;
import io.nzbee.variables.CategoryVars;

@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    public BrandController() {
        super();
    }
    
    @GetMapping("/Brand/{lcl}/{curr}")
    public List<Sidebar> getBrands(@PathVariable String lcl, @PathVariable String curr) {
    	return brandService.getBrands(lcl, curr);
    }

    @GetMapping("/Brand/{lcl}/{curr}/id/{brandId}")
    public Brand getBrand(@PathVariable String lcl, @PathVariable String curr, @PathVariable Long brandId) {
    	return brandService.getBrand(lcl, curr, brandId);
    }
    
    @PostMapping("/Brand/{lcl}/{curr}/category/{categoryDesc}")
    public List<Sidebar> getBrands(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<Sidebar> facets) {
    	return brandService.getBrands(CategoryVars.PRIMARY_HIERARCHY_CODE, lcl, curr, categoryDesc, facets);
    }
}
