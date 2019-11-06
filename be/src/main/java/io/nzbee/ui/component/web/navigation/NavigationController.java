package io.nzbee.ui.component.web.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.facet.FacetContainer;

@RestController
@RequestMapping("/api")
public class NavigationController {

	@Autowired 
	private INavigationService navigationService;
	
	@PostMapping("/Product/{locale}/{currency}/category/{category}/maxPrice/{price}/page/{page}/size/{size}/sortBy/{sortBy}")
	public Page<Product> getProducts(	@PathVariable String locale, 
										@PathVariable String currency, 
										@PathVariable String category,
										@PathVariable Double price, 
										@PathVariable int page, 
										@PathVariable int size, 
										@PathVariable String sortBy,
										@RequestBody final FacetContainer selectedFacets) {
	   	return navigationService.findAll(locale, currency, price, page, size, category, selectedFacets.getFacets(), sortBy);
	}

	@PostMapping("/Product/{locale}/{currency}/category/{category}/page/{page}/size/{size}/sortBy/{sortBy}")
	public Page<Product>  getProducts(	@PathVariable String 	locale, 
										@PathVariable String 	currency, 
										@PathVariable String 	category,
										@PathVariable int 		page, 
										@PathVariable int 		size, 
										@PathVariable String 	sortBy,
										@RequestBody final FacetContainer selectedFacets) {
	   	return navigationService.findAll(locale, currency, category, page, size, selectedFacets.getFacets(), sortBy);
	}
	
}
