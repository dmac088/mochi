package io.nzbee.test.integration.domain;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.test.integration.domain.beans.brand.BrandDoBeanFactory;
import io.nzbee.test.integration.domain.beans.brand.IBrandDoBeanFactory;
import io.nzbee.test.integration.domain.beans.category.CategoryDoBeanFactory;
import io.nzbee.test.integration.domain.beans.category.ICategoryDoBeanFactory;
import io.nzbee.test.integration.domain.beans.department.DepartmentDoBeanFactory;
import io.nzbee.test.integration.domain.beans.department.IDepartmentDoBeanFactory;
import io.nzbee.test.integration.domain.beans.product.IProductDoBeanFactory;
import io.nzbee.test.integration.domain.beans.product.ProductDoBeanFactory;
import io.nzbee.test.integration.domain.beans.promotion.IPromotionDoBeanFactory;
import io.nzbee.test.integration.domain.beans.promotion.PromotionDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
public class IT_ProductDoServiceImplIntegrationTest {
	
	@TestConfiguration
	static class ProductDoServiceImplIntegrationTest_Configuration {
		// the beans that we need to run this test
		
		@Bean
		public IProductDoBeanFactory productDoBeanFactory() {
			return new ProductDoBeanFactory();
		}
		
		@Bean
		public ICategoryDoBeanFactory categoryDoBeanFactory() {
			return new CategoryDoBeanFactory();
		}
		
		@Bean
		public IBrandDoBeanFactory brandDoBeanFactory() {
			return new BrandDoBeanFactory();
		}
		
		@Bean
		public IDepartmentDoBeanFactory departmentDoBeanFactory() {
			return new DepartmentDoBeanFactory();
		}
		
		@Bean
		public IPromotionDoBeanFactory promotionDoBeanFactory() {
			return new PromotionDoBeanFactory();
		}
		
	}
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private IProductPortService productService;
	
	@Autowired
	private IProductDoBeanFactory productDoBeanFactory;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static Product product = null;
	
	private static boolean setUpIsDone = false;
	
	private Product persistNewProduct() {
		product = productDoBeanFactory.getBean();
   	
		productService.save(product);
	    	
	    return product;
	}
	
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
