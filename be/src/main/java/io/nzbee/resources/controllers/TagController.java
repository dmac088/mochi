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
import io.nzbee.resources.tag.browse.facet.TagBrowseFacetResource;
import io.nzbee.resources.tag.browse.facet.TagBrowseFacetResourceAssembler;
import io.nzbee.search.facet.IFacet;
import io.nzbee.view.product.tag.facet.ITagFacetViewService;
import io.nzbee.view.product.tag.facet.TagFacetView;


@RestController
@RequestMapping("/api")
public class TagController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ITagFacetViewService tagService;
    
    
    @Autowired
    private TagBrowseFacetResourceAssembler tagResourceAssembler;
    
	@PostMapping("/Tag/Facet/{locale}/{currency}/Category/Code/{categoryCode}")
    public ResponseEntity<CollectionModel<TagBrowseFacetResource>> getTags(@PathVariable String locale, 
    																@PathVariable String currency, 
    																@PathVariable String categoryCode,
    																@RequestBody Set<IFacet> selectedFacets) {
    	
    	LOGGER.debug("call TagController.getTags with parameters: {}, {}, {}", locale, currency, categoryCode);
    	
    	Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getValue()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = Double.valueOf(oMaxPrice.get());
    	}
    	
    	final List<TagFacetView> collection =
    			tagService.findAll(	 locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 maxPrice    							  
    			);
    	
    	return ResponseEntity.ok(tagResourceAssembler.toCollectionModel(collection));
    }
	

	
}
