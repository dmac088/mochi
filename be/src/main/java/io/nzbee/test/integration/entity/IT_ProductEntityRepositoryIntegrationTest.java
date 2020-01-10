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
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
import io.nzbee.test.integration.beans.ProductEntityBeanFactory;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
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
    
    private io.nzbee.entity.product.Product product = null;
    
    
    @Before
    public void setUp() { 
    	//this.persistNewProduct();
    }
    
    
	public io.nzbee.entity.product.Product persistNewProduct() {
    	
		product = productEntityBeanFactory.getProductEntityBean();
	    
		entityManager.persist(product.getProductType());
		entityManager.persist(product.getBrand());
		entityManager.persist(product.getProductStatus());
		
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
