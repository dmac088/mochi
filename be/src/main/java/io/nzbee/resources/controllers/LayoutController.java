package io.nzbee.resources.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.nzbee.Globals;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.domain.ports.ILayoutPortService;
import io.nzbee.resources.category.CategoryResource;
import io.nzbee.resources.category.CategoryResourceAssembler;
import io.nzbee.resources.discovery.DiscoveryResource;
import io.nzbee.resources.layout.LayoutResource;

@RestController
@RequestMapping("/api")
public class LayoutController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private Globals globalVars;
	
    @Autowired
    private ILayoutPortService layoutService;
    
    @Autowired
    private CategoryResourceAssembler categoryResourceAssember;
	
	@GetMapping("/Layout/{code}")
    public ResponseEntity<Resources<LayoutResource>> getCategories(@PathVariable String code) {
    	LOGGER.debug("Fetching layouts for parameters : {}, {}", locale, currency);
    	final List<CategoryResource> collection = categoryService.findAll(locale, currency).stream().map(c -> categoryResourceAssember.toResource(c)).collect(Collectors.toList());
        final Resources <CategoryResource> resources = new Resources <> (collection);
        final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uriString, "self"));
        return ResponseEntity.ok(resources);
    }
	
}
