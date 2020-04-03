package io.nzbee.test.integration.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

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
import io.nzbee.dto.product.IProductService;
import io.nzbee.dto.product.Product;
import io.nzbee.dto.product.ProductServiceImpl;
import io.nzbee.test.integration.beans.ProductDtoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_ProductDtoServiceImplIntegrationTest {

	@TestConfiguration
    static class ProductEntityServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "productDtoService")
        public IProductService productService() {
            return new ProductServiceImpl();
        }
        
        @Bean(value = "productEntityService")
        public io.nzbee.entity.product.IProductService productEntityService() {
        	return new io.nzbee.entity.product.ProductServiceImpl();
        }
       
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
    private IProductService productService;
	
	@Autowired
	private ProductDtoBeanFactory productDtoBeanFactory;
	
	private Product product = null;
	
	
	public Product persistNewProduct() {
    	
		product = productDtoBeanFactory.getProductDtoBean();

	    //persist a new transient test category
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
        Optional<Product> found = productService.findByCode("en-GB", "HKD", code);
      
        assertFound(found.get());
    }
    
    private void assertFound(final Product found) {
    	assertThat(found.getCode())
        .isEqualTo("123456789");
    	assertThat(found.getDepartmentCode())
    	.isEqualTo("FOO01");
    	assertThat(found.getProductStatusCode())
    	.isEqualTo("ACT01");
    	assertThat(found)
    	.isEqualTo("PLA01");
    	assertThat(found.getProductRetail())
    	.isEqualTo(new Double(78));
    }
    
	
}
