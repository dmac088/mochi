package io.nzbee.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.services.tag.ITagService;

public class TagController {

    @Autowired
    private ITagService tagService;

//		I don't think we need this controller here
    
//    @PostMapping("/Product/{locale}/{currency}/{category}/{price}/tags")
//    public List<Tag> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @PathVariable Double price, @RequestBody final List<Sidebar> selectedFacets) {
//    	return tagService.getProductTags(locale, currency, category, price, selectedFacets);
//    }
//    
//    @PostMapping("/Product/{locale}/{currency}/{category}/tags")
//    public List<Sidebar> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @RequestBody final List<Sidebar> selectedFacets) {
//    	return tagService.getProductTags(locale, currency, category, selectedFacets);
//    }
	
}
