package io.nzbee.ui.component.web.facet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FacetController {
    
	@Autowired
	@Qualifier(value = "searchFacetService")
	private IFacetService navFacetService;

	@PostMapping("/Product/{locale}/{currency}/{categoryDesc}/tags")
	public FacetResult getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryDesc, @RequestBody final FacetContainer selectedFacets) {
		return navFacetService.findAll(locale, currency, categoryDesc, selectedFacets);
	} 
	
    @GetMapping("/Category/{lcl}/{curr}")
    public FacetResult getCategories(@PathVariable String lcl, @PathVariable String curr) {
    	return navFacetService.findAll(lcl, curr);
    } 

    @PostMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}/children")
    public FacetResult getCategoryChildren(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody FacetContainer selectedFacets) {
    	return navFacetService.findAll(lcl, curr, categoryDesc, selectedFacets);
    }
    
    @GetMapping("/Brand/{lcl}/{curr}/category/{categoryCode}")
    public FacetResult getBrands(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryCode) {
    	return navFacetService.findAllBrands(lcl, categoryCode);
    }
	
    @PostMapping("/Product/{lcl}/{curr}/category/{categoryDesc}")
    public FacetResult getAll(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody FacetContainer selectedFacets) {
    	return navFacetService.findAll(lcl, curr, categoryDesc, selectedFacets);
    }
    
}
