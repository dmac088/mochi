package io.nzbee.entity.adapters;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.IMapper;
import io.nzbee.entity.product.IProductService;

@Component
public class PostgresProductAdapter implements IProductPortService {

	private IProductService productService;
	
	private IMapper<Product, io.nzbee.entity.product.Product> productMapper;
	
	@Override
	public Optional<Product> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Product tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return productMapper.entityToDo(productService.findByCode(locale, currency, code).get());
	}

	@Override
	public Product findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findAll(String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findAll(String locale, String currency, List<String> productCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findAll(String locale, String currency, Double price, Pageable pageable, String categoryDesc,
			List<Product> collect, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findAll(String locale, String currency, Pageable pageable, String categoryDesc,
			List<Product> collect, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findAll(String locale, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
