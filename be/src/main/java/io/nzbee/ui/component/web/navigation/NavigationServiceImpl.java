package io.nzbee.ui.component.web.navigation;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import io.nzbee.domain.Product;
import io.nzbee.domain.services.product.IProductService;
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
		
		//convert selected facets into token lists
		List<Long> categoryIds = selectedFacets.getCategories().stream().map(t -> Long.parseLong(t.getFacetId())).collect(Collectors.toList());
		List<Long> brandIds = selectedFacets.getBrands().stream().map(t -> Long.parseLong(t.getFacetId())).collect(Collectors.toList());
		List<Long> tagIds = selectedFacets.getTags().stream().map(t ->  Long.parseLong(t.getFacetId())).collect(Collectors.toList());
				
		Page<Product> pp = productService.findAll(locale, currency, categoryDesc, price, page, size, sortBy, categoryIds, brandIds, tagIds);
		
		//add the page of objects to a new Search object and return it 
		Search search = new Search();
		search.setProducts(pp);
		//search.setFacets(selectedFacets);
		return search;
	}
    
}
