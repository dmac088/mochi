package io.nzbee.test.integration.domain;

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
import io.nzbee.domain.product.Food;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.ProductServiceImpl;
import io.nzbee.domain.product.Product;
import io.nzbee.test.integration.beans.ProductDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_ProductDoServiceImplIntegrationTest {

	@TestConfiguration
    static class ProductServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "productDomainService")
        public IProductService ProductService() {
            return new ProductServiceImpl();
        }
        
        @Bean(value = "productDoBeanFactory")
        public ProductDoBeanFactory ProductFactoryBean() {
            return new ProductDoBeanFactory();
        }
    }
	

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
    private IProductService productService;
	
	@Autowired
	private ProductDoBeanFactory productDoBeanFactory;
	
	private Product product = null;
	
	
	public Product persistNewProduct() {
		product = productDoBeanFactory.getProductDoBean();
   	
		productService.save(product);
	    	
	    return product;
	}
	
	@Before
	public void setUp() { 
		this.persistNewProduct();
	}
	
	@Test
    public void whenValidCode_thenProductShouldBeFound() {
        String code = "3254354673";
        
        Product found = productService.findByCode("en-GB", "HKD", code);
      
        assertFound(found);
    }
    
    @Test
    public void whenValidDesc_thenProductShouldBeFound() {
    	String desc = "Test Product Description";
    	
    	Product found = productService.findByDesc("en-GB", "HKD", desc);
      
        assertFound(found);
    }
    
    private void assertFound(final Product found) {

    	assertThat(found.getProductUPC())
        .isEqualTo("3254354673");
    	
    	assertThat(found.getProductRetail())
    	.isEqualTo(new Double(78));
    	
    	assertThat(found.getProductMarkdown())
    	.isEqualTo(new Double(71));
    	
    	assertThat(((Food) found).getCountryOfOrigin())
    	.isEqualTo("NZL");
    	
    	assertThat(((Food) found).getDisplayCategories())
    	.isEqualTo("test1, test2");
	    
	    assertThat(found.getProductDesc())
	    .isEqualTo("Test Product Description");
    }
	
}
