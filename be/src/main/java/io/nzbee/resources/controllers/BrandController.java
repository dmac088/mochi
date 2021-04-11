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
import io.nzbee.Constants;
import io.nzbee.resources.brand.BrandFacetResource;
import io.nzbee.resources.brand.BrandFacetResourceAssembler;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.product.brand.facet.BrandFacetView;
import io.nzbee.view.product.brand.facet.IBrandFacetViewService;

@RestController
@RequestMapping("/api")
public class BrandController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IBrandFacetViewService brandService;
    
    @Autowired
	private BrandFacetResourceAssembler brandFacetResourceAssembler;

    public BrandController() {
        super();
    }
    
    @PostMapping("/Brand/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<CollectionModel<BrandFacetResource>> getBrands(@PathVariable String locale, 
    																@PathVariable String currency, 
    																@PathVariable String categoryCode,
    																@RequestBody  Set<IFacet> selectedFacets) {
    	 
    	LOGGER.debug("call BrandController.getBrands with parameters {}, {}, {}", locale, currency, categoryCode);
    	
    	Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
    	
    	final List<BrandFacetView> collection =
    			brandService.findAll(locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 maxPrice);

        return ResponseEntity.ok(brandFacetResourceAssembler.toCollectionModel(collection));
    }
    
    
    @PostMapping("/BrandFacet/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<CollectionModel<BrandFacetResource>> getBrandFacets(@PathVariable String locale, 
	    																 @PathVariable String currency, 
	    																 @PathVariable String categoryCode,
	    																 @RequestBody  Set<IFacet> selectedFacets) {
    	 
    	LOGGER.debug("call BrandController.getBrandFacets with parameters: {}, {}, {}", locale, currency, categoryCode);
    	
    	Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
    	
    	List<BrandFacetView> collection =
    			brandService.findAll(locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 maxPrice
    			);

        return ResponseEntity.ok(brandFacetResourceAssembler.toCollectionModel(collection));
    }

    @GetMapping("/Brand/{locale}/{currency}/code/{brandCode}")
	public ResponseEntity<BrandFacetResource> get(String locale, String brandCode) {
    	LOGGER.debug("call BrandController.get with parameters: {}, {}, {}", locale, brandCode);
    	BrandFacetView b = brandService.findByCode(locale, Constants.primaryProductRootCategoryCode, brandCode);
    	return ResponseEntity.ok(brandFacetResourceAssembler.toModel(b));
	}
}
