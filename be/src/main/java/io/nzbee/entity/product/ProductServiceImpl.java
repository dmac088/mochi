package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.ICategoryService;


@Service(value = "productEntityService")
public class ProductServiceImpl implements IProductService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	public static final String CACHE_NAME = "productCache";      
	
	@Autowired
	private IProductDao productDAO;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	@Qualifier("categoryEntityService")
	private ICategoryService categoryService;
	
	@Override
	public Optional<ProductEntity> findById(Long id) {
		LOGGER.debug("call ProductServiceImpl.findById parameters : {}", id);
		return productRepository.findById(id);
	}
	
	@Override
	public Optional<ProductEntity> findByCode(String productUPC) {
		LOGGER.debug("call ProductServiceImpl.findByCode parameters : {}", productUPC);
		return productDAO.findByCode(productUPC);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #currency + \", \" + #productId.toString()")
	public Optional<ProductDTO> findById(String locale, String currency, Long productId) {
		LOGGER.debug("call ProductServiceImpl.findById parameters : {}, {}, {}", locale, currency, productId);
		return productDAO.findById(locale, currency, productId);
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME, key = "#locale + \", \" + #currency + \", \" + #productUPC")
	public Optional<ProductDTO> findByCode(String locale, String currency, String productUPC) {
		LOGGER.debug("call ProductServiceImpl.findByCode parameters : {}, {}, {}", locale, currency, productUPC);
		return productDAO.findByCode(locale, currency, productUPC);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key = "#locale + \", \" + #currency + \", \" + #productDesc")
	public Optional<ProductDTO> findByDesc(String locale, String currency, String productDesc) {
		LOGGER.debug("call ProductServiceImpl.findByDesc parameters : {}, {}, {}", locale, currency, productDesc);
		return productDAO.findByDesc(locale, currency, productDesc);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #productCodes.getCacheKey()")
	public List<ProductDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper productCodes) {
		LOGGER.debug("call ProductServiceImpl.findAll parameters : {}, {}, {}, {}", locale, currency, rootCategory, productCodes.getCacheKey());
		return productDAO.findAll(locale, currency, rootCategory, productCodes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #cls.getSimpleName()")
	public <T> List<ProductDTO> findAllByType(String locale, String currency, String rootCategory, Class<T> cls) {
		LOGGER.debug("call ProductServiceImpl.findAllByType parameters : {}, {}, {}, {}", locale, currency, rootCategory, cls.getSimpleName());
		return productDAO.findAllByType(locale, currency, rootCategory, cls);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = CACHE_NAME + "Other", allEntries = true),
			@CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
	})
	public void save(ProductEntity product) {
		productRepository.save(product);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(cacheNames = CACHE_NAME + "Other", 	allEntries = true),
			@CacheEvict(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #product.productUPC"),
			@CacheEvict(cacheNames = CACHE_NAME, key="#locale + \", \" + #currency + \", \" + #product.productId.toString()")
	})
	public void save(String locale, String currency, ProductEntity product) {
		productRepository.save(product);
	}
	
	@Override
	public void update(ProductEntity t) {
		productRepository.save(t);
	}

	@Override
	public void delete(ProductEntity t) {
		productRepository.delete(t);
	}

}