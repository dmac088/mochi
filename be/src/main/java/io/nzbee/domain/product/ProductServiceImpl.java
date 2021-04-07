package io.nzbee.domain.product;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.ports.IProductPortService;

public class ProductServiceImpl implements IProductService {
    
    @Autowired 
    private IProductPortService productService;
    
	@Override
	public Product findByCode(String locale, String currency, String code) {
	   	return productService.findByCode(locale, currency, code);
	}	

	@Override
	public Product findByDesc(String locale, String currency, String desc) {
		return productService.findByDesc(locale, currency, desc);
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