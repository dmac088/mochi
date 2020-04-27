package io.nzbee.domain.product;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.search.dto.facet.IFacet;

public class ProductServiceImpl implements IProductService {
    
    @Autowired 
    private IProductPortService productService;
    
	@Override
	@Transactional(readOnly=true)
	public Optional<Product> findByCode(String locale, String currency, String code) {
	   	return productService.findByCode(locale, currency, code);
	}	

	@Override
	@Transactional(readOnly=true)
	public Optional<Product> findByDesc(String locale, String currency, String desc) {
		return productService.findByDesc(locale, currency, desc);
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Product> findAll(String locale, String currency) {
		return productService.findAll(locale, currency);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Set<Product> findAll(String locale, String currency, Set<String> codes) {
		return productService.findAll(locale, currency, codes);	
	}

	@Override
	@Transactional(readOnly=true)
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
	@Transactional(readOnly=true)
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
	@Transactional(readOnly=true)
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
	public void save(Product object) {
		productService.save(object);
	}

	@Override
	public void delete(Product object) {
		// TODO Auto-generated method stub
	}

	

}