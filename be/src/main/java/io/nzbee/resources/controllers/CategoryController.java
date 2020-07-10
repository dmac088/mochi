package io.nzbee.resources.controllers;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.resources.category.CategoryResource;
import io.nzbee.resources.category.CategoryResourceAssembler;
import io.nzbee.search.dto.facet.FacetContainer;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class CategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ICategoryService categoryService;
    
    @Autowired
    private CategoryResourceAssembler categoryResourceAssember;

    public CategoryController() {
        super();
    }
	
    @GetMapping("/Category/{locale}/{currency}")
    public ResponseEntity<CollectionModel<CategoryResource>> getCategories(@PathVariable String locale, @PathVariable String currency) {
    	LOGGER.debug("Fetching all categories for parameters : {}, {}", locale, currency);
    	final Set<Category> collection = categoryService.findAll(locale, currency);
    	return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
    }
    
    @GetMapping("/ProductCategory/{locale}/{currency}")
    public ResponseEntity<CollectionModel<CategoryResource>> getProductCategories(@PathVariable String locale, @PathVariable String currency) {
    	LOGGER.debug("Fetching product categories for parameters : {}, {}", locale, currency);
    	final Set<ProductCategory> collection = categoryService.findAllProductCategories(locale, currency);
    	return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
    }
    
    @GetMapping("/BrandCategory/{locale}/{currency}")
    public ResponseEntity<CollectionModel<CategoryResource>> getBrandCategories(@PathVariable String locale, @PathVariable String currency) {
    	LOGGER.debug("Fetching brand categories for parameters : {}, {}", locale, currency);
    	final Set<BrandCategory> collection = categoryService.findAllBrandCategories(locale, currency);
    	return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
    }
    
    @GetMapping("/Category/{locale}/{currency}/product/{productCode}")
    public ResponseEntity<CollectionModel<CategoryResource>> getCategories(@PathVariable String locale, @PathVariable String currency, @PathVariable String productCode) {
    	LOGGER.debug("Fetching categories for parameters : {}, {}, {}", locale, currency, productCode);
    	final Set<ProductCategory> collection = categoryService.findAllByProductCode(locale, currency, productCode);
    	return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
    } 
    
    @GetMapping("/Category/{locale}/{currency}/code/{categoryCode}")
    public ResponseEntity<CategoryResource> getCategory(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryCode) {
    	LOGGER.debug("Fetching category for parameters : {}, {}, {}", locale, currency, categoryCode);
    	Category c = categoryService.findByCode(locale, currency, categoryCode);
    	return ResponseEntity.ok(categoryResourceAssember.toModel(c));
    }
    
    @PostMapping("/Category/{locale}/{currency}/code/{categoryCode}")
    public ResponseEntity<CollectionModel<CategoryResource>> getChildCategories(@PathVariable String locale, 
    														   @PathVariable String currency, 
    														   @PathVariable String categoryCode, 
    														   @RequestBody final FacetContainer selectedFacets) {
    	LOGGER.debug("call CategoryController.getChildCategories with parameters : {}, {}, {}", locale, currency, categoryCode);
    	final Set<Category> collection = categoryService.findAll(locale, currency, categoryCode, selectedFacets.getFacets());
    	return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
    }
   
}
