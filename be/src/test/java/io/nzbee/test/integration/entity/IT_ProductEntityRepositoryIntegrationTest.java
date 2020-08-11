package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
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
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.food.Food;
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
public class IT_ProductEntityRepositoryIntegrationTest {

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

    @Autowired
    private ICategoryProductService productCategoryService;
    
    private Product product = null;
    
	public Product persistNewProduct() {
    	
		product = productEntityBeanFactory.getProductEntityBean();
	    
	    entityManager.persist(product);
	    entityManager.flush();
	    	
	    return product;
	}
	
	@Before
	public void persistANewProduct() {
		this.persistNewProduct();
	}
	
	@Test
	public void whenFindById_thenReturnProduct() {
		 // when
    	Product found = productService.findById(  Constants.localeENGB, 
				  								  Constants.currencyUSD,  
												  product.getProductId()).get();
     
        // then
    	assertFound(found);
	}
	
	
	@Test
	public void whenFindByCode_thenReturnProduct() {
		 // when
    	Product found = productService.findByCode(Constants.localeENGB, 
				  								  Constants.currencyUSD,  
												  "123456789").get();
     
        // then
    	assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenReturnProduct() {
		 // when
    	Product found = productService.findByDesc(Constants.localeENGB, 
				  								  Constants.currencyUSD,  
												  "test product").get();
     
        // then
    	assertFound(found);
	}
	
	@Test
    public void whenFindByProductCode_thenReturnProductCategories() {
    	
        // when
    	List<CategoryProduct> found = productCategoryService.findAllByProductCode(Constants.localeENGB, 
				  																  Constants.currencyUSD, 
				 																  "123456789");
     
        //then
    	assertTrue(found.size() == 1);
    }
	
	@Test
    public void whenFindByProductCode_thenReturnProduct() {
    	
        // when
    	Page<Product> found =		 productService.findAll( Constants.localeENGB, 
    														 Constants.currencyUSD, 
    														 "FRT01", 
    														 new HashSet<String>(), 
    														 new HashSet<String>(), 
    														 new HashSet<String>(), 
    														 new Double(10000), 
    														 "1", 
    														 "50", 
    														 "priceAsc");
    
    	Long size = found.getTotalElements();
        //then
    	assertThat(size).isEqualTo(new Long(13));
    }
	 
    private void assertFound(final Product found) {
    	assertThat(found.getUPC())
        .isEqualTo("123456789");
    	assertThat(found.getCategories().stream().filter(f -> f.getCategoryCode().equals("FRT01")).findFirst().isPresent())
    	.isTrue();
    	assertThat(found.getDepartment().getDepartmentCode())
    	.isEqualTo("FOO01");
    	assertThat(found.getProductStatus().getCode())
    	.isEqualTo("ACT01");
    	assertThat(found.getBrand().getBrandCode())
    	.isEqualTo("PLA01");
    	assertNotNull(found.getPrimaryCategory());
    	if(found instanceof Food) {
    		assertThat(((Food) found).getCountryOfOrigin())
    		.isEqualTo("NZL");
    	}
    	assertThat(found.getCurrentRetailPriceUSD())
    	.isEqualTo(new Double(7.8));
    	assertThat(found.getCurrentRetailPriceHKD())
    	.isEqualTo(new Double(78));
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
