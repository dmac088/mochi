package io.nzbee.test.integration.entity.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
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
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageImpl;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDTO;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightService;
import io.nzbee.entity.product.physical.light.PhysicalProductLightServiceImpl;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.test.integration.entity.beans.product.physical.IPhysicalProductEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_ProductCacheIntegrationTest {

	@TestConfiguration
	static class ProductEntityRepositoryIntegrationTest {

	}

	@MockBean
	private JavaMailSender mailSender;
	
	@Autowired
	private JCacheCacheManager cacheManager;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private IPhysicalProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IPhysicalProductLightService physicalProductService;
    
    @Autowired
    private IProductService productService;
    
    @Autowired
    @Qualifier("mochiDataSourceOwner")
    private DataSource database;
    
    private static boolean setUpIsDone = false;
    
    private static ProductEntity product = null;
    
    @Before
   	public void setUp() {
       	if (setUpIsDone) {
               return;
           }
       	try (Connection con = database.getConnection()) {
               ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
               ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
           } catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
       	this.persistNewProduct();
           setUpIsDone = true;
   	}
    
	public void persistNewProduct() {
    	
		product = productEntityBeanFactory.getBean();
		
	    //persist a new transient test product
		productService.save(product);
	}
	
	
	
	@Test
	@Rollback(false)
    public void whenFindAllForListOfProductCodes_thenReturnCorrectResultFromCache() {
		
		Set<String> ss = new HashSet<String>();
		
		ss.add(product.getProductUPC());
		
		 // when
		physicalProductService.findAll(Constants.localeENGB, Constants.currencyHKD, Constants.primaryProductRootCategoryCode, new StringCollectionWrapper(ss));
		
		// then
	    Cache cache = cacheManager.getCache(PhysicalProductLightServiceImpl.CACHE_NAME);
		
		assertNotNull(cache);
    	
	    JCacheCache jCache = (JCacheCache) cache;
	    String key = Constants.localeENGB + ", " + Constants.currencyHKD + ", " + Constants.primaryProductRootCategoryCode + ", " + new StringCollectionWrapper(ss).getCacheKey();
		
	    SimpleValueWrapper ob = (SimpleValueWrapper) jCache.get(key);
    	
	    assertNotNull(ob);
	    assertNotNull(ob.get());
	    assertThat(ob.get().getClass().getSimpleName()).isEqualTo(ArrayList.class.getSimpleName());
		
	}
	
	@Test
	@Rollback(false)
    public void whenFindAllDTOForClassType_thenReturnCorrectResultFromCache() {
		
	}
	
	
	@Test
	@Rollback(false)
    public void whenFindProductDTOByBrowseCriteria_thenReturnCurrectBrowseResultFromCache() {
		
		physicalProductService.findAll(	Constants.localeENGB, 
								Constants.currencyHKD, 
								Constants.primaryProductRootCategoryCode, 
								new StringCollectionWrapper(new HashSet<String>()), 
								new StringCollectionWrapper(new HashSet<String>()), 
								new StringCollectionWrapper(new HashSet<String>()), 
								null, 
								"0", 
								"10", 
								"nameAsc");
		
		// then
	    Cache cache = cacheManager.getCache(PhysicalProductLightServiceImpl.CACHE_NAME);
		
	    assertNotNull(cache);
    	
	    JCacheCache jCache = (JCacheCache) cache;
	    
	    String key = Constants.localeENGB + ", " + 
	    			 Constants.currencyHKD + ", " + 
	    			 Constants.primaryProductRootCategoryCode + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 "" + ", " + 
					 "0" + ", " +
					 "10" + ", " + 
					 "nameAsc";
	    
	    
	    
	    SimpleValueWrapper ob = (SimpleValueWrapper) jCache.get(key);
    	
	    assertNotNull(ob);
	    assertNotNull(ob.get());
	    assertThat(ob.get().getClass().getSimpleName()).isEqualTo(PageImpl.class.getSimpleName());
	    
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
    public void whenFindProductDTOByBrowseCriteriaForVeg_thenReturnCurrectBrowseResultFromCache() {
		
		String cc = "VEG01";
		
		physicalProductService.findAll(	Constants.localeENGB, 
								Constants.currencyHKD, 
								cc, 
								new StringCollectionWrapper(new HashSet<String>()),  
								new StringCollectionWrapper(new HashSet<String>()), 
								new StringCollectionWrapper(new HashSet<String>()), 
								null, 
								"0", 
								"10", 
								"nameAsc");
		
		// then
	    Cache cache = cacheManager.getCache(PhysicalProductLightServiceImpl.CACHE_NAME);
		
	    assertNotNull(cache);
    	
	    JCacheCache jCache = (JCacheCache) cache;
	    
	    String key = Constants.localeENGB + ", " + 
	    			 Constants.currencyHKD + ", " + 
	    			 cc + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 "" + ", " + 
					 "0" + ", " +
					 "10" + ", " + 
					 "nameAsc";
	    
	    SimpleValueWrapper ob = (SimpleValueWrapper) jCache.get(key);
    	
	    assertNotNull(ob);
	    assertNotNull(ob.get());
	    assertThat(ob.get().getClass().getSimpleName()).isEqualTo(PageImpl.class.getSimpleName());
	    assertThat(((PageImpl<PhysicalProductLightDTO>) ob.get()).getTotalElements()).isEqualTo(Long.valueOf(12));
	    
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
    public void whenFindPhysicalProductDTOByBrowseCriteriaForVeg_thenReturnCurrectBrowseResultFromCache() {
		
		String cc = "VEG01";
		
		physicalProductService.findAll(	Constants.localeENGB, 
										Constants.currencyHKD, 
										cc, 
										new StringCollectionWrapper(new HashSet<String>()),  
										new StringCollectionWrapper(new HashSet<String>()), 
										new StringCollectionWrapper(new HashSet<String>()), 
										null, 
										"0", 
										"10", 
										"nameAsc");
		
		// then
	    Cache cache = cacheManager.getCache(PhysicalProductLightServiceImpl.CACHE_NAME);
		
	    assertNotNull(cache);
    	
	    JCacheCache jCache = (JCacheCache) cache;
	    
	    String key = Constants.localeENGB + ", " + 
	    			 Constants.currencyHKD + ", " + 
	    			 cc + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 (new StringCollectionWrapper(new HashSet<String>()).getCacheKey()) + ", " +
	    			 "" + ", " +
					 "0" + ", " +
					 "10" + ", " + 
					 "nameAsc";
	    
	    SimpleValueWrapper ob = (SimpleValueWrapper) jCache.get(key);
	    
	    assertNotNull(ob);
	    assertNotNull(ob.get());
	    assertThat(ob.get().getClass().getSimpleName()).isEqualTo(PageImpl.class.getSimpleName());
	    assertThat(((PageImpl<PhysicalProductLightDTO>) ob.get()).getTotalElements()).isEqualTo(Long.valueOf(12));
	    
	}

}
