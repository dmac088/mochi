package io.nzbee.ui.component.web.facet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NavFacetController {
    
	@Autowired
	private INavFacetService sidebarService;

//  @PostMapping("/Product/{locale}/{currency}/{category}/{price}/tags")
//  public List<Tag> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @PathVariable Double price, @RequestBody final List<Sidebar> selectedFacets) {
//  	return tagService.getProductTags(locale, currency, category, price, selectedFacets);
//  }
  
	@PostMapping("/Product/{locale}/{currency}/{category}/tags")
	public List<NavFacet> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @RequestBody final List<NavFacet> selectedFacets) {
	  return sidebarService.findAllTags(locale, currency, category, selectedFacets);
	} 

    @PostMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}/children")
    public List<NavFacet> getCategoryChildren(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<NavFacet> facets) {
    	return sidebarService.findAllCategories(lcl, curr, categoryDesc, facets);
    }
    
    @PostMapping("/Brand/{lcl}/{curr}/category/{categoryDesc}")
    public List<NavFacet> getBrands(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<NavFacet> facets) {
    	return sidebarService.findAllBrands(lcl, curr, categoryDesc, facets);
    }
	
}
