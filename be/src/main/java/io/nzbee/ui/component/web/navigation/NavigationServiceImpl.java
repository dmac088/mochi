package io.nzbee.ui.component.web.navigation;

import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.facet.NavFacetContainer;
import io.nzbee.ui.component.web.generic.UIService;
import io.nzbee.ui.component.web.search.Search;

@Service(value = "NavigationService")
@Transactional
@CacheConfig(cacheNames="products")
public class NavigationServiceImpl extends UIService implements INavigationService {
	
	@Autowired
	INavigationService navigationService;

	@Autowired
	IProductService productService;

	//returns a user interface object, rule broken, need to change to return a domain object 
	@Override
	public Search findAll(String locale, 
			 String currency, 
			 String categoryDesc,
			 Double price,
			 int page, 
			 int size, 
			 String sortBy, 
			 NavFacetContainer selectedFacets) {

		
		Page<Product> pp = productService.findAll(locale, 
												  currency, 
												  categoryDesc, 
												  price, 
												  page, 
												  size, 
												  sortBy, 
												  selectedFacets.getProductCategories().stream().map(c -> c.getPayload()).collect(Collectors.toList()), 
												  selectedFacets.getBrands().stream().map(b -> b.getPayload()).collect(Collectors.toList()),
												  selectedFacets.getTags().stream().map(t -> t.getPayload()).collect(Collectors.toList()));
		
		//add the page of objects to a new Search object and return it 
		Search search = new Search();
		search.setProducts(pp);
		//search.setFacets(selectedFacets);
		return search;
	}
	
	@Override
	public Search findAll(String locale, 
			 String currency, 
			 String categoryDesc,
			 int page, 
			 int size, 
			 String sortBy, 
			 NavFacetContainer selectedFacets) {

		
		Page<Product> pp = productService.findAll(locale, 
												  currency, 
												  categoryDesc, 
												  page, 
												  size, 
												  sortBy, 
												  selectedFacets.getProductCategories().stream().map(c -> c.getPayload()).collect(Collectors.toList()), 
												  selectedFacets.getBrands().stream().map(b -> b.getPayload()).collect(Collectors.toList()),
												  selectedFacets.getTags().stream().map(t -> t.getPayload()).collect(Collectors.toList()));
		
		//add the page of objects to a new Search object and return it 
		Search search = new Search();
		search.setProducts(pp);
		//search.setFacets(selectedFacets);
		return search;
	}
    
}
