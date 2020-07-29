package io.nzbee.resources.controllers;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
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
import io.nzbee.resources.category.CategoryFacetResource;
import io.nzbee.resources.category.CategoryFacetResourceAssembler;
import io.nzbee.resources.category.CategoryResource;
import io.nzbee.resources.category.CategoryResourceAssembler;
import io.nzbee.search.dto.facet.EntityFacet;
import io.nzbee.search.dto.facet.IFacet;
import io.nzbee.search.dto.facet.IFacetMapper;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class CategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IFacetMapper<Category> facetMapper;

	@Autowired
	private CategoryResourceAssembler categoryResourceAssember;
	
	@Autowired
	private CategoryFacetResourceAssembler categoryFacetResourceAssembler;

	public CategoryController() {
		super();
	}

	@GetMapping("/Category/{locale}/{currency}")
	public ResponseEntity<CollectionModel<CategoryResource>> getCategories(@PathVariable String locale,
			@PathVariable String currency) {
		LOGGER.debug("Fetching all categories for parameters : {}, {}", locale, currency);
		final Set<Category> collection = categoryService.findAll(locale, currency);
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}

	@GetMapping("/ProductCategory/{locale}/{currency}")
	public ResponseEntity<CollectionModel<CategoryResource>> getProductCategories(@PathVariable String locale,
			@PathVariable String currency) {
		LOGGER.debug("Fetching product categories for parameters : {}, {}", locale, currency);
		final Set<ProductCategory> collection = categoryService.findAllProductCategories(locale, currency);
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}

	@GetMapping("/BrandCategory/{locale}/{currency}")
	public ResponseEntity<CollectionModel<CategoryResource>> getBrandCategories(@PathVariable String locale,
			@PathVariable String currency) {
		LOGGER.debug("Fetching brand categories for parameters : {}, {}", locale, currency);
		final Set<BrandCategory> collection = categoryService.findAllBrandCategories(locale, currency);
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}

	@GetMapping("/Category/{locale}/{currency}/product/{productCode}")
	public ResponseEntity<CollectionModel<CategoryResource>> getCategories(@PathVariable String locale,
			@PathVariable String currency, @PathVariable String productCode) {
		LOGGER.debug("Fetching categories for parameters : {}, {}, {}", locale, currency, productCode);
		final Set<ProductCategory> collection = categoryService.findAllByProductCode(locale, currency, productCode);
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}

	@GetMapping("/Category/{locale}/{currency}/code/{categoryCode}")
	public ResponseEntity<CategoryResource> getCategory(@PathVariable String locale, @PathVariable String currency,
			@PathVariable String categoryCode) {
		LOGGER.debug("Fetching category for parameters : {}, {}, {}", locale, currency, categoryCode);
		Category c = categoryService.findByCode(locale, currency, categoryCode);
		return ResponseEntity.ok(categoryResourceAssember.toModel(c));
	}

	@PostMapping("/Category/{locale}/{currency}/code/{categoryCode}")
	public ResponseEntity<CollectionModel<CategoryResource>> getChildCategories(@PathVariable String locale,
			@PathVariable String currency, @PathVariable String categoryCode,
			@RequestBody Set<IFacet> selectedFacets) {
		LOGGER.debug("call CategoryController.getChildCategories with parameters : {}, {}, {}", locale, currency,
				categoryCode);
		
		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("maxPrice")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
		
		final Set<Category> collection = categoryService.findAll(locale, currency, categoryCode,
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 maxPrice);
		
		
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}
	
	@PostMapping("/CategoryFacet/{locale}/{currency}/code/{categoryCode}")
	public ResponseEntity<CollectionModel<CategoryFacetResource>> getChildCategoryFacets(@PathVariable String locale,
			@PathVariable String currency, @PathVariable String categoryCode,
			@RequestBody Set<IFacet> selectedFacets) {
		LOGGER.debug("call CategoryController.getChildCategoryFacets with parameters : {}, {}, {}", locale, currency,
				categoryCode);
		
		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("maxPrice")).map(p -> p.getValue()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
		
		final Set<EntityFacet> collection = categoryService.findAll(locale, currency, categoryCode,
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 maxPrice)
															.stream().map(c -> facetMapper.toEntityFacet(c)).collect(Collectors.toSet());
		
		return ResponseEntity.ok(categoryFacetResourceAssembler.toCollectionModel(collection));
	}
	
	@PostMapping("/Category/{locale}/{currency}/code/{categoryCode}/maxPrice")
	public ResponseEntity<Double> getMaxPrice(@PathVariable String locale,
			@PathVariable String currency, @PathVariable String categoryCode,
			@RequestBody Set<IFacet> selectedFacets) {
		LOGGER.debug("call CategoryController.getMaxPrice with parameters : {}, {}, {}", locale, currency,
				categoryCode);
		
		
		 Double result = categoryService.getMaxPrice(locale, currency, categoryCode, 
													 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
													 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
													 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()));
		return ResponseEntity.ok(result);
	}

}
