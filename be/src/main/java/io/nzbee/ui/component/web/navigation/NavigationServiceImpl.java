package io.nzbee.ui.component.web.navigation;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Product;
import io.nzbee.domain.Tag;
import io.nzbee.domain.services.product.IProductService;
import io.nzbee.ui.component.web.facet.NavFacet;
import io.nzbee.ui.component.web.generic.UIService;
import io.nzbee.ui.component.web.search.Search;
import io.nzbee.variables.CategoryVars;

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
			 int page, 
			 int size, 
			 String sortBy, 
			 List<NavFacet> selectedFacets) {
		
		//convert selected facets into token lists
		List<Long> categoryIds = this.getFacetIds(selectedFacets, Category.class); 
		List<Long> brandIds = this.getFacetIds(selectedFacets, Brand.class);
		List<Long> tagIds = this.getFacetIds(selectedFacets, Tag.class);
				
		List<NavFacet> ls = selectedFacets.stream().filter(f -> f.getFacetName().equals(CategoryVars.PRICE_FACET_NAME)).collect(Collectors.toList());
	
		Page<Product> pp = ls.isEmpty()
						   ? productService.findAll(locale, currency, categoryDesc, page, size, sortBy, categoryIds, brandIds, tagIds)
						   : productService.findAll(locale, currency, categoryDesc, Double.parseDouble(ls.get(0).getToken()), page, size, sortBy, categoryIds, brandIds, tagIds);
		
		//add the page of objects to a new Search object and return it 
		Search search = new Search();
		search.setProducts(pp);
		search.setFacets(selectedFacets);
		return search;
	}
    
}
