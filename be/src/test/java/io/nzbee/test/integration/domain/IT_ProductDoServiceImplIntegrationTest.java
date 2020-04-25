package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.nzbee.Globals;
import io.nzbee.domain.product.Food;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.Product;
import io.nzbee.test.integration.beans.ProductDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
public class IT_ProductDoServiceImplIntegrationTest {

	@TestConfiguration
    static class ProductServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
		
    }
	
	@Autowired
	private Globals globalVars;
	
	@Autowired
    private IProductService productService;
	
	@Autowired
	private ProductDoBeanFactory productDoBeanFactory;
	
	private Product product = null;
	
	
	private Product persistNewProduct() {
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
        Product found = productService.findByCode(globalVars.getLocaleENGB(), globalVars.getCurrencyHKD(), "3254354673").get();
      
        assertFound(found);
    }
    
    @Test
    public void whenValidDesc_thenProductShouldBeFound() {
    	Product found = productService.findByDesc(globalVars.getLocaleENGB(), globalVars.getCurrencyHKD(), "Test Product Description").get();
      
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
    	.isEqualTo("Fruit");
	    
	    assertThat(found.getProductDesc())
	    .isEqualTo("Test Product Description");
    }
	
}
