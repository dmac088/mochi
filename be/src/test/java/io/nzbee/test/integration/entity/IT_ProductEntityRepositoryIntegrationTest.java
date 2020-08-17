package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
				 																  "123456789");
     
        //then
    	assertTrue(found.size() == 1);
    }
	
	@Test
    public void whenFindForFruitCategory_thenReturnAllFruitProducts() {
    	
        // when
    	Page<Product> found =		 productService.findAll( Constants.localeENGB, 
    														 Constants.currencyUSD, 
    														 "FRT01", 
    														 new HashSet<String>(), 
    														 new HashSet<String>(), 
    														 new HashSet<String>(), 
    														 new Double(10000), 
    														 "0", 
    														 "50", 
    														 "priceAsc");
    
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(12));
    }
	
	
	@Test
    public void whenFindForFruitCategoryWithNullPrice_thenReturnAllFruitProducts() {
    	
        // when
    	Page<Product> found =		 productService.findAll( Constants.localeENGB, 
    														 Constants.currencyUSD, 
    														 "FRT01", 
    														 new HashSet<String>(), 
    														 new HashSet<String>(), 
    														 new HashSet<String>(), 
    														 null, 
    														 "0", 
    														 "50", 
    														 "priceAsc");
    
    	assertNotNull(found);
	}
	
	@Test
    public void whenFindForPomesCategory_thenReturnAllPomesProducts() {
    	
		Set<String> categories = new HashSet<String>();
		categories.add("POM01");
		
        // when
    	Page<Product> found =		 productService.findAll( Constants.localeENGB, 
    														 Constants.currencyUSD, 
    														 "FRT01", 
    														 categories, 
    														 new HashSet<String>(), 
    														 new HashSet<String>(), 
    														 new Double(10000), 
    														 "0", 
    														 "50", 
    														 "priceAsc");
    
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(3));
    }
	
	
	@Test
    public void whenFindForFruitWithOrganicTag_thenReturnAllOrganicFruitProducts() {
    	
		Set<String> tags = new HashSet<String>();
		tags.add("ORG01");
		
        // when
    	Page<Product> found =		 productService.findAll( Constants.localeENGB, 
    														 Constants.currencyUSD, 
    														 "FRT01", 
    														 new HashSet<String>(), 
    														 new HashSet<String>(), 
    														 tags, 
    														 new Double(10000), 
    														 "0", 
    														 "50", 
    														 "priceAsc");
    
        //then
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(1));
    }
	
	@Test
    public void whenFindForFruitWithBrandEnza_thenReturnAllEnzaFruitProducts() {
    	
		Set<String> brands = new HashSet<String>();
		brands.add("ENZ01");
		
        // when
    	Page<Product> found =		 productService.findAll( Constants.localeENGB, 
    														 Constants.currencyUSD, 
    														 "FRT01", 
    														 new HashSet<String>(), 
    														 brands, 
    														 new HashSet<String>(),
    														 new Double(10000), 
    														 "0", 
    														 "50", 
    														 "priceAsc");
    
        //then
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(1));
    }
	
	@Test
    public void whenFindForBrandEnza_thenReturnAllEnzaProducts() {
    	
		Set<String> brands = new HashSet<String>();
		brands.add("ENZ01");
		
        // when
    	Page<Product> found =		 productService.findAll( Constants.localeENGB, 
    														 Constants.currencyUSD, 
    														 "PRM01", 
    														 new HashSet<String>(), 
    														 brands, 
    														 new HashSet<String>(),
    														 new Double(10000), 
    														 "0", 
    														 "50", 
    														 "priceAsc");
    
        //then
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(4));
    }
	
	 
    private void assertFound(final Product found) {
    	
    	assertThat(found.getUPC())
        .isEqualTo("123456789");
    	
    	assertThat(found.getDepartment().getDepartmentCode())
    	.isEqualTo("ACC01");
    	
    	assertThat(found.getProductStatus().getCode())
    	.isEqualTo("ACT01");
    	
    	assertThat(found.getBrand().getBrandCode())
    	.isEqualTo("PLA01");
    	
    	assertNotNull(found.getPrimaryCategory());
    	assertThat(found.getPrimaryCategory().getCategoryCode().equals("FRT01")).isTrue();
    	
    	assertNotNull(found.getCategories());
    	assertThat(found.getCategories().stream().filter(f -> f.getCategoryCode().equals("FRT01")).findFirst().isPresent()).isTrue();
    	
    	assertThat(found.getCurrentRetailPriceUSD())
    	.isEqualTo(new Double(7.8));
    	
    	assertThat(found.getCurrentRetailPriceHKD())
    	.isEqualTo(new Double(78));
    	
    	assertNotNull(found.getTags());
    	assertThat(found.getTags().stream().filter(f -> f.getTagCode().equals("ORG01")).findFirst().isPresent()).isTrue();
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
