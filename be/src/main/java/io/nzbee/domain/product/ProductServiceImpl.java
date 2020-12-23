package io.nzbee.domain.product;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.ports.IProductPortService;

public class ProductServiceImpl implements IProductService {
    
    @Autowired 
    private IProductPortService productService;
    
	@Override
	@Transactional(readOnly=true)
	public Product findByCode(String locale, String currency, String code) {
	   	return productService.findByCode(locale, currency, code);
	}	

	@Override
	@Transactional(readOnly=true)
	public Product findByDesc(String locale, String currency, String desc) {
		return productService.findByDesc(locale, currency, desc);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Product> findAll(String locale, String currency) {
		return productService.findAll(locale, currency);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Product> findAll(String locale, String currency, Set<String> codes) {
		return productService.findAll(locale, currency, codes);	
	}


	@Override
	@Transactional(readOnly=true)
	public Page<Product> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort) {
		Page<Product> pp =
				 productService.findAll(	 	 locale, 
												 currency,
												 categoryCode,
												 categoryCodes, 
												 brandCodes,
												 tagCodes,
												 maxPrice,
												 page,
												 size,
												 sort
												 );
				
		     	return new PageImpl<Product>(pp.stream().collect(Collectors.toList()),
											 PageRequest.of(Integer.parseInt(page), Integer.parseInt(size)),
										 	 pp.getTotalElements());
	}

	@Override
	public void save(Product object) {
		productService.save(object);
	}

	@Override
	public void delete(Product object) {
		productService.delete(object);
	}

	@Override
	public void update(Product object) {
		productService.update(object);
	}

	@Override
	public Product findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	

}