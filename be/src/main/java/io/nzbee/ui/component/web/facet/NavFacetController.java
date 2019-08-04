package io.nzbee.ui.component.web.facet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NavFacetController {
    
	@Autowired
	private INavFacetService navFacetService;

//  @PostMapping("/Product/{locale}/{currency}/{category}/{price}/tags")
//  public List<Tag> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @PathVariable Double price, @RequestBody final List<Sidebar> selectedFacets) {
//  	return tagService.getProductTags(locale, currency, category, price, selectedFacets);
//  }
  
//	@PostMapping("/Product/{locale}/{currency}/{category}/tags")
//	public NavFacetResult getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String categoryDesc, @RequestBody final List<NavFacet> selectedFacets) {
//	  return navFacetService.findAllTags(locale, currency, categoryDesc, selectedFacets);
//	} 
//	
    @GetMapping("/Category/{lcl}/{curr}")
    public NavFacetResult getCategories(@PathVariable String lcl, @PathVariable String curr) {
    	return navFacetService.findAll(lcl, curr);
    }
//
//    @PostMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}/children")
//    public NavFacetResult getCategoryChildren(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<NavFacet> selectedFacets) {
//    	return navFacetService.findAllCategories(lcl, curr, categoryDesc, selectedFacets);
//    }
//    
//    @PostMapping("/Brand/{lcl}/{curr}/category/{categoryDesc}")
//    public NavFacetResult getBrands(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<NavFacet> selectedFacets) {
//    	return navFacetService.findAllBrands(lcl, curr, categoryDesc, selectedFacets);
//    }
	
    @PostMapping("/NavFacet/{lcl}/{curr}/category/{categoryDesc}")
    public NavFacetResult getAll(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<NavFacet> selectedFacets) {
    	return navFacetService.findAll(lcl, curr, categoryDesc, selectedFacets);
    }
    
}
