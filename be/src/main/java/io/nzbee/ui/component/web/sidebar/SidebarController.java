package io.nzbee.ui.component.web.sidebar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SidebarController {
    
	@Autowired
	private ISidebarService sidebarService;

//  @PostMapping("/Product/{locale}/{currency}/{category}/{price}/tags")
//  public List<Tag> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @PathVariable Double price, @RequestBody final List<Sidebar> selectedFacets) {
//  	return tagService.getProductTags(locale, currency, category, price, selectedFacets);
//  }
  
	@PostMapping("/Product/{locale}/{currency}/{category}/tags")
	public List<Sidebar> getTags(@PathVariable String locale, @PathVariable String currency, @PathVariable String category, @RequestBody final List<Sidebar> selectedFacets) {
	  return sidebarService.findAll(locale, currency, category, selectedFacets);
	} 
	
}
