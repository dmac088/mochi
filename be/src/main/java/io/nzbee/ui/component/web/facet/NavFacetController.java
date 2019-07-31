package io.nzbee.ui.component.web.facet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Tag;

@RestController
@RequestMapping("/api")
public class NavFacetController {
    
	@Autowired
	private INavFacetService navFacetService;

//  @PostMapping("/Product/{locale}/{currency}/{category}/{price}/tags")
//  public List<Tag> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @PathVariable Double price, @RequestBody final List<Sidebar> selectedFacets) {
//  	return tagService.getProductTags(locale, currency, category, price, selectedFacets);
//  }
  
	@PostMapping("/Product/{locale}/{currency}/{category}/tags")
	public List<NavFacet<Tag>> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @RequestBody final List<NavFacet> selectedFacets) {
	  return navFacetService.findAllTags(locale, currency, category, selectedFacets);
	} 

    @PostMapping("/Category/{lcl}/{curr}/desc/{categoryDesc}/children")
    public List<NavFacet<Category>> getCategoryChildren(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<NavFacet> facets) {
    	return navFacetService.findAllCategories(lcl, curr, categoryDesc, facets);
    }
    
    @PostMapping("/Brand/{lcl}/{curr}/category/{categoryDesc}")
    public List<NavFacet<Brand>> getBrands(@PathVariable String lcl, @PathVariable String curr, @PathVariable String categoryDesc, @RequestBody List<NavFacet> facets) {
    	return navFacetService.findAllBrands(lcl, curr, categoryDesc, facets);
    }
	
}
