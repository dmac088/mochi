package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.util.HashSet;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.category.CategoryDTO;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.test.integration.beans.CategoryEntityBeanFactory;

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
public class IT_ProductCategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class ProductCategoryEntityRepositoryIntegrationTest {
        
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
   
    
    private io.nzbee.entity.category.CategoryEntity category = null;
    
    @Before
    public void setUp() { 
    	this.persistNewCategory();
    }
    
    
	public io.nzbee.entity.category.CategoryEntity persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getProductCategoryEntityBean();
	    
	    //persist a new transient test category
	    entityManager.persist(category);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return category;
	}
   
    
    @Test
    public void whenFindById_thenReturnProductCategory() {
    	
        // when
    	CategoryDTO found = categoryService.findById(Constants.localeENGB, 
    												 category.getCategoryId()).get();
     
        // then
    	assertFound(found);
    }
    
    @Test
    public void whenFindByCode_thenReturnProductCategory() {
    	
        // when
    	CategoryDTO found = categoryService.findByCode(	Constants.localeENGB, 
    													"TST02").get();
     
        // then
    	assertFound(found);
    }
    
    @Test
    public void whenFindByExistingCode_thenReturnProductCategory() {
    	
        // when
    	CategoryDTO found = categoryService.findByCode(	Constants.localeENGB, 
				 										"FET01").get();
     
    	assertThat(found.getCategoryCode())
        .isEqualTo("FET01");
    	
	    assertThat(found.getCategoryDesc())
	    .isEqualTo("Featured");
    }
    
    @Test
    public void whenFindByDesc_thenReturnProductCategory() {
    	
        // when
    	CategoryDTO found = categoryService.findByDesc(	Constants.localeENGB,
				 										"test product category").get();
     
        //then
    	assertFound(found);
    }
    
    @Test
    public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	Set<CategoryDTO> lc = categoryService.findAll(	Constants.localeENGB, 
		    											Constants.currencyUSD, 
		    											"FRT01",
		    											categories,
		    											brands, 
		    											tags, 
		    											null);
     
        //then
    	assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(7);
    }
    
    @Test
    public void whenFindAllWithBrandFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	brands.add("ENZ01");
    	Set<String> tags = new HashSet<String>();
    	
    	//when
    	Set<CategoryDTO> lc = categoryService.findAll(	Constants.localeENGB, 
    													Constants.currencyUSD, 
    													"FRT01", 
    													categories,
    													brands, 
    													tags, 
    													null);
     
        //then
    	assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(1);
    }
    
    @Test
    public void whenFindAllWithTagFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categories = new HashSet<String>();
    	Set<String> brands = new HashSet<String>();
    	Set<String> tags = new HashSet<String>();
    	tags.add("GFR01");
    	
    	//when
    	Set<CategoryDTO> lc = categoryService.findAll(		Constants.localeENGB, 
		    												Constants.currencyUSD, 
		    												"FRT01", 
		    												categories,
		    												brands, 
		    												tags, 
		    												null);
     
        //then
		assertNotNull(lc);
    	assertThat(lc.size()).isEqualTo(1);
    }
    
  
    private void assertFound(final CategoryDTO found) {
    	
    	CategoryProductDTO cp = (CategoryProductDTO) found;
    	
    	assertThat(cp.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(cp.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    
	    assertThat(cp.getCategoryDesc())
	    .isEqualTo("test product category");
    }

 
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}