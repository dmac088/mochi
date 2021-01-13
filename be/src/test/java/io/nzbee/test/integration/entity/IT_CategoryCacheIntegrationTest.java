package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import javax.persistence.EntityManager;

import org.ehcache.CacheManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.category.CategoryServiceImpl;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.test.integration.entity.beans.category.ICategoryEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_CategoryCacheIntegrationTest {

	@TestConfiguration
	static class CategoryEntityRepositoryIntegrationTest {

	}

	@MockBean
	private JavaMailSender mailSender;
	
	@Autowired
	private JCacheCacheManager cacheManager;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ICategoryEntityBeanFactory categoryEntityBeanFactory;
	
    @Before
    public void setUp() { 
    	this.persistNewCategory();
    }
    
    private CategoryEntity category = null;
    
	public void persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getBean();
		
	    //persist a new transient test category
	    categoryService.save(category);
	}
	
	@Test
    public void whenFindEntityById_thenReturnCategoryEntityFromCache() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findById(category.getCategoryId());
     
        // then
    	Cache cache = cacheManager.getCache(CategoryServiceImpl.CACHE_NAME);
    	
    	assertNotNull(cache);
    	JCacheCache jCache = (JCacheCache) cache;
    	SimpleValueWrapper ob = (SimpleValueWrapper) jCache.get(found.get().getCategoryId().toString());
    	
    	assertNotNull(ob.get());
    	assertThat(ob.get().getClass().getSimpleName()).isEqualTo(CategoryProductEntity.class.getSimpleName());
    }
	
	@Test
    public void whenFindEntityByCode_thenReturnCategoryEntityFromCache() {
    	
        // when
    	Optional<CategoryEntity> found = categoryService.findByCode(category.getCategoryCode());
     
        // then
    	Cache cache = cacheManager.getCache(CategoryServiceImpl.CACHE_NAME);
    	
    	assertNotNull(cache);
    	
    	JCacheCache jCache = (JCacheCache) cache;
    	SimpleValueWrapper ob = (SimpleValueWrapper) jCache.get(found.get().getCategoryCode());
    	
    	assertNotNull(ob.get());
    	assertThat(ob.get().getClass().getSimpleName()).isEqualTo(CategoryProductEntity.class.getSimpleName());
    }
	
	@Test
    public void whenFindDTOByIdAndLocale_thenReturnCategoryDTOFromCache() {
    	
        // when
    	categoryService.findById(Constants.localeENGB, category.getCategoryId());
     
        // then
    	Cache cache = cacheManager.getCache(CategoryServiceImpl.CACHE_NAME);
    	
    	assertNotNull(cache);
    	
    	JCacheCache jCache = (JCacheCache) cache;
    	String key = Constants.localeENGB + ", " + category.getCategoryId().toString();
    	SimpleValueWrapper ob = (SimpleValueWrapper) jCache.get(key);

    	assertNotNull(ob.get());
    	assertThat(ob.get().getClass().getSimpleName()).isEqualTo(CategoryProductEntity.class.getSimpleName());
    }
	
	@Test
    public void whenFindDTOByCodeAndLocale_thenReturnCategoryDTOFromCache() {
    	
//        // when
//    	categoryService.findByCode(Constants.localeENGB, category.getCategoryCode());
//     
//        // then
//    	Cache cache = cacheManager.getCache(CategoryServiceImpl.CACHE_NAME);
//    	
//    	assertNotNull(cache);
//    	
//    	@SuppressWarnings("rawtypes")
//		ConcurrentHashMap nativeCache = (ConcurrentHashMap) cache.getNativeCache();
//    	String key = Constants.localeENGB + ", " + category.getCategoryCode().toString();
//    	Object ob = nativeCache.get(key);
//    
//    	assertNotNull(ob);
//    	assertTrue(nativeCache.containsKey(key)); 	
//    	assertThat(ob.getClass().getSimpleName()).isEqualTo(CategoryProductDTO.class.getSimpleName());
    }
	
	
	@Test
    public void whenSavingEntity_thenKeyIsEvictedFromCache() {
    	
//        // when
//    	categoryService.findByCode(Constants.localeENGB, category.getCategoryCode());
//     
//        // then
//    	Cache cache = cacheManager.getCache(CategoryServiceImpl.CACHE_NAME);
//    	
//    	assertNotNull(cache);
//    	
//    	@SuppressWarnings("rawtypes")
//		ConcurrentHashMap nativeCache = (ConcurrentHashMap) cache.getNativeCache();
//    	String key = Constants.localeENGB + ", " + category.getCategoryCode().toString();
//    	Object ob = nativeCache.get(key);
//    
//    	assertNotNull(ob);
//    	assertTrue(nativeCache.containsKey(key)); 	
//    	assertThat(ob.getClass().getSimpleName()).isEqualTo(CategoryProductDTO.class.getSimpleName());
    }
    
	@After
	public void closeConnection() {
		entityManager.close();
	}

}
