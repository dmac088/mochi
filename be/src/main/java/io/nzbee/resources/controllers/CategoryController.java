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
import io.nzbee.resources.category.CategoryFacetResource;
import io.nzbee.resources.category.CategoryFacetResourceAssembler;
import io.nzbee.resources.category.CategoryResource;
import io.nzbee.resources.category.CategoryResourceAssembler;
import io.nzbee.resources.product.PriceFacetResource;
import io.nzbee.resources.product.PriceFacetResourceAssembler;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacet;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.category.product.ProductCategoryView;
import io.nzbee.view.ports.ICategoryViewPortService;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class CategoryController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IFacetMapper<Double> priceFacetMapper;

	@Autowired 
	private IFacetMapper<ProductCategoryView> categoryFacetMapper; 
	
	@Autowired
	private CategoryResourceAssembler categoryResourceAssember;
	
	@Autowired
	private CategoryFacetResourceAssembler categoryFacetResourceAssembler;
	
	@Autowired
	private PriceFacetResourceAssembler priceFacetResourceAssembler;

	@Autowired
	private ICategoryViewPortService categoryService;
	
	public CategoryController() {
		super();
	}

	@GetMapping("/Category/Product/{locale}/{currency}")
	public ResponseEntity<CollectionModel<CategoryResource>> getProductCategories(@PathVariable String locale) {
		LOGGER.debug("Fetching product categories for parameters : {}, {}", locale);
		final List<ProductCategoryView> collection = categoryService.findAllProductCategories(locale);
		return ResponseEntity.ok(categoryResourceAssember.toCollectionModel(collection));
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
 
		
		final List<ProductCategoryView> collection = categoryService.findAll(locale, 
																			 currency, 
																			 code,
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
		LOGGER.debug("call CategoryController.getChildCategoryFacets with parameters : {}, {}, {}", locale, currency, code);
		
		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getValue()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
		
		Set<EntityFacet> collection = categoryService.findAll(locale, 
															  currency, 
															  code,
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
