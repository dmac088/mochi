package io.nzbee.resources.controllers;

import java.util.List;
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
import io.nzbee.resources.product.PriceFacetResource;
import io.nzbee.resources.product.PriceFacetResourceAssembler;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacet;
import io.nzbee.search.facet.IFacetMapper;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class CategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IFacetMapper<Category> categoryFacetMapper;
	
	@Autowired
	private IFacetMapper<Double> priceFacetMapper;

	@Autowired
	private CategoryResourceAssembler categoryResourceAssember;
	
	@Autowired
	private CategoryFacetResourceAssembler categoryFacetResourceAssembler;
	
	@Autowired
	private PriceFacetResourceAssembler priceFacetResourceAssembler;

	public CategoryController() {
		super();
	}

	@GetMapping("/Category/{locale}/{currency}")
	public ResponseEntity<CollectionModel<CategoryResource>> getCategories(@PathVariable String locale) {
		LOGGER.debug("Fetching all categories for parameters : {}, {}", locale);
		final List<Category> collection = categoryService.findAll(locale);
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}

	@GetMapping("/ProductCategory/{locale}/{currency}")
	public ResponseEntity<CollectionModel<CategoryResource>> getProductCategories(@PathVariable String locale) {
		LOGGER.debug("Fetching product categories for parameters : {}, {}", locale);
		final List<ProductCategory> collection = categoryService.findAllProductCategories(locale);
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}

	@GetMapping("/BrandCategory/{locale}/{currency}")
	public ResponseEntity<CollectionModel<CategoryResource>> getBrandCategories(@PathVariable String locale) {
		LOGGER.debug("Fetching brand categories for parameters : {}, {}", locale);
		final List<BrandCategory> collection = categoryService.findAllBrandCategories(locale);
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}

	@GetMapping("/Category/{locale}/{currency}/Code/{code}")
	public ResponseEntity<CategoryResource> getCategory(@PathVariable String locale,
			@PathVariable String categoryCode) {
		LOGGER.debug("Fetching category for parameters : {}, {}, {}", locale, categoryCode);
		Category c = categoryService.findByCode(locale, categoryCode);
		return ResponseEntity.ok(categoryResourceAssember.toModel(c));
	}

	@PostMapping("/Category/{locale}/{currency}/Code/{code}")
	public ResponseEntity<CollectionModel<CategoryResource>> getChildCategories(@PathVariable String locale,
			@PathVariable String currency, @PathVariable String code,
			@RequestBody Set<IFacet> selectedFacets) {
		LOGGER.debug("call CategoryController.getChildCategories with parameters : {}, {}, {}", locale, currency, code);
		
		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getValue()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
 
		
		final List<Category> collection = categoryService.findAll(locale, currency, code,
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 maxPrice);
		
		
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
	}
	
	@PostMapping("/CategoryFacet/{locale}/{currency}/Code/{code}")
	public ResponseEntity<CollectionModel<CategoryFacetResource>> getChildCategoryFacets(@PathVariable String locale,
																						 @PathVariable String currency, 
																						 @PathVariable String code,
																						 @RequestBody Set<IFacet> selectedFacets) {
		LOGGER.debug("call CategoryController.getChildCategoryFacets with parameters : {}, {}, {}", locale, currency,
				code);
		
		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getValue()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
		
		final Set<EntityFacet> collection = categoryService.findAll(locale, currency, code,
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()),
																 maxPrice)
															.stream().map(c -> categoryFacetMapper.toEntityFacet(c)).collect(Collectors.toSet());
		
		return ResponseEntity.ok(categoryFacetResourceAssembler.toCollectionModel(collection));
	}
	
	@PostMapping("/Category/{locale}/{currency}/Code/{code}/maxPrice")
	public ResponseEntity<Double> getMaxPrice(	@PathVariable String locale,
												@PathVariable String currency, @PathVariable String code,
												@RequestBody  Set<IFacet> selectedFacets) {
		LOGGER.debug("call CategoryController.getMaxPrice with parameters : {}, {}, {}", locale, currency, code);
	
		
		 Double result = categoryService.getMaxPrice(locale, currency, code, 
													 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
													 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
													 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()));
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/Category/{locale}/{currency}/Code/{code}/maxPriceFacet")
	public ResponseEntity<PriceFacetResource> getMaxPriceFacet(	@PathVariable String locale,
																@PathVariable String currency, 
																@PathVariable String code,
																@RequestBody Set<IFacet> selectedFacets) {
		LOGGER.debug("call CategoryController.getMaxPriceFacet with parameters : {}, {}, {}", locale, currency, code);
		
		
		 EntityFacet result = priceFacetMapper.toEntityFacet(categoryService.getMaxPrice(locale, 
																						 currency, 
																						 code, 
																						 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
																						 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
																						 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet())));
		return ResponseEntity.ok(priceFacetResourceAssembler.toModel(result));
	}


}
