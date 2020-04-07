package io.nzbee.domain.product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.ui.component.web.facet.IFacet;

@Service(value = "productDomainService")
public class ProductServiceImpl implements IProductService {
    
    @Autowired 
    private IProductPortService productService;
    

	@Override
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
		return productService.findAll(locale, currency, productCodes);	
	}

	@Override
	public Page<Product> findAll(	String locale, 
									String currency, 
									Pageable pageable, 
									String categoryDesc,
									Set<IFacet> facets, 
									String sortBy) {

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
		return token;
	}

	@Override
	public Set<Product> findAll(String lcl, String currency, Set<String> codes) {
		return null;
	}

}