package io.nzbee.ui.component.web.search;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import io.nzbee.domain.Product;
import io.nzbee.domain.services.product.IProductService;
import io.nzbee.ui.component.web.sidebar.Sidebar;

@Service(value = "SearchService")
@Transactional
@CacheConfig(cacheNames="products")
public class SearchServiceImpl implements ISearchService{

	@Autowired
	private IProductService productService;
	
	@Override
	public Search findAll(String lcl, 
			 String currency, 
			 String categoryDesc, 
			 String searchTerm, 
			 int page, 
			 int size, 
			 String sortBy, 
			 List<Sidebar> selectedFacets) {
		
		//convert selected facets into token lists
		List<String> categoryTokens = null; 
		List<String> brandTokens = null;
		List<String> tagTokens = null;
		
		//call the domain layer service to get a Page of Products
		Page<Product> pp = productService.findAll(lcl, currency, categoryDesc, searchTerm, page, size, sortBy, categoryTokens, brandTokens, tagTokens);
		
		//add the page of objects to a new Search object and return it 
		Search search = new Search();
		search.setProducts(pp);
		return search;
	}
	
	//returns a user interface object, rule broken, need to change to return a domain object 
	@Override
	public Search findAll(String locale, 
			 String currency, 
			 String categoryDesc, 
			 Double price, 
			 int page, 
			 int size, 
			 String sortBy, 
			 List<Sidebar> selectedFacets) {
		
		return null;
	}
	
}
