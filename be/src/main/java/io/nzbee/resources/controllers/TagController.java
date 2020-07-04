package io.nzbee.resources.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.resources.tag.TagResource;
import io.nzbee.resources.tag.TagResourceAssembler;
import io.nzbee.search.dto.facet.FacetContainer;


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
    																@RequestBody final FacetContainer selectedFacets) {
    	
    	LOGGER.debug("Fetching tags for parameters : {}, {}, {}", locale, currency, categoryCode);
    	
    	
    	final List<TagResource> collection =
    			tagService.findAll(	locale, 
    								 currency, 
    								 categoryCode,
    								 selectedFacets.getFacets().stream().filter(f -> f.getFacetingName().equals("category")).map(f -> f.getId()).collect(Collectors.toSet()),
    								 selectedFacets.getFacets().stream().filter(f -> f.getFacetingName().equals("brand")).map(f -> f.getId()).collect(Collectors.toSet())
    			).stream()
        		.map(b -> tagResourceAssembler.toModel(b))
        		.collect(Collectors.toList());
    	
    	final CollectionModel<TagResource> resources = new CollectionModel<TagResource> (collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "tags"));
        return ResponseEntity.ok(resources);
    }
	
	
}
