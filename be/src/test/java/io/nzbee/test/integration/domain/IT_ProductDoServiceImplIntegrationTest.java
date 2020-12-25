package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.test.integration.domain.beans.product.IProductDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
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
public class IT_ProductDoServiceImplIntegrationTest {
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private IProductPortService productService;
	
	@Autowired
	private IProductDoBeanFactory productDoBeanFactory;
	
	private Product product = null;
	
	
	private Product persistNewProduct() {
		product = productDoBeanFactory.getBean();
   	
		productService.save(product);
	    	
	    return product;
	}
	
	@Before
	public void setUp() { 
		this.persistNewProduct();
	}
	
	@Test
    public void whenValidCode_thenProductShouldBeFound() {
        Product found = productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "3254354673");
      
        assertFound(found);
    }
    
    @Test
    public void whenValidDesc_thenProductShouldBeFound() {
    	Product found = productService.findByDesc(Constants.localeENGB, Constants.currencyHKD, "Test Product Description");
      
        assertFound(found);
    }
    
    private void assertFound(Product found) {

    	assertNotNull(found);
    	
    	assertThat(found.getProductUPC())
        .isEqualTo("3254354673");
    	
    	assertThat(found.getProductRetail())
    	.isEqualTo(new Double(78));
    	
    	assertThat(found.getProductMarkdown())
    	.isEqualTo(new Double(71));
	    
	    assertNotNull(found.getBrand());
    	
	    assertThat(found.getProductDesc())
	    .isEqualTo("Test Product Description");
	    
	    assertNotNull(found.getCategories());
	    
	    assertNotNull(found.getPromotions());
	    
	    assertThat(found.getPromotions().size())
	    .isEqualTo(1);
	    
	    assertThat(found.getCategories().size())
	    .isEqualTo(2);
	    
	    assertThat(found.getCategories().stream().filter(c -> c.getCategoryCode().equals("POM01")).findAny().isPresent())
	    .isTrue();
	    
	    assertThat(found.getCategories().stream().filter(c -> c.getCategoryCode().equals("CIT01")).findAny().isPresent())
	    .isTrue();
    }
	
}
