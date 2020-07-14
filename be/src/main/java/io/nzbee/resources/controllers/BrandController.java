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
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.resources.brand.BrandResource;
import io.nzbee.resources.brand.BrandResourceAssembler;
import io.nzbee.search.dto.facet.FacetContainer;

@RestController
@RequestMapping("/api")
public class BrandController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IBrandService brandService;
    
    @Autowired
    private BrandResourceAssembler brandResourceAssembler;

    public BrandController() {
        super();
    }
    
    @PostMapping("/Brand/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<CollectionModel<BrandResource>> getBrands(@PathVariable String locale, 
    																@PathVariable String currency, 
    																@PathVariable String categoryCode,
    																@RequestBody final FacetContainer selectedFacets) {
    	 
    	LOGGER.debug("Fetching brands for parameters : {}, {}, {}", locale, currency, categoryCode);
    	
    	Optional<String> oMaxPrice = selectedFacets.getFacets().stream().filter(p -> p.getFacetingName().equals("maxPrice")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
    	
    	final Set<Brand> collection =
    			brandService.findAll(locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.getFacets().stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getId()).collect(Collectors.toSet()),
    								 selectedFacets.getFacets().stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getId()).collect(Collectors.toSet()),
    								 selectedFacets.getFacets().stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getId()).collect(Collectors.toSet()),
    								 maxPrice
    			);

        return ResponseEntity.ok(brandResourceAssembler.toCollectionModel(collection));
    }
    
    @GetMapping("/Brand/{locale}/{currency}")
    public ResponseEntity<CollectionModel<BrandResource>> getBrands(@PathVariable String locale, 
    																@PathVariable String currency) {
    	LOGGER.debug("Fetching brands for parameters : {}, {}", locale, currency);
    	final Set<Brand> collection = 
    			 brandService.findAll(locale, currency);
    	
    	return ResponseEntity.ok(brandResourceAssembler.toCollectionModel(collection));
    }

    @GetMapping("/Brand/{locale}/{currency}/code/{brandCode}")
    public ResponseEntity<BrandResource> get(@PathVariable String locale, @PathVariable String currency, @PathVariable String brandCode) {
    	LOGGER.debug("Fetching brand for parameters : {}, {}, {}", locale, currency, brandCode);
    	Brand b = brandService.findByCode(locale, currency, brandCode);
    	return ResponseEntity.ok(brandResourceAssembler.toModel(b));
    }
}
