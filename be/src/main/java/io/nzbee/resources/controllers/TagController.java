package io.nzbee.resources.controllers;

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
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.resources.tag.TagResource;
import io.nzbee.resources.tag.TagResourceAssembler;
import io.nzbee.search.dto.facet.IFacet;


@RestController
@RequestMapping("/api")
public class TagController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ITagPortService tagService;
    
    @Autowired
    private TagResourceAssembler tagResourceAssembler;
	
	@PostMapping("/Tag/{locale}/{currency}/category/{categoryCode}")
    public ResponseEntity<CollectionModel<TagResource>> getTags(@PathVariable String locale, 
    																@PathVariable String currency, 
    																@PathVariable String categoryCode,
    																@RequestBody Set<IFacet> selectedFacets) {
    	
    	LOGGER.debug("Fetching tags for parameters : {}, {}, {}", locale, currency, categoryCode);
    	
    	Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("maxPrice")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
    	
    	final Set<Tag> collection =
    			tagService.findAll(	 locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getId()).collect(Collectors.toSet()),
    								 selectedFacets.stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getId()).collect(Collectors.toSet()),
    								 maxPrice    							  
    			);
    	
    	return ResponseEntity.ok(tagResourceAssembler.toCollectionModel(collection));
    }
	
	
}
