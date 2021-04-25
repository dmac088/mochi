package io.nzbee.test.integration.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.test.integration.view.beans.product.IProductViewBeanFactory;
import io.nzbee.test.integration.view.beans.product.ProductViewBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
public class IT_ProductDoServiceImplIntegrationTest {
	
	@TestConfiguration
	static class ProductDoServiceImplIntegrationTest_Configuration {
		// the beans that we need to run this test
		
		@Bean
		public IProductViewBeanFactory productDoBeanFactory() {
			return new ProductViewBeanFactory();
		}
			
	}
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private IProductPortService productService;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static boolean setUpIsDone = false;
	
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
		setUpIsDone = true;
	}
	
	@Test
	@Rollback(false)
    public void whenValidCode_thenProductShouldBeFound() {
        Product found = productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "3254354673");
      
        assertFound(found);
    }
    
    @Test
    @Rollback(false)
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
    	
	    assertThat(found.getProductDesc())
	    .isEqualTo("Test Product Description");
	    
	    assertNotNull(found.getPromotions());
	    
	    assertThat(found.getPromotions().size())
	    .isEqualTo(1);
	    
    }
	
}
