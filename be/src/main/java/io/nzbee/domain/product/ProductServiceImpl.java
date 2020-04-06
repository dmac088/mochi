package io.nzbee.domain.product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.ui.component.web.facet.IFacet;

@Service(value = "productDomainService")
public class ProductServiceImpl implements IProductService {
	//In service classes, we should only call methods of entity service classes
	//the repositories themselves should not be referenced outside the entity service class
	//this way we ensure proper separation of concerns
    
    @Autowired 
    @Qualifier(value="productService")
    private IProductPortService productService;
    
    @Autowired 
    @Qualifier(value="categoryService")
    private ICategoryPortService categoryService;
    
    @Autowired 
    @Qualifier(value="brandService")
    private IBrandPortService brandService;
    
    @Autowired 
    @Qualifier(value="tagService")
    private ITagPortService tagService;
    

	@Override
	@Transactional
	@Cacheable(value="product")
	public Product findByCode(String locale, String currency, String code) {
	   	return productService.findByCode(locale, currency, code);
	}	

	@Override
	public Product findByDesc(String locale, String currency, String desc) {
		return productService.findByDesc(locale, currency, desc);
	}

	@Override
	public Set<Product> findAll(String locale, String currency) {
		return productService.findAll(locale, currency);
	}
    
	@Override
	public Set<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return productService.findAll(locale, currency, productCodes);	
	}

	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									Pageable pageable, 
									String categoryDesc,
									Set<IFacet> facets, 
									String sortBy) {
		// TODO Auto-generated method stub
		Page<Product> pp =
			productService.findAll(locale, 
									  currency, 
									  pageable, 
									  categoryDesc, 
									  facets.stream().map(f -> (Product)f.getPayload()).collect(Collectors.toList()), 
									  sortBy);
				
		    return new PageImpl<Product>(
			pp.stream().collect(Collectors.toList()),
			pageable,
			pp.getTotalElements());
	}
	
	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									Double price, 
									Pageable pageable, 
									String categoryDesc,
									Set<IFacet> facets, 
									String sortBy) {
		// TODO Auto-generated method stub
    	Page<Product> pp =
		   productService.findAll(	 locale, 
										 currency, 
										 price, 
										 pageable, 
										 categoryDesc, 
										 facets.stream().map(f -> (Product)f.getPayload()).collect(Collectors.toList()), 
										 sortBy);
		
     	return new PageImpl<Product>(pp.stream().collect(Collectors.toList()),
									 pageable,
								 	 pp.getTotalElements());
    	
	}
	
	@Override
	public Page<Product> findAllByBrand(	
									String locale, 
									String currency, 
									Double price, 
									Pageable pageable, 
									String categoryDesc,
									String brandCode,
									String sortBy) {
	
		
     	return null;
    	
	}

	@Override
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return token;
	}

	@Override
	public Set<Product> findAll(String lcl, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

}