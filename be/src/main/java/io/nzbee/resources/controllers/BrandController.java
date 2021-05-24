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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.resources.brand.facet.BrandFacetModel;
import io.nzbee.resources.brand.facet.BrandFacetModelAssembler;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacet;
import io.nzbee.search.facet.IFacetMapper;
import io.nzbee.view.product.brand.facet.BrandFacetView;
import io.nzbee.view.product.brand.facet.IBrandFacetViewService;

@RestController
@RequestMapping("/api")
public class BrandController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IBrandFacetViewService brandService;
    
    @Autowired
	private BrandFacetModelAssembler brandFacetResourceAssembler;
    
    @Autowired
    private IFacetMapper<BrandFacetView> facetMapper;

    public BrandController() {
        super();
    }
    
    @PostMapping("/Brand/{locale}/{currency}/Category/Code/{categoryCode}")
    public ResponseEntity<CollectionModel<BrandFacetModel>> getBrands(	@PathVariable String locale, 
			    																@PathVariable String currency, 
			    																@PathVariable String categoryCode,
			    																@RequestBody  Set<IFacet> selectedFacets) {
    	 
    	LOGGER.debug("call BrandController.getBrands with parameters {}, {}, {}", locale, currency, categoryCode);
    	
    	Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = Double.valueOf(oMaxPrice.get());
    	}
    	
    	final List<EntityFacet> collection =
    			brandService.findAll(locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 maxPrice).stream().map(b -> facetMapper.toEntityFacet(b)).collect(Collectors.toList());

        return ResponseEntity.ok(brandFacetResourceAssembler.toCollectionModel(collection));
    }
    
    
    @PostMapping("/Brand/Facet/{locale}/{currency}/Category/Code/{categoryCode}")
    public ResponseEntity<CollectionModel<BrandFacetModel>> getBrandFacets( 	@PathVariable String locale, 
				    																@PathVariable String currency, 
				    																@PathVariable String categoryCode,
				    																@RequestBody  Set<IFacet> selectedFacets) {
    	 
    	LOGGER.debug("call BrandController.getBrandFacets with parameters: {}, {}, {}", locale, currency, categoryCode);
    	
    	Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice =  Double.valueOf(oMaxPrice.get());
    	}
    	
    	List<EntityFacet> collection =
    			brandService.findAll(locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("tag")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 maxPrice
    			).stream().map(b -> facetMapper.toEntityFacet(b)).collect(Collectors.toList());

        return ResponseEntity.ok(brandFacetResourceAssembler.toCollectionModel(collection));
    }

}
