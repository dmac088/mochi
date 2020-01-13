package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.status.IProductStatusRepository;
import io.nzbee.entity.product.type.IProductTypeRepository;
import io.nzbee.test.integration.beans.ProductEntityBeanFactory;
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
public class IT_ProductEntityRepositoryIntegrationTest {

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
    private IProductService productService;
    
    @Autowired
    private IBrandService brandService;
    
    @Autowired
    private IProductTypeRepository productTypeRepository;
    
    @Autowired
    private IProductStatusRepository productStatusRepository;
    
    @Autowired
    private ICategoryService categoryService;
    
    private Product product = null;
    
	public Product persistNewProduct() {
    	
		product = productEntityBeanFactory.getProductEntityBean();
	    
		//we need a brand
		product.setBrand(brandService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
												 GeneralVars.CURRENCY_HKD, 
												 "PLA01").get());
		
		
		//we need a type
		product.setProductType(productTypeRepository.findByCode("NML01").get());
		
		//we need a status
		product.setProductStatus(productStatusRepository.findByCode("NML01").get());
		
		//we need a category
		product.addProductCategory((CategoryProduct) categoryService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
								   GeneralVars.CURRENCY_HKD,
								   "PRM01").get());
		
//		ProductAttribute productAttribute = new ProductAttribute();
//		productAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
//		productAttribute.setProductDesc("test product description");
//		product.getAttributes().add(productAttribute);
//		product.addProductCategory(categoryProduct);
		
	    entityManager.persist(product);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return product;
	}
	
	@Before
	public void persistANewProduct() {
		this.persistNewProduct();
	}
	
	@Test
	public void whenFindById_thenReturnProductCategory() {
		 // when
    	Product found = productService.findById(GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
												  product.getProductId()).get();
     
        // then
    	assertFound(found);
	}
	
	
	  
    private void assertFound(final Product found) {
    	
    	assertThat(found.getUPC())
        .isEqualTo("123456789");
	    
    }
}
