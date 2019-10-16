package io.nzbee.ui.component.web.facet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EntityFacetController {
    
	@Autowired
	private IEntityFacetService navFacetService;

	@PostMapping("/Product/{locale}/{currency}/{categoryDesc}/tags")
	public EntityFacetResult getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryDesc, @RequestBody final EntityFacetContainer selectedFacets) {
		return navFacetService.findAll(locale, currency, categoryDesc, selectedFacets);
	} 
	
    @GetMapping("/Category/{lcl}/{curr}")
    public EntityFacetResult getCategories(@PathVariable String lcl, @PathVariable String curr) {
    	return navFacetService.findAll(lcl, curr);
    }

    @PostMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}/children")
    public EntityFacetResult getCategoryChildren(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody EntityFacetContainer selectedFacets) {
    	return navFacetService.findAll(lcl, curr, categoryDesc, selectedFacets);
    }
    
    @GetMapping("/Brand/{lcl}/{curr}/category/{categoryCode}")
    public EntityFacetResult getBrands(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryCode) {
    	return navFacetService.findAllBrands(lcl, categoryCode);
    }
	
    @PostMapping("/Product/{lcl}/{curr}/category/{categoryDesc}")
    public EntityFacetResult getAll(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody EntityFacetContainer selectedFacets) {
    	return navFacetService.findAll(lcl, curr, categoryDesc, selectedFacets);
    }
    
}
