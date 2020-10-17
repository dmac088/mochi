package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.ICategoryService;


@Service(value = "productEntityService")
public class ProductServiceImpl implements IProductService {
	
	private static final String CACHE_NAME = "productCache";      
	
	@Autowired
	private IProductDao productDAO;
	
	@Autowired
	@Qualifier("categoryEntityService")
	private ICategoryService categoryService;
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #currency, #id}")
	public Optional<Product> findById(String locale, String currency, long id) {
		return productDAO.findById(locale, currency, id);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #currency, #code}")
	public Optional<Product> findByCode(String locale, String currency, String code) {
		return productDAO.findByCode(locale, currency, code);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#productUPC")
	public Optional<Product> findByCode(String productUPC) {
		return productDAO.findByCode(productUPC);
	}

	@Override
	public Optional<Product> findByDesc(String locale, String currency, String desc) {
		return productDAO.findByDesc(locale, currency, desc);
	}
	
	@Override
	public List<Product> findAll(String locale, String currency) {
		return productDAO.findAll(locale, currency);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<Product> findAll(String locale, String currency, Set<String> productCodes) {
		return productDAO.findAll(locale, currency, productCodes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public <T> List<Product> findAllByType(String locale, String currency, Class<T> cls) {
		return productDAO.findAllByType(locale, currency, cls);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public <T> List<Product> findAllByType(Class<T> cls) {
		return null;
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Page<Product> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Set<String> tagCodes, Double maxPrice, String page, String size, String sort) {
		return productDAO.findAll(
								  locale,
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
	
	}


	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = CACHE_NAME + "Other", 	allEntries = true),
			@CacheEvict(cacheNames = CACHE_NAME, key="#product.productUPC"),
			@CacheEvict(cacheNames = CACHE_NAME, key="{#product.locale, #product.currency, #product.productId}"),
			@CacheEvict(cacheNames = CACHE_NAME, key="{#product.locale, #product.currency, #product.productUPC}")
	})
	public void save(Product product) {
		productDAO.save(product);
	}

	
	
	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Product> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

}