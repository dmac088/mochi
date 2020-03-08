package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.CacheMode;
import org.hibernate.search.batchindexing.impl.SimpleIndexingProgressMonitor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
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
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.type.IProductTypeRepository;
import io.nzbee.test.integration.beans.ProductEntityBeanFactory;
import io.nzbee.ui.component.web.facet.FacetContainer;
import io.nzbee.ui.component.web.search.ISearchService;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
@SqlGroup({
	@Sql(scripts = "/database/mochi_schema.sql",
			config = @SqlConfig(dataSource = "mochiDataSourceOwner", 
			transactionManager = "mochiTransactionManagerOwner",
			transactionMode = TransactionMode.ISOLATED)), 
	@Sql(scripts = "/database/mochi_data.sql",
			config = @SqlConfig(dataSource = "mochiDataSource", 
			transactionManager = "mochiTransactionManager",
			transactionMode = TransactionMode.ISOLATED))
})
public class IT_ProductEntitySearchIntegrationTest {

	@TestConfiguration
    static class ProductEntityRepositoryIntegrationTest {
        
        @Bean(value = "productEntityService")
        public io.nzbee.entity.product.IProductService productService() {
            return new io.nzbee.entity.product.ProductServiceImpl();
        }
        
        @Bean(value = "productEntityBeanFactory")
        public ProductEntityBeanFactory productFactoryBean() {
            return new ProductEntityBeanFactory();
        }
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private ProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IBrandService brandService;
    
    @Autowired
    private IProductTypeRepository productTypeRepository;
    
    @Autowired
    private IProductStatusRepository productStatusRepository;
    
    @Autowired
    private ICategoryService categoryService;
    
    @PersistenceContext(unitName = "mochiEntityManagerFactory")
	private EntityManager em;

    @Autowired 
    private ISearchService searchService;
    
    private Product product = null;
    
	public Product persistNewProduct() {
    	
		product = productEntityBeanFactory.getProductEntityBean();
	    
		//we need a brand
		product.setBrand(brandService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
												 GeneralVars.CURRENCY_HKD, 
												 "PLA01").get());
		
		
		//we need a type
		product.setProductType(productTypeRepository.findByProductTypeCode("NML01").get());
		
		//we need a status
		product.setProductStatus(productStatusRepository.findByProductStatusCode("ACT01").get());
		
		//we need a category
		CategoryProduct cp = (CategoryProduct) categoryService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
																		GeneralVars.CURRENCY_HKD,
																		"FRT01").get();
		
		
		//add the product to the category
		product.addProductCategory(cp);
		
	    entityManager.persist(product);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return product;
	}
	
	@Before
	public void buildSearchIndex() {
		FullTextEntityManager fullTextEntityManager 
		  = Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager
			.createIndexer( ProductAttribute.class )
			.batchSizeToLoadObjects( 25 )
			.cacheMode( CacheMode.NORMAL )
			.threadsToLoadObjects( 12 )
			.idFetchSize( 150 )
			.transactionTimeout( 1800 )
			.progressMonitor( new SimpleIndexingProgressMonitor() ) //a MassIndexerProgressMonitor implementation
			.startAndWait();	
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void whenSearchFruit_thenReturnAllFruitProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Fruit", 
							  0, 
							  10, 
							  "", 
							  new FacetContainer());
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(2);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(12));
	}

	@Test
	public void whenSearchVegetables_thenReturnAllVegetableProducts() {
		
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Vegetables", 
							  0, 
							  10, 
							  "", 
							  new FacetContainer());
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(2);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(12));
	}
	
	@Test
	public void whenSearchApple_thenReturnAppleProduct() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Apple", 
							  0, 
							  10, 
							  "", 
							  new FacetContainer());
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	} 
	
	 @After
	 public void closeConnection() {
	  	entityManager.close();
	 }
}
