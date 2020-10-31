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
	public Optional<ProductDTO> findById(String locale, String currency, long id) {
		return productDAO.findById(locale, currency, id);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "{#locale, #currency, #productUPC}")
	public Optional<ProductDTO> findByCode(String locale, String currency, String productUPC) {
		return productDAO.findByCode(locale, currency, productUPC);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key="#productUPC")
	public Optional<ProductEntity> findByCode(String productUPC) {
		return productDAO.findByCode(productUPC);
	}

	@Override
	public Optional<ProductDTO> findByDesc(String locale, String currency, String desc) {
		return productDAO.findByDesc(locale, currency, desc);
	}
	
	@Override
	public List<ProductDTO> findAll(String locale, String currency) {
		return productDAO.findAll(locale, currency);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public List<ProductDTO> findAll(String locale, String currency, Set<String> productCodes) {
		return productDAO.findAll(locale, currency, productCodes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public <T> List<ProductDTO> findAllByType(String locale, String currency, Class<T> cls) {
		return productDAO.findAllByType(locale, currency, cls);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public <T> List<ProductEntity> findAllByType(Class<T> cls) {
		return null;
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public Page<ProductDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
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
	public void save(ProductEntity product) {
		productDAO.save(product);
	}

	
	
	@Override
	public void update(ProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<ProductDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

}