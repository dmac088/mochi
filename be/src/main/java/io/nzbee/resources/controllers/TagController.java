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
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.resources.tag.TagFacetResource;
import io.nzbee.resources.tag.TagFacetResourceAssembler;
import io.nzbee.resources.tag.TagResource;
import io.nzbee.resources.tag.TagResourceAssembler;
import io.nzbee.search.facet.EntityFacet;
import io.nzbee.search.facet.IFacet;
import io.nzbee.search.facet.IFacetMapper;


@RestController
@RequestMapping("/api")
public class TagController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ITagService tagService;
    
	@Autowired
	private IFacetMapper<Tag> facetMapper;
    
    @Autowired
    private TagResourceAssembler tagResourceAssembler;
    
	@Autowired
	private TagFacetResourceAssembler tagFacetResourceAssembler;
	
	@PostMapping("/Tag/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<CollectionModel<TagResource>> getTags(@PathVariable String locale, 
    																@PathVariable String currency, 
    																@PathVariable String categoryCode,
    																@RequestBody Set<IFacet> selectedFacets) {
    	
    	LOGGER.debug("call TagController.getTags with parameters: {}, {}, {}", locale, currency, categoryCode);
    	
    	Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getValue()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
    	
    	final List<Tag> collection =
    			tagService.findAll(	 locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 maxPrice    							  
    			);
    	
    	return ResponseEntity.ok(tagResourceAssembler.toCollectionModel(collection));
    }
	
	@PostMapping("/TagFacet/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<CollectionModel<TagFacetResource>> getTagFacets(@PathVariable String locale, 
    																 @PathVariable String currency, 
    																 @PathVariable String categoryCode,
    																 @RequestBody Set<IFacet> selectedFacets) {
    	
    	LOGGER.debug("call TagController.getTagFacets with parameters: {}, {}, {}", locale, currency, categoryCode);
    	 
    	Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("price")).map(p -> p.getValue()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
    	
    	final Set<EntityFacet> collection =
    			tagService.findAll(	 locale, 
    								 currency, 
    	 							 categoryCode,
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getValue()).collect(Collectors.toSet()),
    								 maxPrice    							  
    			).stream().map(c -> facetMapper.toEntityFacet(c)).collect(Collectors.toSet());;
    	
    	return ResponseEntity.ok(tagFacetResourceAssembler.toCollectionModel(collection));
    }
	
	
}
