package io.nzbee.ui.component.web.navigation;

import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.ui.component.web.facet.FacetContainer;
import io.nzbee.ui.component.web.generic.UIService;

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
	public Page<Product> findAll(	 String locale, 
							 String currency, 
							 Double price,
							 int page, 
							 int size, 
							 String categoryDesc,
							 FacetContainer selectedFacets,
							 String sortBy) {
		
		Page<Product> pp = productService.findAll(locale, 
												  currency, 
												  price, 
												  PageRequest.of(page, size), 
												  categoryDesc, 
												  selectedFacets.getFacets(),
												  sortBy);
		
		
		return new PageImpl<Product>(pp.stream().map(p->productService.dtoToDO(p)).collect(Collectors.toList()), 
													   pp.getPageable(), pp.getTotalElements());

	}
	
	@Override
	public Page<Product> findAll(	 String locale, 
							 String currency, 
							 String categoryDesc,
							 int page, 
							 int size,
							 FacetContainer selectedFacets,
							 String sortBy
						 ) {

		
		return productService.findAll(locale, 
												  currency, 
												  PageRequest.of(page, size),
												  categoryDesc, 
												  selectedFacets.getFacets(),
												  sortBy);
		
		
	}
    
}
