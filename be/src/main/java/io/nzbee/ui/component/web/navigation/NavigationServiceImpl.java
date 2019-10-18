package io.nzbee.ui.component.web.navigation;

import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.generic.UIService;
import io.nzbee.ui.component.web.search.Search;
import io.nzbee.ui.component.web.search.facet.SearchFacetContainer;

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
	public Search findAll(	 String locale, 
							 String currency, 
							 Double price,
							 int page, 
							 int size, 
							 String categoryDesc,
							 SearchFacetContainer selectedFacets,
							 String sortBy) {
		
		Page<Product> pp = productService.findAll(locale, 
												  currency, 
												  price, 
												  page, 
												  size, 
												  categoryDesc, 
												  selectedFacets.getFacets(),
												  sortBy);
		
		//add the page of objects to a new Search object and return it 
		Search search = new Search();
		Page<Product> products = new PageImpl<Product>(pp.stream().map(p->productService.dtoToDO(p)).collect(Collectors.toList()), 
													   pp.getPageable(), pp.getTotalElements());
		
		search.setProducts(pp);
		search.setFacets(selectedFacets);
		return search;
	}
	
	@Override
	public Search findAll(	 String locale, 
							 String currency, 
							 String categoryDesc,
							 int page, 
							 int size,
							 SearchFacetContainer selectedFacets,
							 String sortBy
							 ) {

		
		Page<Product> pp = productService.findAll(locale, 
												  currency, 
												  page, 
												  size, 
												  categoryDesc, 
												  selectedFacets.getFacets(),
												  sortBy);
		
		//add the page of objects to a new Search object and return it 
		Search search = new Search();
		search.setProducts(pp);
		//search.setFacets(selectedFacets);
		return search;
	}
    
}
