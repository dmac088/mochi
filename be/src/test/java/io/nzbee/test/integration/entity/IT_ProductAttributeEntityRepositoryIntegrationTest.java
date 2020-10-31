package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import javax.persistence.EntityManager;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.test.integration.beans.ProductEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
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
public class IT_ProductAttributeEntityRepositoryIntegrationTest {

	@TestConfiguration
    static class ProductEntityRepositoryIntegrationTest {
        
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private ProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IProductService productService;
    
    private ProductEntity p = null;
    
	public ProductEntity persistNewProduct() {
    	
		p = productEntityBeanFactory.getProductEntityBean();
	    
	    entityManager.persist(p);
	    entityManager.flush();
	    	
	    return p;
	}
	
	@Before
	public void persistANewProduct() {
		this.persistNewProduct();
	}
	
	@Test
	public void whenFindById_thenReturnProduct() {
		 // when
    	ProductEntity found = productService.findById(  Constants.localeENGB, 
												  Constants.currencyHKD,  
												  p.getProductId()).get();
     
        // then
    	assertFound(found);
	}
	
	
	@Test
	public void whenFindByCode_thenReturnProduct() {
		 // when
    	ProductEntity found = productService.findByCode(Constants.localeENGB, 
				  								  Constants.currencyHKD,  
												  "123456789").get();
     
        // then
    	assertFound(found);
	}
	 
    private void assertFound(final ProductEntity found) {
    	assertThat(found.getUPC())
        .isEqualTo("123456789");
    	assertThat(found.getCategories().stream().filter(f -> f.getCategoryCode().equals("POM01")).findFirst().isPresent())
    	.isTrue();
    	assertThat(found.getCategories().stream().filter(f -> f.getCategoryCode().equals("CIT01")).findFirst().isPresent())
    	.isTrue();
    	assertThat(found.getDepartment().getDepartmentCode())
    	.isEqualTo("ACC01");
    	assertThat(found.getProductStatus().getCode())
    	.isEqualTo("ACT01");
    	assertThat(found.getBrand().getBrandCode())
    	.isEqualTo("PLA01");
    	assertThat(found.getProductAttribute().getProductDesc())
    	.isEqualTo("test product");
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
