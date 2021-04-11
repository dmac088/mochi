package io.nzbee.test.integration.entity.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.test.integration.entity.beans.product.physical.IPhysicalProductEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_ProductEntityRepositoryIntegrationTest {

	@TestConfiguration
    static class ProductDTORepositoryIntegrationTest {
        
    }
	
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private IPhysicalProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IProductService productService;

    @Autowired
    @Qualifier("mochiDataSourceOwner")
    private DataSource database;
    
    private static ProductEntity product = null;

    private static boolean setUpIsDone = false;
    
    
	public ProductEntity persistNewProduct() {
    	
		product = productEntityBeanFactory.getBean();
	    
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
	@Rollback(false)
	public void whenFindById_thenReturnProduct() {
		System.out.println("ProductId = " + product.getProductId());
		
		 // when
    	Optional<ProductDTO> found = productService.findById( Constants.localeENGB, 
				  								  	Constants.currencyUSD,  
				  								  	product.getProductId());
     
        // then
    	assertFound(found);
	}
	
	
	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnProduct() {
		 // when
    	Optional<ProductDTO> found = productService.findByCode(Constants.localeENGB, 
				  								  	 Constants.currencyUSD,  
												     "123456789");
    	
        // then
    	assertFound(found);
	}
	
	@Test
	@Rollback(false)
	public void whenFindByDesc_thenReturnProduct() {
		 // when
    	Optional<ProductDTO> found = productService.findByDesc(Constants.localeENGB, 
				  								  	 Constants.currencyUSD,  
												  	 "test product");
     
        // then
    	assertFound(found);
	}
	
	 
    private void assertFound(Optional<ProductDTO> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getProductUPC())
        .isEqualTo("123456789");
    	
    	assertThat(found.get().getDepartment().getDepartmentCode())
    	.isEqualTo("ACC01");
    	
    	assertThat(found.get().getProductStatusCode())
    	.isEqualTo("ACT01");
    	
//    	assertThat(found.get().getBrand().getBrandCode())
//    	.isEqualTo("PLA01");
    	
    	assertNotNull(found.get().getCategories());
    	assertThat(found.get().getCategories().size()).isEqualTo(2);
    	assertThat(found.get().getCategories().stream().filter(f -> f.getCategoryCode().equals("POM01")).findAny().isPresent()).isTrue();
    	assertThat(found.get().getCategories().stream().filter(f -> f.getCategoryCode().equals("CIT01")).findAny().isPresent()).isTrue();
    	
    	assertThat(found.get().getRetailPrice())
    	.isEqualTo(new Double(7.8));
    	
    	assertThat(found.get().getMarkdownPrice())
    	.isEqualTo(new Double(6.45));
    	
    	assertThat(found.get().getPromotions().size()).isEqualTo(1);
    	
    //	assertThat(found.getTags().stream().filter(f -> f.getTagCode().equals("ORG01")).findFirst().isPresent()).isTrue();
    }
    
}
