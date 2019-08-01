package io.nzbee.ui.component.web.navigation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nzbee.ui.component.web.facet.NavFacet;
import io.nzbee.ui.component.web.search.Search;

@RestController
@RequestMapping("/api")
public class NavigationController {

	@Autowired
	private INavigationService navigationService;
	
	@PostMapping("/Product/{locale}/{currency}/category/{category}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
	public Search getProducts(	@PathVariable String locale, 
								@PathVariable String currency, 
								@PathVariable String category,
								@PathVariable Double price, 
								@PathVariable int page, 
								@PathVariable int size, 
								@PathVariable String sortBy,
								@RequestBody final List<NavFacet> selectedFacets) {
	   	return navigationService.findAll(locale, currency, category, price, page, size, sortBy, selectedFacets);
	}

	
}
