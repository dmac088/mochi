package io.nzbee.entity.product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.ICategoryService;


@Service(value = "productEntityService")
public class ProductServiceImpl implements IProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	public static final String CACHE_NAME = "productCache";      
	
	@Autowired
	private IProductDao productDAO;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	@Qualifier("categoryEntityService")
	private ICategoryService categoryService;
	
	@Override
	public Optional<ProductDTO> findByCode(String locale, String code) {
		LOGGER.debug("call ProductServiceImpl.findByCode parameters : {}, {}", locale, code);
		return productDAO.findByCode(locale, code);
	}
	
	@Override
	public List<ProductDTO> findAll(String locale) {
		return productDAO.findAll(locale);
	}

	@Override
	public List<ProductDTO> findAll(String locale, StringCollectionWrapper codes) {
		return productDAO.findAll(locale, codes);
	}

	@Override
	public Optional<ProductDTO> findById(String locale, Long id) {
		LOGGER.debug("call ProductServiceImpl.findById parameters : {}, {}", locale, id);
		return productDAO.findById(locale, id);
	}

	@Override
	public Optional<ProductDTO> findByDesc(String locale, String desc) {
		return productDAO.findByDesc(locale, desc);
	}

	@Override
	public List<ProductEntity> findAll() {
		return productRepository.findAll();
	}

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
		return productDAO.findByCode(locale, currency, productUPC);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key = "#locale + \", \" + #currency + \", \" + #productDesc")
	public Optional<ProductDTO> findByDesc(String locale, String currency, String productDesc) {
		return productDAO.findByDesc(locale, currency, productDesc);
	}
	
	@Override
	public List<ProductDTO> findAll(String locale, String currency, String rootCategory) {
		return productDAO.findAll(locale, currency, rootCategory);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #productCodes.getCacheKey()")
	public List<ProductDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper productCodes) {
		return productDAO.findAll(locale, currency, rootCategory, productCodes);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public <T> List<ProductDTO> findAllByType(String locale, String currency, String rootCategory, Class<T> cls) {
		return productDAO.findAllByType(locale, currency, rootCategory, cls);
	}
	
	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other")
	public <T> List<ProductEntity> findAllByType(Class<T> cls) {
		return null;
	}

	@Override
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString()) + \", \" + #page.toString() + \", \" + #cls.getSimpleName() + \", \" + #size.toString() + \", \" + #sort.toString()")
	public <T> Page<ProductDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice, Class<T> cls, String page, String size, String sort) {
		return productDAO.findAll(
								  locale,
						 		  currency,
						 		  rootCategory,
						 		  categoryCodes,
						 		  brandCodes, 
						 		  tagCodes,
						 		  maxPrice,
						 		  cls,
						 		  page,
						 		  size,
						 		  sort
				 		  	);
	
	}
	
	@Override									  
	@Cacheable(cacheNames = CACHE_NAME + "Other", key="#locale + \", \" + #currency + \", \" + #rootCategory + \", \" + #categoryCodes.getCacheKey() + \", \" + #brandCodes.getCacheKey() + \", \" + #tagCodes.getCacheKey() + \", \" + ((#maxPrice == null) ? '' : #maxPrice.toString()) + \", \" + #page.toString() + \", \" + #size.toString() + \", \" + #sort.toString()")
	public Page<ProductDTO> findAll(String locale, String currency, String rootCategory,
			StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes,
			Double maxPrice, String page, String size, String sort) {
		return productDAO.findAll(
				  locale,
		 		  currency,
		 		  rootCategory,
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductEntity t) {
		productRepository.delete(t);
	}

	@Override
	public List<ProductEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	

}